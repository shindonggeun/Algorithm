import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보를 저장하는 내부 클래스
	static class Position {
		int x;
		int y;
		int distance;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Position(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	static int N;	// 지도 크기
	static int[][] map;	// 맵
	static boolean[][] visited;	// 방문배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int minBridgeDistance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		map = new int[N][N];	// [0][0] ~ [N-1][N-1]
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int islandNum = 1;	// 섬번호 1번부터 시작
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 해당 좌표가 육지(1)면서 동시에 방문처리 안된 좌표인 경우
				if(map[i][j] == 1 && !visited[i][j]) {
					islandLabeling(i, j, islandNum);	// 섬번호 매기기 실시
					islandNum++;	// 섬 번호 증가
				}
			}
		}
				
		minBridgeDistance = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 해당 좌표가 바다(0)가 아닌 경우 (즉, 육지 -> 섬인경우)
				if(map[i][j] != 0) {
					visited = new boolean[N][N];	// 방문 배열 초기화 및 생성
					int bridgeDistance = findBridge(i, j);	// 시작 좌표에서 부터 다른 섬까지의 다리 길이 찾는 메서드 호출
					
					// 다른 섬과 다리를 잇는 거리가 -1인 경우 (다른 섬과 다리 이을 수 없는 경우)
					if(bridgeDistance == -1) {
						continue;	// 넘어감 (다른 섬 탐색할 수 있게끔)
					}
					
					// 다리를 잇는 최소 거리가 현재 다른 섬과 다리를 잇는 거리보다 큰 경우
					if(minBridgeDistance > bridgeDistance) {
						minBridgeDistance = bridgeDistance;	// 다리를 잇는 최소 거리 갱신
					}
				}
			}
		}
		
		System.out.println(minBridgeDistance);

	}
	
	// 섬 번호를 매기는 메서드 (너비우선탐색 알고리즘 이용)
	public static void islandLabeling(int startX, int startY, int islandNum) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));	// 시작 좌표 정보 큐에 저장
		map[startX][startY] = islandNum;
		visited[startX][startY] = true;	// 시작 좌표 방문 처리
		
		while(!queue.isEmpty()) {
			// 현재 좌표 정보 뽑아냄
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
				
				// 탐색한 좌표가 이미 방문했거나 또는 바다(0)인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;	// 넘어감
				}
				
				queue.add(new Position(nextX, nextY));	// 탐색한 좌표 정보 큐에 저장
				visited[nextX][nextY] = true;	// 탐색한 좌표 방문 처리
				map[nextX][nextY] = islandNum;	// 탐색한 좌표에 섬 번호 부여
			}
		}
	}
	
	// 다른 섬과 다리 이어주는 메서드 (너비우선탐색 알고리즘 메서드 이용)
	public static int findBridge(int startX, int startY) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));	// 시작 좌표 정보 큐에 저장
		int islandNum = map[startX][startY];	// 시작 좌표의 섬 번호 추출
		visited[startX][startY] = true;	// 해당 섬의 시작좌표 방문처리
		
		// 너비우선탐색 알고리즘 이용
		while(!queue.isEmpty()) {
			// 현재 좌표 정보 뽑아냄
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			// 현재 좌표가 바다(0)가 아니면서 동시에 현재 섬번호가 아닌 경우 (즉, 다른 섬에 도달한 경우 -> 다리 놓여짐)
			if(map[nowX][nowY] != 0 && map[nowX][nowY] != islandNum) {
				// 현재까지의 다리 길이 반환 (-1 해줘야 올바른 다리 길이 반환됨)
				// 다른 섬 육지까지 포함해서 다리 길이가 놓여진 것이므로
				return nowDistance-1;	
			}
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 현재 섬번호와 같은 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == islandNum) {
					continue;	// 넘어감
				}
				
				queue.add(new Position(nextX, nextY, nowDistance + 1));
				visited[nextX][nextY] = true;	// 탐색한 좌표 방문 처리
			}
			
		}
		
		// 너비우선탐색을 다 실시했는데도 두 섬을 이을 수 없는 경우 -1 반환
		return -1;	
	}

}
