import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x, y, distance;
		
		public Position(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	static int N;	// y축을 의미 (세로 길이)
	static int M; 	// x축을 의미 (가로 길이)
	static int[][] map;
	static boolean[][] visited;
	static int max = Integer.MIN_VALUE;
	// 8가지 방향 (상, 하, 좌, 우, 오른쪽 위(우 + 상), 오른쪽 아래 (우 + 하), 왼쪽 위 (좌 + 상), 왼쪽 아래 (좌 + 하)
	static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};	// x축 고정되어 있을 때 y좌표가 이동하는 방향배열 (상, 하)
	static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};	// y축 고정되어 있을 때 x좌표가 이동하는 방향배열 (좌, 우)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 모든 빈칸(0)을 너비우선탐색 해준다
				if(map[i][j] == 0) {
					visited = new boolean[N][M];	// 탐색할때마다 해당좌표 방문여부 배열 초기화시켜줌(중요!!)
					int result = bfs(i, j);
					max = Math.max(max, result); 
				}
			}
		}
		System.out.println(max);
	}
	
	public static int bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int distance = now.distance;
			
			// 8가지 방향 탐색
			for(int i=0; i<8; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// (0, 0) ~ (M-1, N-1) 이외의 좌표인 경우 넘어감
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				// 방문한 좌표인경우 넘어감
				if(visited[nextX][nextY]) { 
					continue;
				}
				// 아기상어가 있는 칸인 경우 해당 거리를 return한다
				if(map[nextX][nextY] == 1) {
					return distance + 1;
				}
				
				
				queue.add(new Position(nextX, nextY, distance + 1));
				visited[nextX][nextY] = true;
			}
		}
		return 0;
	}

}
