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
	
	static int N;	// 세로 (y 좌표)
	static int M;	// 가로 (x 좌표)
	static int T;	// 경계값 T
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열(상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열(좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int R = Integer.parseInt(st.nextToken());
				int G = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				int sum = R + G + B;
				map[i][j] = sum / 3;
			}
		}
		T = Integer.parseInt(br.readLine());	// 경계값 입력
		int pixelCount = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] >= T && !visited[i][j]) {
					pixelCount++;
					bfs(i, j);
				}
			}
		}
		
		System.out.println(pixelCount);
		

	}
	
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
				// 탐색한 좌표가 (0, 0) ~ (M-1, N-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				// 탐색한 좌표가 이미 방문했거나 경계값 T보다 작은 경우
				if(visited[nextX][nextY] || map[nextX][nextY] < T) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
	}

}
