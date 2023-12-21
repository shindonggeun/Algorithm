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
	
	static int H;
	static int W;
	static char[][] map;
	static boolean[][] visited;
	static int[][] dist;
	static final int INF = Integer.MAX_VALUE;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];	// [0][0] ~ [H-1][W-1]
		visited = new boolean[H][W];
		dist = new int[H][W];
		
		int startX = 0;
		int startY = 0;
		int endX = 0;
		int endY = 0;
		
		for(int i=0; i<H; i++) {
			String input = br.readLine();
			for(int j=0; j<W; j++) {
				map[i][j] = input.charAt(j);
				dist[i][j] = INF;
				
				if(map[i][j] == 'S') {
					startX = i;
					startY = j;
				}
				else if(map[i][j] == 'E') {
					endX = i;
					endY = j;
				}
			}
		}
		
		dijkstra(startX , startY);
		
		System.out.println(dist[endX][endY]);

	}
	
	public static void dijkstra(int startX, int startY) {
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		pq.add(new Position(startX, startY));
		dist[startX][startY] = 0;
		
		while(!pq.isEmpty()) {
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			if(!visited[nowX][nowY]) {
				visited[nowX][nowY] = true;
				boolean nowPositionWallCheck = wallCheck(nowX, nowY);
				
				// 4가지 방향 탐색 (하, 상, 좌, 우)
				for(int i=0; i<4; i++) {
					int nextX = nowX + dx[i];
					int nextY = nowY + dy[i];
					
					if(nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
						continue;
					}
					
					if(map[nextX][nextY] == '#') {
						continue;
					}
					
					boolean nextPositionWallCheck = wallCheck(nextX, nextY);
					
					int time = 0;
					
					if(nowPositionWallCheck && nextPositionWallCheck) {
						time = 0;
					}
					else {
						time = 1;
					}
					
					int nextDist = dist[nowX][nowY] + time;
					if(nextDist < dist[nextX][nextY]) {
						dist[nextX][nextY] = nextDist;
						pq.add(new Position(nextX, nextY));
					}
					
				}
			}
			
			
		}
	}
	
	public static boolean wallCheck(int nowX, int nowY) {
		for(int i=0; i<4; i++) {
			int nextX = nowX + dx[i];
			int nextY = nowY + dy[i];
			
			if(nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
				continue;
			}
			
			if(map[nextX][nextY] == '#') {
				return true;
			}
		}
		
		return false;
	}

}
