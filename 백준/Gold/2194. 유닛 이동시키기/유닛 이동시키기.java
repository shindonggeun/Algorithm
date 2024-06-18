import java.util.*;
import java.io.*;

public class Main {
	
	static class Unit {
		int x;
		int y;
		int move;
		
		public Unit(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}
	
	static int N;
	static int M;
	static int A;
	static int B;
	static int K;
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			map[x][y] = 1;
		}
		
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken()) - 1;
		int startY = Integer.parseInt(st.nextToken()) - 1;
		
		st = new StringTokenizer(br.readLine());
		int endX = Integer.parseInt(st.nextToken()) - 1;
		int endY = Integer.parseInt(st.nextToken()) - 1;
		
		int result = bfs(startX, startY, endX, endY);
		
		System.out.println(result);
	}
	
	public static int bfs(int startX, int startY, int endX, int endY) {
		Queue<Unit> queue = new LinkedList<>();
		queue.add(new Unit(startX, startY, 0));
		visited[startX][startY] = true;
		
		while (!queue.isEmpty()) {
			Unit now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowMove = now.move;
			
			if (nowX == endX && nowY == endY) {
				return nowMove;
			}
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if (visited[nextX][nextY]) {
					continue;
				}
				
				
				if (isPossible(nextX, nextY)) {
					visited[nextX][nextY] = true;
					queue.add(new Unit(nextX, nextY, nowMove + 1));
				}
			}
		}
		
		return -1;
	}
	
	public static boolean isPossible(int x, int y) {
		for (int i=x; i<x+A; i++) {
			for (int j=y; j<y+B; j++) {
				if (i < 0 || j < 0 || i >= N || j >= M) {
					return false;
				}
				
				if (map[i][j] == 1 || visited[x][y]) {
					return false;
				}
			}
		}
		
		return true;
	}

}