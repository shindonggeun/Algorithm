import java.util.*;
import java.io.*;

public class Main {
	
	static class Island {
		int x;
		int y;
		
		public Island(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int W;
	static int H;
	static int[][] map;
	static boolean[][] visited;
	// 8가지 방향 배열 (상, 상 + 우, 우, 하 + 우, 하, 하 + 좌, 좌, 상 + 좌)
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			if (W == 0 && H == 0) {
				break;
			}
			
			map = new int[H][W]; // [0][0] ~ [H-1][W-1]
			visited = new boolean[H][W];
			
			for (int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int islandCount = 0;
			for (int i=0; i<H; i++) {
				for (int j=0; j<W; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
						islandCount++;
					}
				}
			}
			
			sb.append(islandCount).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Island> queue = new LinkedList<>();
		queue.add(new Island(startX, startY));
		visited[startX][startY] = true;
		
		while (!queue.isEmpty()) {
			Island now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<8; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
					continue;
				}
				
				if (map[nextX][nextY] == 0 || visited[nextX][nextY]) {
					continue;
				}
				
				queue.add(new Island(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
	}

}