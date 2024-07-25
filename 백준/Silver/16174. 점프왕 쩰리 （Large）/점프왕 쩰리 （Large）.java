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
	// 2가지 방향 배열 (우, 하)
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs() == true ? "HaruHaru" : "Hing");
	}
	
	public static boolean bfs() {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(0, 0));
		
		while (!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowMove = map[nowX][nowY];
			
			if (nowX == N-1 && nowY == N-1) {
				return true;
			}
			
			for (int i=0; i<2; i++) {
				int nextX = nowX + dx[i] * nowMove;
				int nextY = nowY + dy[i] * nowMove;
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				if (visited[nextX][nextY]) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
		
		return false;
	}

}
