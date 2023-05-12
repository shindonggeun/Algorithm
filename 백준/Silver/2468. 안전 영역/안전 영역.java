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
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int maxHeight = 1;
	static int maxSafeArea = 0;
	
	// 4가지 방향 배열(상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 이동하는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 이동하는 방향 배열 (좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];	// (0, 0) ~ (N-1, N-1)
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}
		
		// 침수높이(최대 높이)부터 시작해서 높이를 1씩 줄여나가면서 탐색해나간다
		while(maxHeight-- > 0) {
			visited = new boolean[N][N];
			int tempArea = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 방문하지 않은 좌표면서 좌표의 높이가 해당 높이보다 큰 경우인 경우(침수되지 않은 영역)
					if(!visited[i][j] && (map[i][j] > maxHeight)) {
						bfs(i, j);
						tempArea++;
					}
				}
			}
			
			maxSafeArea = Math.max(maxSafeArea, tempArea);
		}
		System.out.println(maxSafeArea);

	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (N-1, N-1) 이외의 범위인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 침수되는 높이인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] <= maxHeight) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
			
		}
	}

}
