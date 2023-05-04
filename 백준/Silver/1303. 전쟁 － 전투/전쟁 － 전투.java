import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x, y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;	// 가로 크기 (x축)
	static int M;	// 세로 크기 (y축)
	static char[][] map;
	static boolean[][] whiteVisited;
	static boolean[][] blueVisited;
	// 4가지 방향 (상, 하, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};	// x축 고정되어 있을 때 방향 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축 고정되어 있을 때 방향 (좌, 우)
	static int whitePower;
	static int bluePower;
	static int whiteNum;	// 흰색옷을 입은 인접한 병사의 수 (아군)
	static int blueNum;		// 파란색옷을 입은 인접한 병사의 수 (적군)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 가로 크기 저장 (x)
		M = Integer.parseInt(st.nextToken());	// 세로 크기 저장 (y)
		
		map = new char[M][N];	// (0,0) ~ (N-1, M-1)
		whiteVisited = new boolean[M][N];
		blueVisited = new boolean[M][N];
		whitePower = 0;
		bluePower =  0;
		
		
		for(int i=0; i<M; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				// 해당 좌표가 방문처리 안된 상태이면서 흰색 옷을 입고있는 병사가 있는 경우 탐색
				if(!whiteVisited[i][j] && map[i][j] == 'W') {
					whiteNum = 0;
					whiteBFS(i, j);
					whitePower += Math.pow(whiteNum, 2);	// 해당 병사를 제곱한 수를 더해줌
				}
				// 해당 좌표가 방문처리 안된 상태이면서 파란색 옷을 입고있는 병사가 있는 경우 탐색
				if(!blueVisited[i][j] && map[i][j] == 'B') {
					blueNum = 0;
					blueBFS(i, j);
					bluePower += Math.pow(blueNum, 2);		// 해당 병사를 제곱한 수를 더해줌
				}
			}
		}
		
		System.out.println(whitePower + " " + bluePower);
	}
	
	public static void whiteBFS(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		whiteVisited[startX][startY] = true;
		whiteNum++;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 범위를 벗어난 경우 넘어간다
				// (0,0) ~ (N-1, M-1) 이외의 범위인 경우이다
				if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문한 상태거나 또는 파란색 옷을 입은 병사인 경우 (적군 병사)
				if(whiteVisited[nextX][nextY] || map[nextX][nextY] == 'B') {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				whiteVisited[nextX][nextY] = true;
				whiteNum++;
			}
		}
	}
	
	public static void blueBFS(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		blueVisited[startX][startY] = true;
		blueNum++;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 범위를 벗어난 경우 넘어간다
				// (0,0) ~ (N-1, M-1) 이외의 범위인 경우이다
				if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문한 상태거나 또는 흰색 옷을 입은 병사인 경우 (아군 병사)
				if(blueVisited[nextX][nextY] || map[nextX][nextY] == 'W') {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				blueVisited[nextX][nextY] = true;
				blueNum++;
			}
		}
	}

}
