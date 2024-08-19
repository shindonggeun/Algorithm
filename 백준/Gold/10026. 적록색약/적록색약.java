import java.util.*;
import java.io.*;

public class Main {
	
	static class Color {
		int x;
		int y;
		
		public Color(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static char[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		visited = new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		// 1. 적록색약이 아닌 사람이 봤을 때 구역의 개수 구하는 과정 (정상인 사람)
		int normalAreaCount = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
					normalAreaCount++;
				}
			}
		}
		
		// 2. 해당 그리드 적록색약 버전으로 바꾸는 과정 ('G' -> 'R') 
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j] == 'G') {
					map[i][j] = 'R';
				}
			}
		}
		
		// 3. 적록색약인 사람이 봤을 때 구역의 개수를 구하는 과정 (비정상)
		visited = new boolean[N][N];
		int abnormalAreaCount = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
					abnormalAreaCount++;
				}
			}
		}
		
		System.out.println(normalAreaCount + " " + abnormalAreaCount);

	}
	
	public static void bfs(int startX, int startY) {
		Queue<Color> queue = new LinkedList<>();
		queue.add(new Color(startX, startY));
		visited[startX][startY] = true;
		char ch = map[startX][startY];
		
		while (!queue.isEmpty()) {
			Color now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				if (visited[nextX][nextY] || map[nextX][nextY] != ch) {
					continue;
				}
				
				queue.add(new Color(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
	}

}