import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		int move;
		
		public Position(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}
	
	static int N;
	static int M;
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
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		
		K = Integer.parseInt(br.readLine());
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			
			for (int r = x - d; r <= x + d; r++) {
                if (r >= 0 && r < N) {
                    int leftY = y - (d - Math.abs(r - x));
                    int rightY = y + (d - Math.abs(r - x));
                    if (leftY >= 0 && leftY < M) {
                        map[r][leftY] = 1;
                        visited[r][leftY] = true;
                    }
                    if (rightY >= 0 && rightY < M) {
                        map[r][rightY] = 1;
                        visited[r][rightY] = true;
                    }
                }
            }
		}
		
		int result = bfs();
		
		if (result == -1) {
			System.out.println("NO");
		}
		else {
			System.out.println("YES");
			System.out.println(result);
		}
	}
	
	public static int bfs() {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(0, 0, 0));
		visited[0][0] = true;
		
		while (!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowMove = now.move;
			
			if (nowX == N-1 && nowY == M-1) {
				return nowMove;
			}
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if (visited[nextX][nextY] || map[nextX][nextY] == 1) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY, nowMove + 1));
				visited[nextX][nextY] = true;
			}
		}
		
		return -1;
	}

}