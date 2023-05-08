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
	
	static int N;	// 세로의 크기 (y축 좌표)
	static int M;	// 가로의 크기 (x축 좌표)
	static int[][] map;
	static boolean[][] visited;
	static int[][] resultDistance;
	// 4가지 방향 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
		visited = new boolean[N][M];
		resultDistance = new int[N][M];
		int startX = 0;
		int startY = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 목표지점을 찾은 경우 (목표지점부터 출발하면 각 지점까지의 거리를 계산할 수 있음)
				// 즉 각 지점에서 목표지점까지의 거리 == 목표지점부터 시작해서 각 지점까지의 거리 
				if(map[i][j] == 2) {
					startX = i;
					startY = j;
				}
			}
		}
		
		bfs(startX, startY);	// 시작지점부터 각 지점까지의 거리를 너비우선탐색으로 계산
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 방문 안했으면서 갈수있는 땅(1)인경우
				if(!visited[i][j] && map[i][j] == 1) {
					sb.append("-1" + " ");
				}
				// 위의 경우를 제외한 나머지는 각 지점에서 목표지점까지의 계산한 거리 출력하면 됨
				else {
					sb.append(resultDistance[i][j] + " ");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
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
				
				// (0, 0) ~ (M-1, N-1)
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
				resultDistance[nextX][nextY] = resultDistance[nowX][nowY] + 1;
			}
		}
	}

}
