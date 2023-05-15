import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x, y, distance;
		boolean crack;
		
		public Position(int x, int y, int distance, boolean crack) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.crack = crack;
		}
	}
	
	static int N;	// 세로 길이 (y 좌표)
	static int M;	// 가로 길이 (x 좌표)
	static char[][] map;	
	static boolean[][][] visited;	// 벽 부쉈는지 안부쉈는지 여부를 저장해주게끔 하기 위해 3차원 배열로
	// 4가지 방향 배열(상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y 좌표가 움직이는 방향 배열(상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x 좌표가 움직이는 방향 배열(좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];	// (0, 0) ~ (M-1, N-1)
		visited = new boolean[2][N][M];	// 3차원 배열 : [벽을 부쉈는지 0,1] -> [0] = 벽 안부쉈음, [1] = 벽 부숨
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		int result = bfs(0, 0, 1, false);
		System.out.println(result);
	}
	
	public static int bfs(int startX, int startY, int distance, boolean crack) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, distance, crack));
		visited[0][startX][startY] = true;
		
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			boolean nowCrack = now.crack;
			
			// 너비우선탐색 종료조건
			if(nowX == N-1 && nowY == M-1) {
				return nowDistance;
			}
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (M-1, N-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 탐색한 좌표가 벽(1)인 경우
				if(map[nextX][nextY] == '1') {
					// 한번도 벽을 부순적이 없는 경우
					if(!nowCrack) {
						queue.add(new Position(nextX, nextY, nowDistance + 1, true));
						visited[1][nextX][nextY] = true;
					}
				}
				// 탐색한 좌표가 이동(0) 가능한 경우
				else {
					// 부신 벽이 여태까지 없는 경우
					if(!nowCrack && !visited[0][nextX][nextY]) {
						queue.add(new Position(nextX, nextY, nowDistance + 1, false));
						visited[0][nextX][nextY] = true;
					}
					
					// 벽을 한번 부순적이 있는 경우
					if(nowCrack && !visited[1][nextX][nextY]) {
						queue.add(new Position(nextX, nextY, nowDistance + 1, true));
						visited[1][nextX][nextY] = true;
					}
				}
			}
		}
		// 위의 경우 다 탐색했는데 도착 좌표까지 갈 수 없는 경우 -1 반환
		return -1;
	}

}
