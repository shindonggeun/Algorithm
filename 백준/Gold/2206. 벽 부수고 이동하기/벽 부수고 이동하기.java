import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		int distance;
		boolean crack;
		
		public Position(int x, int y, int distance, boolean crack) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.crack = crack;
		}
	}
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][][] visited;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		visited = new boolean[2][N][M]; 
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		int minDistance = bfs(0, 0);
		System.out.println(minDistance);

	}
	
	public static int bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 1, false));
		visited[0][startX][startY] = true;
		
		while (!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			boolean nowCrack = now.crack;
			
			if (nowX == N-1 && nowY == M-1) {
				return nowDistance;
			}
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if (map[nextX][nextY] == 1) {
					if (!nowCrack) {
						if (!visited[1][nextX][nextY]) {
							queue.add(new Position(nextX, nextY, nowDistance + 1, true));
							visited[1][nextX][nextY] = true;
						}
					}
				}
				else {
					if (!nowCrack) {
						if (!visited[0][nextX][nextY]) {
							queue.add(new Position(nextX, nextY, nowDistance + 1, false));
							visited[0][nextX][nextY] = true;
						}
					}
					else {
						if (!visited[1][nextX][nextY]) {
							queue.add(new Position(nextX, nextY, nowDistance + 1, true));
							visited[1][nextX][nextY] = true;
						}
					}
				}
			}
		}
		
		return -1;
	}

}