import java.util.*;
import java.io.*;

public class Main {

	static class Position {
		int x, y;
		int time;
		boolean gram;
		
		public Position(int x, int y, int time, boolean gram) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.gram = gram;
		}
	}
	
	static int N;	// 너비 (x좌표)
	static int M;	// 높이 (y좌표)
	static int T;	// 제한시간
	static int[][] map;
	static boolean[][][] visited;	
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
		visited = new boolean[N][M][2];	// [0]은 전설의 검 그람 없을 때, [1]은 전설의 검 그람 있을 때
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int resultTime = bfs(0, 0);	// 너비우선탐색 실시
		if(resultTime == -1) {
			System.out.println("Fail");
		}
		else {
			System.out.println(resultTime);
		}
		
	}
	
	public static int bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0, false));
		visited[startX][startY][0] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowTime = now.time;
			boolean nowGram = now.gram;
			
			// 제한시간 오버되면 while문 빠져나옴
			if(now.time > T) {
				break;
			}
			
			// 공주가 있는 좌표에 도달한 경우 걸린 시간 반환해줌
			if(nowX == N-1 && nowY == M-1) {
				return nowTime;
			}
			
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (M-1, N-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 전설의 검 그람을 보유한 경우
				if(nowGram) {
					// 그람을 가지고 있으면 어느벽이든 다 통과 가능 
					// 그러므로 해당 좌표가 방문했는지 여부만 판단해주면 됨
					if(!visited[nextX][nextY][1]) {
						queue.add(new Position(nextX, nextY, nowTime + 1, nowGram));
						visited[nextX][nextY][1] = true;
					}
				}
				// 그람을 보유하지 않은 경우
				else {
					// 해당 좌표가 벽(0)이면서 동시에 방문하지 않은 좌표인 경우
					if(map[nextX][nextY] == 0 && !visited[nextX][nextY][0]) {
						queue.add(new Position(nextX, nextY, nowTime + 1, nowGram));
						visited[nextX][nextY][0] = true;
					}
					// 전설의 검 그람(2)이 있는 좌표면서 동시에 방문하지 않은 좌표인 경우
					else if(map[nextX][nextY] == 2 && !visited[nextX][nextY][0]) {
						queue.add(new Position(nextX, nextY, nowTime + 1, true));
						visited[nextX][nextY][0] = true;
						visited[nextX][nextY][1] = true;
					}
				}
			}
			
		}
		return -1;
	}

}
