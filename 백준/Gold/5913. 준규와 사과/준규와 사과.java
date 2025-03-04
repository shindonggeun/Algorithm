import java.util.*;
import java.io.*;

public class Main {

	static final int SIZE = 5;
	static int K;
	static int[][] map;
	static boolean[][] visited;
	// 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		K = Integer.parseInt(br.readLine());
		
		map = new int[SIZE][SIZE];
		visited = new boolean[SIZE][SIZE];
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			map[x][y] = 1;
		}
		
		result = 0;
		
		dfs(0, 0, 0, false);
		System.out.println(result);

	}
	
	public static void dfs(int x, int y, int depth, boolean flag) {
		if (depth == (SIZE * SIZE - K) / 2) {
			if (!flag) {
				dfs(x, y, 0, true);
			}
			else if (x == SIZE - 1 && y == SIZE - 1) {
				result++;
			}
			
			return;
		}
		
		visited[x][y] = true;
		
		for (int i=0; i<4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if (isValid(nextX, nextY)) {
				dfs(nextX, nextY, depth + 1, flag);
			}
		}
		
		visited[x][y] = false;
	}
	
	
	
	public static boolean isValid(int x, int y) {
		if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
			return false;
		}
		
		if (visited[x][y] || map[x][y] == 1) {
			return false;
		}
		
		return true;
	}

}