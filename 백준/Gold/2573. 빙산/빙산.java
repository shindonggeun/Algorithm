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
	
	static int N;	// 행의 개수 (y 좌표)
	static int M;	// 열의 개수 (x 좌표)
	static int[][] map;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표 이동방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표 이동방향 배열 (좌, 우)
	
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
		
		int afterYear = 0;
		while(true) {
			int iceBurgCnt = countIceBurg();
			// 빙산이 다 녹은 경우(즉, 빙산 덩어리가 0개인 경우) 다 녹을때까지 분리되지 않은거므로 0 출력해야함 
			if(iceBurgCnt == 0) {
				afterYear = 0;
				break;
			}
			// 빙산 덩어리가 2개 이상이 된 경우 무한반복 빠져나옴
			else if(iceBurgCnt >= 2) {
				break;
			}
			
			meltBfs();	// 빙산 녹이는 작업 실행
			afterYear++;	// 년도 증가
			
		}
		System.out.println(afterYear);
		
	}
	
	// 빙산덩어리의 개수 구해주는 메서드
	public static int countIceBurg() {
		boolean[][] visited = new boolean[N][M];
		int cnt = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 해당 좌표가 방문하지 않은 상태면서 바다가 아닌 경우(빙산인 경우)
				if(!visited[i][j] && map[i][j] != 0) {
					dfs(i, j, visited);
					cnt++;
				}
			}
		}
		// 위의 해당 좌표들을 다 탐색하여 빙산 덩어리 개수 반환함
		return cnt;
	}
	
	// 깊이우선탐색
	public static void dfs(int startX, int startY, boolean[][] visited) {
		visited[startX][startY] = true;
		
		// 4가지 방향 탐색
		for(int i=0; i<4; i++) {
			int nextX = startX + dx[i];
			int nextY = startY + dy[i];
			// 탐색한 좌표가 (0, 0) ~ (M-1, N-1) 이외의 좌표인 경우 넘어감 
			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
				continue;
			}
			
			// 탐색한 좌표가 방문하지 않은 좌표면서 바다가 아닌 경우(빙산인 경우)
			if(!visited[nextX][nextY] && map[nextX][nextY] != 0) {
				dfs(nextX, nextY, visited);
			}
		}
	}
	
	// 빙산 녹이는 작업 (너비우선탐색)
	public static void meltBfs() {
		Queue<Position> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0) {
					queue.add(new Position(i, j));
					visited[i][j] = true;
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (M-1, N-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문했거나 현재 좌표가 0인경우는 녹일수 없다(음수값이 되면 안되므로)
				if(visited[nextX][nextY] || map[nowX][nowY] == 0) {
					continue;
				}
				
				
				if(map[nextX][nextY] == 0) {
					map[nowX][nowY]--;
				}
			}
		}
	}

}
