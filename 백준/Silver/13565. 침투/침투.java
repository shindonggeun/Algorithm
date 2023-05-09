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
	
	static int M;	// 세로 길이(y 좌표)
	static int N;	// 가로 길이(x 좌표)
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 이동하는 방향 배열(상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 이동하는 방향 배열(좌, 우)
	static boolean electronicOk;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];	// (0, 0) ~ (N-1, M-1)
		visited = new boolean[M][N];
		
		for(int i=0; i<M; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		// 위쪽에서 아래쪽으로 전류가 완벽하게 흐르면 성공이므로 
		// 위쪽을 의미하는 좌표 -> (0, 0) ~ (N-1, 0) 에서 전류가 잘 통하는 흰색(0)인 경우만 탐색
		for(int j=0; j<N; j++) {
			// 해당 좌표가 방문하지 않았으면서 전류가 잘 통하는 흰색(0)인 경우 
			if(!visited[0][j] && map[0][j] == 0) {
				bfs(0, j);
			}
		}
		
		if(electronicOk) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
		
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
					
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (N-1, M-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문했거나 전류가 통하지 않는 검은색(1)인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == 1) {
					continue;
				}
				
				// 위쪽에서 아래쪽으로 전류가 완벽하게 흐르면 성공
				// 아래쪽을 의미하는 좌표 -> (0, M-1) ~ (N-1, M-1) 이면서 전류가 잘 통하는 흰색이면 전류 잘 전달 됨 
				if(nextX == M-1 && map[nextX][nextY] == 0) {
					electronicOk = true;
					return;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
		
		
	}

}
