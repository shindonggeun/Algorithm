import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		int button;
		
		public Position(int x, int y, int button) {
			this.x = x;
			this.y = y;
			this.button = button;
		}
	}
	
	static int N;
	static int[][] map;
	static int[][] dist;
	static final int INF = Integer.MAX_VALUE;
	// 2가지 방향 배열 (하, 우)
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N]; // [0][0] ~ [N-1][N-1]
		dist = new int[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dist[i][j] = INF;
			}
		}
		
		dijkstra(0, 0);
		
		System.out.println(dist[N-1][N-1]);

	}
	
	public static void dijkstra(int startX, int startY) {
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		pq.add(new Position(startX, startY, 0));
		dist[startX][startY] = 0;
		
		while (!pq.isEmpty()) {
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowButton = now.button;
			
			for (int i=0; i<2; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				int requireButton = 0;
				if (map[nowX][nowY] <= map[nextX][nextY]) {
					requireButton = map[nextX][nextY] - map[nowX][nowY] + 1;
				}
				
				int cost = nowButton + requireButton;
				
				if (cost < dist[nextX][nextY]) {
					dist[nextX][nextY] = cost;
					pq.add(new Position(nextX, nextY, cost));
				}
			}
		}
	}

}