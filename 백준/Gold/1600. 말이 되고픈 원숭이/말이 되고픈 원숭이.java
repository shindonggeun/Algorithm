import java.util.*;
import java.io.*;

public class Main {

	static class Position {
		int x, y;
		int distance;	// 원숭이가 갈 수 있는 거리
		int count;		// 원숭이가 말처럼 움직일 수 있는 능력 몇개까지 사용했는지 저장할 변수
		
		public Position(int x, int y, int distance, int count) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.count = count;
		}
	}
	
	static int K;	// 능력 수
	static int W;	// 가로 길이 (x 좌표)
	static int H;	// 세로 길이 (y 좌표)
	static int[][] map;
	static boolean[][][] visited;	// 3차원 방문배열 이용
	// 8가지 방향 배열 
	static int[] eigthDx = {2, 1, -1, -2, -2, -1, 1, 2};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] eightDy = {1, 2, 2, 1, -1, -2, -2, -1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] fourDx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] fourDy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];	// (0, 0) ~ (W-1, H-1)
		visited = new boolean[K+1][H][W];	// [0] -> 능력 사용 X, [1] ~ [K] 능력 사용한 횟수
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = bfs(0, 0);
		System.out.println(result);

	}
	
	public static int bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0, 0));
		visited[0][startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			int nowCount = now.count;
			
			// 끝좌표에 도달했으면 메서드 종료
			if(nowX == H-1 && nowY == W-1) {
				return nowDistance;
			}

			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for (int i = 0; i < 4; i++) {
				int nextX = nowX + fourDx[i];
				int nextY = nowY + fourDy[i];

				if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
					continue;
				}

				if (visited[nowCount][nextX][nextY] || map[nextX][nextY] == 1) {
					continue;
				}

				queue.add(new Position(nextX, nextY, nowDistance + 1, nowCount));
				visited[nowCount][nextX][nextY] = true;
			}
			
			if(nowCount < K) {
				// 8가지 방향 탐색
				for(int i=0; i<8; i++) {
					int nextX = nowX + eigthDx[i];
					int nextY = nowY + eightDy[i];
					
					if(nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
						continue;
					}
					
					// 탐색한 좌표가 이미 방문했거나 또는 벽(1)인 경우
					if(visited[nowCount+1][nextX][nextY] || map[nextX][nextY] == 1) {
						continue;
					}
					
					queue.add(new Position(nextX, nextY, nowDistance + 1, nowCount + 1));
					visited[nowCount + 1][nextX][nextY] = true;
				}
			}			
		}
		
		// 탐색을 다 했는데도 이동할 수 없는 경우 -1 리턴
		return -1;
	}

}
