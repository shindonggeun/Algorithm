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
	static int M;
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축 고정되어 있을 때 y좌표가 움직이는 방향배열(상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축 고정되어 있을 때 x좌표가 움직이는 방향배열(좌, 우)
	static int pictureCount = 0;		// 그림 개수
	static int max = 0;		// 가장 넓은 그림의 넓이
	static int oneCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// (0,0) ~ (M-1, N-1)
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					oneCount = 0;
					pictureCount++;	// 그림 개수 증가
					bfs(i, j);
					max = Math.max(max, oneCount);
				}
			}
		}
		
		System.out.println(pictureCount);
		System.out.println(max);

	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		oneCount++;	// 그림 넓이 증가 (그림에 포함된 1의 개수가 그림의 넓이임)
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 상, 하, 좌, 우 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// (0,0) ~ (M-1, N-1) 이외의 다른 좌표를 탐색한 경우 넘어감
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문했거나 색칠이 안된 부분인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
				oneCount++;	// 그림 넓이 증가 (그림에 포함된 1의 개수가 그림의 넓이임)
			}
		}
	}

}
