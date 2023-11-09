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
	static char[][] map;
	static boolean[][] visited;
	// 4가지 방향을 나타내는 방향배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new char[N][N];	// (0, 0) ~ (N-1, N-1)
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		int normalColorCount = 0;
		// 적록색약이 아닌경우 실시하는 과정
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					bfs(i, j);
					normalColorCount++;
				}
			}
		}
		
		// 적록색약인 경우 너비우선탐색 실시하는 과정
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못하므로
				// 해당 좌표가 초록색인 경우
				if(map[i][j] == 'G') {
					map[i][j] = 'R';	// 빨간색으로 바꿔줌
				}
			}
		}
		
		
		int abnormalColorCount = 0;
		visited = new boolean[N][N];
		// 적록색약인 경우 너비우선탐색 실시하는 과정
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					bfs(i, j);
					abnormalColorCount++;
				}
			}
		}
		
		System.out.println(normalColorCount + " " + abnormalColorCount);
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		char tempColor = map[startX][startY];
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// (0, 0) ~ (N-1, N-1) 이외의 좌표를 탐색한 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				// 이미 방문한 좌표거나 탐색한 좌표가 인접한 컬러의 색상과 다른 경우
				if(visited[nextX][nextY] || map[nextX][nextY] != tempColor) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
	}

}
