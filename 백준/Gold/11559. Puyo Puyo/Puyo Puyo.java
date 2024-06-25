import java.util.*;
import java.io.*;

public class Main {
	
	static class Puyo {
		int x;
		int y;
		
		public Puyo(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N = 12;
	static int M = 6;
	static char[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[N][M]; // [0][0] ~ [N-1][M-1]
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		int chainCount = 0;
		
		while (true) {
			boolean bomb = false;
			visited = new boolean[N][M];
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (!visited[i][j] && map[i][j] != '.') {
						if (bfs(i, j)) {
							bomb = true;
						}
					}
				}
			}
			
			if (!bomb) {
				break;
			}
			else {
				chainCount++;
				puyoDown();
			}
		}
		
		System.out.println(chainCount);
	}
	
	public static boolean bfs(int startX, int startY) {
		Queue<Puyo> queue = new LinkedList<>();
		queue.add(new Puyo(startX, startY));
		List<Puyo> bombList = new ArrayList<>();
		bombList.add(new Puyo(startX, startY));
		
		visited[startX][startY] = true;
		char color = map[startX][startY];
		int count = 1;
		
		while (!queue.isEmpty()) {
			Puyo now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if (visited[nextX][nextY] || map[nextX][nextY] != color) {
					continue;
				}
				
				queue.add(new Puyo(nextX, nextY));
				bombList.add(new Puyo(nextX, nextY));
				visited[nextX][nextY] = true;
				count++;
			}
		}
		
		if (count >= 4) {
			for (Puyo puyo: bombList) {
				map[puyo.x][puyo.y] = '.';
			}
			return true;
		}
		
		return false;
	}
	
	public static void puyoDown() {
		for (int j=0; j<M; j++) {
			Stack<Character> stack = new Stack<>();
			for (int i=0; i<N; i++) {
				if (map[i][j] != '.') {
					stack.push(map[i][j]);
				}
			}
			
			for (int i=N-1; i>=0; i--) {
				if (!stack.isEmpty()) {
					map[i][j] = stack.pop();
				}
				else {
					map[i][j] = '.';
				}
			}
		}
		
	}

}