import java.util.*;
import java.io.*;

public class Main {
	
	static class Mountain {
		int x;
		int y;
		
		public Mountain(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	// 8가지 방향 배열 (맨 위부터 시계방향으로)
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					if (bfs(i, j)) {
						count++;
					}
				}
			}
		}
		
		System.out.println(count);

	}
	
	public static boolean bfs(int startX, int startY) {
		Queue<Mountain> queue = new LinkedList<>();
		queue.add(new Mountain(startX, startY));
		visited[startX][startY] = true;
		
		int currentHeight = map[startX][startY];
		boolean isPeak = true;
		
		while (!queue.isEmpty()) {
			Mountain now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<8; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if (map[nextX][nextY] > currentHeight) {
                    isPeak = false;
                }
                
                if (!visited[nextX][nextY] && map[nextX][nextY] == currentHeight) {
                    visited[nextX][nextY] = true;
                    queue.add(new Mountain(nextX, nextY));
                }
			}
		}
		
		return isPeak;
	}

}