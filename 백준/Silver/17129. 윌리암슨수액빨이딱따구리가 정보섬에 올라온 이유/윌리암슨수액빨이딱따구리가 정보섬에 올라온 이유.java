import java.util.*;
import java.io.*;

public class Main {

	static class Position {
		int x, y;
		int distance;
		
		public Position(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	static int N;	// 행
	static int M;	// 열
	static int[][] map;
	static boolean[][] visited;
	static int minDistance = Integer.MAX_VALUE;
	// 4가지 방향을 나타내는 방향배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
		// 시작좌표(2)
		int startX = 0;
		int startY = 0;
		// 청국장(3) 있는 좌표
		int endX_1 = 0;
		int endY_1 = 0;
		// 스시(4)가 있는 좌표
		int endX_2 = 0;
		int endY_2 = 0;
		// 맥엔치즈(5)가 있는 좌표
		int endX_3 = 0;
		int endY_3 = 0;
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
				if(map[i][j] == 2) {
					startX = i;
					startY = j;
				}
				else if(map[i][j] == 3) {
					endX_1 = i;
					endY_1 = j;
				}
				else if(map[i][j] == 4) {
					endX_2 = i;
					endY_2 = j;
				}
				else if(map[i][j] == 5) {
					endX_3 = i;
					endY_3 = j;
				}
			}
		}
		
		// 너비우선탐색 실시
		bfs(startX, startY, endX_1, endY_1);
		bfs(startX, startY, endX_2, endY_2);
		bfs(startX, startY, endX_3, endY_3);
		
		if(minDistance == Integer.MAX_VALUE) {
			System.out.println("NIE");
		}
		else {
			System.out.println("TAK");
			System.out.println(minDistance);
		}
	}
	
	public static void bfs(int startX, int startY, int endX, int endY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));
		visited = new boolean[N][M];	// (0, 0) ~ (M-1, N-1)
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			if(nowX == endX && nowY == endY) {
				minDistance = Math.min(minDistance, nowDistance);
				return;
			}
			
			// 4가지 방향 탐색(상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// (0, 0) ~ (M-1, N-1) 이외의 좌표를 탐색한 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 이미 방문한 좌표거나 탐색한 좌표가 장애물(1)인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == 1) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY, nowDistance+1));
				visited[nextX][nextY] = true;
			}
		}
	}

}
