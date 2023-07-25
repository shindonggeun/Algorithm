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
	
	static int N;	// 세로 길이 (y 좌표)
	static int M;	// 가로 길이 (x 좌표)
	static char[][] map;
	static boolean[][] visited;
	static int minDistance;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];	// (0, 0) ~ (M-1, N-1)
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		// 브루트포스 알고리즘 이용 (모든 좌표 완전탐색)
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 해당 맵의 좌표가 육지('L')인 경우
				if(map[i][j] == 'L') {
					bfs(i, j);	// 해당 좌표부터 너비우선 탐색 실시
				}
			}
		}
		
		System.out.println(minDistance);
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));
		visited = new boolean[N][M];
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (M-1, N-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 탐색한 좌표가 바다거나 또는 이미 방문한 좌표인 경우
				if(map[nextX][nextY] == 'W' || visited[nextX][nextY]) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY, nowDistance + 1));
				visited[nextX][nextY] = true;
			}
			
			minDistance = Math.max(minDistance, nowDistance);	// 위의 4가지 방향 탐색 끝나면 최단거리 갱신
		}
	}

}
