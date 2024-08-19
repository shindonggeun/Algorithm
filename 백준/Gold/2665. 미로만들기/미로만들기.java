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
	static int[][] dist;
	static final int INF = Integer.MAX_VALUE;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dist = new int[N][N];
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = input.charAt(j) - '0';
				dist[i][j] = INF;
			}
		}
		
		zeroOneBfs(0, 0);
		
		System.out.println(dist[N-1][N-1]);

	}
	
	public static void zeroOneBfs(int startX, int startY) {
		Deque<Position> deque = new ArrayDeque<>();
		deque.add(new Position(startX, startY));
		dist[startX][startY] = 0;
		
		while (!deque.isEmpty()) {
			Position now = deque.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				if (dist[nowX][nowY] >= dist[nextX][nextY]) {
					continue;
				}
				
				if (map[nextX][nextY] == 1) {
					dist[nextX][nextY] = dist[nowX][nowY];
					deque.addFirst(new Position(nextX, nextY));
				}
				else if (map[nextX][nextY] == 0) {
					dist[nextX][nextY] = dist[nowX][nowY] + 1;
					deque.addLast(new Position(nextX, nextY));
				}
			}
		}
	}

}