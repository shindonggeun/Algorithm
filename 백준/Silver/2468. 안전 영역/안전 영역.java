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
	static int[][] map;
	static boolean[][] visited;
	static int maxHeight;
	static int maxSafeAreaCount;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N]; // [0][0] ~ [N-1][N-1]
		
		maxHeight = 0;
		maxSafeAreaCount = 0;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}
		
		for (int h=0; h<=maxHeight; h++) {
		    visited = new boolean[N][N];
		    int safeAreaCount = 0;

		    for (int i=0; i<N; i++) {
		        for (int j=0; j<N; j++) {
		            if (!visited[i][j] && map[i][j] > h) {
		                bfs(i, j, h);  // h 기준으로 물에 잠기는 지역 무시
		                safeAreaCount++;
		            }
		        }
		    }

		    maxSafeAreaCount = Math.max(maxSafeAreaCount, safeAreaCount);
		}
		
		System.out.println(maxSafeAreaCount);
	}
	
	public static void bfs(int startX, int startY, int height) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		
		while (!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				if (visited[nextX][nextY] || map[nextX][nextY] <= height) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
	}

}