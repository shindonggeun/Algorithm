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
	
	static int N;
	static int M;
	static int[][] map;
	static int[][] dist;
	static final int INF = Integer.MAX_VALUE;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];	// [0][0] ~ [M-1][N-1]
		dist = new int[M][N];
		
		for(int i=0; i<M; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j) - '0';
				dist[i][j] = INF;
			}
		}
		
		dijkstra(0, 0);
		System.out.println(dist[M-1][N-1]);

	}
	
	public static void dijkstra(int startX, int startY) {
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		pq.add(new Position(startX, startY));
		dist[startX][startY] = 0;
		
		while(!pq.isEmpty()) {
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
				
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];

				if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
					continue;
				}
				

				int cost = dist[nowX][nowY] + map[nextX][nextY];
				if(cost < dist[nextX][nextY]) {
					pq.add(new Position(nextX, nextY));
					dist[nextX][nextY] = cost;
				}
			}

		}
	}

}
