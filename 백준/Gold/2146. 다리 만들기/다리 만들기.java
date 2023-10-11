import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표의 정보를 저장해주는 내부 클래스
	static class Position {
		int x;
		int y;
		int distance;
		
		public Position(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int islandCount;	// 각 섬에 고유한 번호 부여해주는 변수
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];	// [0][0] ~ [N-1][N-1]
		visited = new boolean[N][N];
		islandCount = 1;	// 각 섬에 고유한 번호 부여
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int islandNum = 1;	// 섬의 고유 번호를 부여하기 위한 변수
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 탐색한 좌표가 육지면서 동시에 방문하지 않은 좌표인 경우
				if(map[i][j] == 1 && !visited[i][j]) {
					// 섬의 육지에 고유 번호 부여하는 메서드 호출 (너비우선탐색)
					labelIsland(i, j, islandNum);	
					islandNum++;	// 섬의 고유 번호 증가 (다른 섬과 구별하기 위해)
				}
			}
		}
		
		int minDistance = Integer.MAX_VALUE;	// 다리를 잇는 최소 거리 변수 일단 최대값으로 설정
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 바다(0)가 아닌 경우 (즉, 섬인 경우)
				if(map[i][j] != 0) {
					visited = new boolean[N][N];	// 방문배열 초기화
					// 다른 섬과 다리를 놓아서 최단 거리의 다리를 찾는 메서드 호출
					int bridgeDistance = findBridge(i, j);	
					
					// 다른 섬과 다리를 잇는 거리가 -1인 경우 (다른 섬과 다리 이을 수 없는 경우)
					if(bridgeDistance == -1) {
						continue;	// 넘어감 (다른 섬 탐색할 수 있게끔)
					}
					
					// 다리를 잇는 최소 거리가 현재 다른 섬과 다리를 잇는 거리보다 큰 경우
					if(minDistance > bridgeDistance) {
						minDistance = bridgeDistance;	// 다리를 잇는 최소 거리 갱신
					}
				}
			}
		}
		
		System.out.println(minDistance-1);
	}
	
	// 섬의 육지에 고유한 번호를 부여하는 메서드 (너비우선탐색 이용)
	public static void labelIsland(int startX, int startY, int islandNumber) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));
		visited[startX][startY] = true;
		map[startX][startY] = islandNumber;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 바다(0)거나 또는 이미 방문한 좌표인 경우
				if(map[nextX][nextY] == 0 || visited[nextX][nextY]) {
					continue;	// 넘어감
				}
				
				queue.add(new Position(nextX, nextY, 0));
				visited[nextX][nextY] = true;
				map[nextX][nextY] = islandNumber;
			}
		}
	}
	
	// 다른 섬과 다리를 놓아서 최단 거리의 다리를 찾는 메서드 (너비우선탐색 이용)
	public static int findBridge(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));
		int islandNum = map[startX][startY];	// 시작지점의 섬번호 추출
		visited[startX][startY] = true;	// 해당섬의 시작지점 방문처리
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			// 현재 좌표가 바다(0)가 아니면서 동시에 섬의 번호가 다른 경우 => 즉, 이미 다른섬에 도달한 경우
			if(map[nowX][nowY] != 0 && map[nowX][nowY] != islandNum) {
				return nowDistance;	// 현재까지의 거리 반환
			}
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 시작지점의 섬의 번호와 동일한 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == islandNum) {
					continue;	// 넘어감
				}
				
				visited[nextX][nextY] = true;	
				queue.add(new Position(nextX, nextY, nowDistance + 1));
			}
		}
		
		// 위의 너비우선탐색 다 했는데도 다른 섬과 다리를 이을 수 없는 경우 -1 반환
		return -1;
	}

}
