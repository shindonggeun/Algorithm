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
	
	static int M; // 가로 길이
	static int N; // 세로 길이
	static int K; // 배추가 심어져 있는 위치의 개수
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M]; // [0][0] ~ [N-1][M-1]
			visited = new boolean[N][M];
			
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[y][x] = 1;
			}
			
			int wormCount = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (!visited[i][j] && map[i][j] == 1) {
						bfs(i, j);
						wormCount++;
					}
				}
			}
			
			sb.append(wormCount).append("\n");
		}
		
		System.out.print(sb);
		
	}
	
	public static void bfs(int startX, int startY) {
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
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if (visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
	}

}