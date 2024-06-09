import java.util.*;
import java.io.*;

public class Main {
	
	static class Treasure {
		int x;
		int y;
		int distance;
		
		public Treasure(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	static int L;
	static int W;
	static char[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new char[L][W]; // [0][0] ~ [L-1][W-1]
		
		for (int i=0; i<L; i++) {
			String input = br.readLine();
			for (int j=0; j<W; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		int resultDistance = 0;
		for (int i=0; i<L; i++) {
			for (int j=0; j<W; j++) {
				if (map[i][j] == 'L') {
					visited = new boolean[L][W];
					int distance = bfs(i, j);
					resultDistance = Math.max(resultDistance, distance);
				}
			}
		}
		
		System.out.println(resultDistance);
		
	}
	
	public static int bfs(int startX, int startY) {
		Queue<Treasure> queue = new LinkedList<>();
		queue.add(new Treasure(startX, startY, 0));
		visited[startX][startY] = true;
		
		int maxDistance = 0;
		
		while (!queue.isEmpty()) {
			Treasure now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= L || nextY >= W) {
					continue;
				}
				
				if (visited[nextX][nextY] || map[nextX][nextY] == 'W') {
					continue;
				}
				
				queue.add(new Treasure(nextX, nextY, nowDistance + 1));
				visited[nextX][nextY] = true;
				maxDistance = nowDistance + 1;
			}
		}
		
		return maxDistance;
	}
	


}