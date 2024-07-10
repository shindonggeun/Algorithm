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
	
	static int N; // 세로 크기
	static int M; // 가로 크기
	static int[][] map;
	static boolean[][] visited;
	static int[][] resultMap;
	static Queue<Position> queue;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		resultMap = new int[N][M];
		queue = new LinkedList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				resultMap[i][j] = map[i][j];
				if (map[i][j] == 2) {
					queue.add(new Position(i, j));
					resultMap[i][j] = 0;
					visited[i][j] = true;
				}
			}
		}
		
		bfs();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					sb.append(-1).append(" ");
				}
				else {
					sb.append(resultMap[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void bfs() {
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
				resultMap[nextX][nextY] = resultMap[nowX][nowY] + 1;
			}
		}
	}

}