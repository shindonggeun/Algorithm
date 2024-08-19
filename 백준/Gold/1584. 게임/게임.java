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
	static int N; // 위험한 구역의 개수
	static int M; // 죽음의 구역의 개수
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
		
		map = new int[SIZE+1][SIZE+1]; // [0][0] ~ [500][500]
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
			
			// 위험한 구역 설정
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
		
		dijkstra(0, 0);
		
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
	
	public static void dijkstra(int startX, int startY) {
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		pq.add(new Position(startX, startY));
		dist[startX][startY] = 0;
		
		while (!pq.isEmpty()) {
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [500][500] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX > SIZE || nextY > SIZE) {
					continue;
				}
				
				if (map[nextX][nextY] == DEATH) {
					continue;
				}
				
				int cost = dist[nowX][nowY] + map[nextX][nextY];
				
				if (cost < dist[nextX][nextY]) {
					dist[nextX][nextY] = cost;
					pq.add(new Position(nextX, nextY));
				}
			}
		}
	}

}