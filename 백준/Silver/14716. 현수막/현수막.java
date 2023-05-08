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

	static int M;	// y축(세로 길이)
	static int N;	// x축(가로 길이)
	static int[][] map;
	static boolean[][] visited;
	static int count = 0;
	// 8가지 방향 배열(상, 하, 좌, 우, 오른쪽 위(우 + 상), 오른쪽 아래(우 + 하), 왼쪽 위(좌 + 상), 왼쪽 아래(좌 + 하) 
	static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};	// x축 고정되어 있을 때 y좌표가 움직이는 방향 배열(상, 하)
	static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};	// x축 고정되어 있을 때 x좌표가 움직이는 방향 배열(좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];	// (0, 0) ~ (N-1, M-1)
		visited = new boolean[M][N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				// 방문처리가 안됐으면서 글자인 부분(1)이면 너비우선탐색 실시
				if(!visited[i][j] && map[i][j] == 1) {
					count++;	// 글자수 증가
					bfs(i, j);
				}
			}
		}
		System.out.println(count);
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 8가지 방향 탐색 (상, 하, 좌, 우, 대각선 4가지 방향)
			for(int i=0; i<8; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (N-1, M-1) 이외의 좌표인 경우 넘어감
				if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문했거나 글자가 아닌 부분(0)이면 넘어감
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
	}

}
