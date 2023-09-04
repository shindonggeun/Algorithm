import java.util.*;
import java.io.*;

public class Main {
	
	// 빙산의 좌표 정보를 저장하는 내부 클래스
	static class IceBurg {
		int x;
		int y;
		
		public IceBurg(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;	// 행의 개수
	static int M;	// 열의 개수
	static int[][] map;
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// [0][0] ~ [N-1][M-1]
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int afterYear = 0;	// 빙산이 분리되는 최초의 시간(년)
		while(true) {
			// 빙산 덩어리 개수 구해주는 작업
			int iceBurgBlockCount = getIceBurgBlockCount();
			
			// 빙산 덩어리 개수가 0이 되는 경우
			// 빙산이 다 녹아서 분리되지 않은거므로 0 출력해야함 (전부다 녹을때까지 두 덩어리 이상으로 분리되지 않으면 0 출력)
			if(iceBurgBlockCount == 0) {
				afterYear = 0;	// 분리되는 시간 0년
				break;	// 무한 반복 빠져나옴
			}
			
			// 빙산 덩어리 개수가 2개 이상이 되는 경우
			if(iceBurgBlockCount >= 2) {
				break;	// 무한반복 빠져나옴
			}
			
			
			meltBFS();	// 빙산 녹이는 작업 실시 (너비우선탐색)
			afterYear++;	// 빙산이 분리되는 최초의 시간 증가
		}
		
		System.out.println(afterYear);

	}
	
	// 빙산의 덩어리 개수를 구해주는 메서드
	public static int getIceBurgBlockCount() {
		boolean[][] visited = new boolean[N][M];	// 방문 배열
		int tempIceBurgBlockCount = 0;	// 빙산 덩어리 개수
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 방문하지 않은 좌표면서 동시에 바다(0)가 아닌 좌표(칸) 인 경우
				if(!visited[i][j] && map[i][j] != 0) {
					dfs(i, j, visited);	// 해당 좌표 및 방문배열 같이 parameter로 넘겨서 깊이우선탐색 실시
					tempIceBurgBlockCount++;	// 빙산 덩어리 개수 증가
				}
			}
		}
		
		return tempIceBurgBlockCount;	// 빙산 덩어리 개수 반환
	}
	
	// 깊이우선탐색 메서드 (빙산 덩어리 어디까지 연결되어 있는지 확인해주는 메서드)
	public static void dfs(int startX, int startY, boolean[][] visited) {
		visited[startX][startY] = true;	// 해당 시작좌표 방문처리
		
		// 4가지 방향 탐색
		for(int i=0; i<4; i++) {
			int nextX = startX + dx[i];
			int nextY = startY + dy[i];
			
			// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
				continue;	// 넘어감
			}
			
			// 탐색한 좌표가 이미 방문처리 됐거나 또는 바다(0)인 경우
			if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
				continue;	// 넘어감
			}
			
			dfs(nextX, nextY, visited);
			
		}
	}
	
	// 너비우선탐색 메서드 (빙산 주변 상하좌우 탐색해서 빙산덩어리 녹이는 작업을 해주는 메서드)
	public static void meltBFS() {
		Queue<IceBurg> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 해당 좌표가 바다(0)가 아닌 경우
				if(map[i][j] != 0) {
					queue.add(new IceBurg(i, j));
					visited[i][j] = true;
				}
			}
		}
		
		while(!queue.isEmpty()) {
			IceBurg now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 현재 좌표가 바다(0)인 경우 => 녹일 수 없음
				if(visited[nextX][nextY] || map[nowX][nowY] == 0) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 바다(0)인 경우 (즉, 바다 근처인 경우)
				if(map[nextX][nextY] == 0) {
					map[nowX][nowY]--;	// 빙산 녹여줌
				}
			}
		}
	}

}
