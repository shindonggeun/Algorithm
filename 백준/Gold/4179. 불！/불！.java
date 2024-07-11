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
	
	static int R;
	static int C;
	static char[][] map;
	static boolean[][] visited;
	static Queue<Position> fireQueue;
	static Queue<Position> jihunQueue;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C]; // [0][0] ~ [R-1][C-1]
		visited = new boolean[R][C];
		
		fireQueue = new LinkedList<>();
		jihunQueue = new LinkedList<>();
		
		for (int i=0; i<R; i++) {
			String input = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'F') {
					fireQueue.add(new Position(i, j));
					visited[i][j] = true;
				}
				else if (map[i][j] == 'J') {
					jihunQueue.add(new Position(i, j));
					visited[i][j] = true;
				}
			}
		}
		
		int escapeTime = bfs();
		
		System.out.println(escapeTime == -1 ? "IMPOSSIBLE" : escapeTime);

	}
	
	public static int bfs() {
		int time = 0;
		
		while (!jihunQueue.isEmpty()) {
			// 1. 불 먼저 퍼트리기
			int fireCount = fireQueue.size();
			
			for (int i=0; i<fireCount; i++) {
				Position fire = fireQueue.poll();
				int nowX = fire.x;
				int nowY = fire.y;
				
				for (int k=0; k<4; k++) {
					int nextX = nowX + dx[k];
					int nextY = nowY + dy[k];
					
					if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
						continue;
					}
					
					if (visited[nextX][nextY] || map[nextX][nextY] != '.') {
						continue;
					}
					
					map[nextX][nextY] = 'F';
					visited[nextX][nextY] = true;
					fireQueue.add(new Position(nextX, nextY));
				}
			}
			

			// 2. 지훈이 이동하기
			int jihunCount = jihunQueue.size();
			
			for (int i=0; i<jihunCount; i++) {
				Position jihun = jihunQueue.poll();
				int nowX = jihun.x;
				int nowY = jihun.y;
				
				for (int k=0; k<4; k++) {
					int nextX = nowX + dx[k];
					int nextY = nowY + dy[k];
					
					if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
						return time + 1;
					}
					
					if (visited[nextX][nextY] || map[nextX][nextY] != '.') {
						continue;
					}
					
					jihunQueue.add(new Position(nextX, nextY));
					visited[nextX][nextY] = true;
				}
			}
			
			
			time++;
		}
		
		return -1;
	}

}