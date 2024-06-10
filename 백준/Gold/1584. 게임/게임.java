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
	
	static int N; // 위험한 구역의 수
	static int M; // 죽음의 구역의 수
	static int[][] map;
	static int[][] dist;
	static final int INF = Integer.MAX_VALUE;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[500+1][500+1]; // [0][0] ~ [500][500]
		dist = new int[500+1][500+1];
		
		for (int i=0; i<=500; i++) {
			Arrays.fill(dist[i], INF);
		}
		
		// 위험 구역 설정하는 과정
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			setupZone(x1, y1, x2, y2, 1);
		}
		
		M = Integer.parseInt(br.readLine());
		
		// 죽음 구역 설정하는 과정
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			setupZone(x1, y1, x2, y2, 2);
		}
		
		map[0][0] = 0;
		dijkstra(0, 0);
		
		if (dist[500][500] == INF) {
			System.out.println(-1);
		}
		else {
			System.out.println(dist[500][500]);
		}
	}
	
	// 구역 설정하는 메서드 (위험구역, 죽음 구역)
	public static void setupZone(int x1, int y1, int x2, int y2, int set) {
		int minX = Math.min(x1, x2);
		int maxX = Math.max(x1, x2);
		
		int minY = Math.min(y1, y2);
		int maxY = Math.max(y1, y2);
		
		for (int i=minX; i<=maxX; i++) {
			for (int j=minY; j<=maxY; j++) {
				// 죽음 구역이 위험 구역보다 우선
				if (map[i][j] < set) {
					map[i][j] = set;
				}
			}
		}
		
	}
	
	public static void dijkstra(int startX, int startY) {
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		pq.add(new Position(startX, startY));
		dist[startX][startY] = map[startX][startY];
		
		while (!pq.isEmpty()) {
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= 501 || nextY >= 501) {
					continue;
				}
				
				if (map[nextX][nextY] == 2) {
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