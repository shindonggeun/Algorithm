import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x, y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int H;	// 높이 (y좌표)
	static int W;	// 너비 (x좌표)
	static char[][] map;
	static boolean[][] visited;
	// 4가지 방향 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열(상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열(좌, 우)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];	// (0, 0) ~ (W-1, H-1)
			visited = new boolean[H][W];
			
			for(int i=0; i<H; i++) {
				String input = br.readLine();
				for(int j=0; j<W; j++) {
					map[i][j] = input.charAt(j);
				}
			}
			
			int sheepGroup = 0;
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					// 양(#)이면서 방문하지 않은 좌표인 경우 너비우선탐색 실시
					if(map[i][j] == '#' && !visited[i][j]) {
						sheepGroup++;
						bfs(i, j);
					}
				}
			}
			
			sb.append(sheepGroup).append("\n");
		}
		
		System.out.print(sb);

	}
	
	// 너비우선탐색 메서드
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색(상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (W-1, H-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
					continue;
				}
				// 탐색한 좌표가 이미 방문했거나 풀(.)인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == '.') {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
		
	}

}
