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
	
	static int K; // 클링온 전투선의 클래스 개수
	static int W; // 평면의 폭
	static int H; // 평면의 높이
	static int[][] map;
	static int[][] dist;
	static final int INF = Integer.MAX_VALUE;
	static Map<Character, Integer> battleShipMap;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			dist = new int[H][W];
			battleShipMap = new HashMap<>();
			
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				char battleShip = st.nextToken().charAt(0);
				int time = Integer.parseInt(st.nextToken());
				
				battleShipMap.put(battleShip, time);
			}
			
			int startX = 0;
			int startY = 0;
			
			for (int i=0; i<H; i++) {
				String input = br.readLine();
				for (int j=0; j<W; j++) {
					char ch = input.charAt(j);
					if (battleShipMap.containsKey(ch)) {
						map[i][j] = battleShipMap.get(ch);
					}
					else {
						startX = i;
						startY = j;
					}
					dist[i][j] = INF;
				}
			}
			
			int minTime = dijkstra(startX, startY);
			sb.append(minTime).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static int dijkstra(int startX, int startY) {
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		pq.add(new Position(startX, startY));
		dist[startX][startY] = 0;
		
		while (!pq.isEmpty()) {
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			if (nowX == 0 || nowY == 0 || nowX == H-1 || nowY == W-1) {
				return dist[nowX][nowY];
			}
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
					continue;
				}
				
				int cost = dist[nowX][nowY] + map[nextX][nextY];
				
				if (cost < dist[nextX][nextY]) {
					dist[nextX][nextY] = cost;
					pq.add(new Position(nextX, nextY));
				}
			}
		}
		
		return -1;
	}

}