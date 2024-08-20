import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static final int SIZE = 500;
	static int N;
	static int M;
	static int[][] map;
	static int[][] dist;
	static final int INF = Integer.MAX_VALUE;
	static final int DANGER = 1;
	static final int DEATH = 2;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		map = new int[SIZE+1][SIZE+1];
		dist = new int[SIZE+1][SIZE+1];
		
		for (int i=0; i<=SIZE; i++) {
			for (int j=0; j<=SIZE; j++) {
				dist[i][j] = INF;
			}
		}
		
		N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			setupZone(x1, y1, x2, y2, DANGER);
		}
		
		M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			setupZone(x1, y1, x2, y2, DEATH);
		}
		
		zeroOneBfs(0, 0);

		System.out.println(dist[SIZE][SIZE] == INF ? -1 : dist[SIZE][SIZE]);
	}
	
	public static void setupZone(int x1, int y1, int x2, int y2, int value) {
		int minX = Math.min(x1, x2);
		int minY = Math.min(y1, y2);
		
		int maxX = Math.max(x1, x2);
		int maxY = Math.max(y1, y2);
		
		for (int i=minX; i<=maxX; i++) {
			for (int j=minY; j<=maxY; j++) {
				if (map[i][j] < value) {
					map[i][j] = value;
				}
			}
		}
	}
	
	public static void zeroOneBfs(int startX, int startY) {
		Deque<Position> deque = new ArrayDeque<>();
		deque.add(new Position(startX, startY));
		dist[startX][startY] = 0;
		
		while (!deque.isEmpty()) {
			Position now = deque.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX > SIZE || nextY > SIZE) {
					continue;
				}
				
				if (map[nextX][nextY] == DEATH) {
					continue;
				}
				
				int cost = dist[nowX][nowY] + map[nextX][nextY];
				
				if (cost < dist[nextX][nextY]) {
					dist[nextX][nextY] = cost;
					
					if (map[nextX][nextY] == 0) {
						deque.addFirst(new Position(nextX, nextY));
					}
					else if (map[nextX][nextY] == DANGER) {
						deque.addLast(new Position(nextX, nextY));
					}
				}
				
				
			}
		}
	}
	
	

}