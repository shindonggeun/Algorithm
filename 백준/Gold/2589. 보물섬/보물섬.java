import java.util.*;
import java.io.*;

public class Main {
	
	// 보물이 묻혀있는 좌표 정보를 담고 있는 내부 클래스
	static class Treasure {
		int x;
		int y;
		int distance; // 거리
		
		public Treasure(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	static int L; // 세로 크기
	static int W; // 가로 크기
	static char[][] map; // 보물 지도
	static boolean[][] visited; // 방문 배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new char[L][W]; // [0][0] ~ [L-1][W-1]
		
		for (int i=0; i<L; i++) {
			String input = br.readLine();
			for (int j=0; j<W; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		int resultDistance = 0; // 보물이 묻혀 있는 두 곳 간의 최단 거리 (결과값)
		
		for (int i=0; i<L; i++) {
			for (int j=0; j<W; j++) {
				// 해당 좌표가 육지('L')인 경우
				if (map[i][j] == 'L') {
					visited = new boolean[L][W]; // 방문 배열 초기화
					int distance = bfs(i, j); // 해당 좌표에서부터 너비우선탐색 실시
					// 보물이 묻혀 있는 두 곳 간의 최단 거리 갱신 (문제에서는 최단 거라고 표현되어 있지만 가장 긴 길이 찾으면 된다)
					resultDistance = Math.max(resultDistance, distance);
				}
			}
		}
		
		System.out.println(resultDistance);
		
	}
	
	// 보물이 묻혀 있는 두 곳 사이의 거리를 찾도록 해주는 너비우선탐색 메서드
	public static int bfs(int startX, int startY) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Treasure> queue = new LinkedList<>();
		queue.add(new Treasure(startX, startY, 0)); // 큐에 보물의 시작 좌표 및 거리 정보 저장
		visited[startX][startY] = true; // 시작 좌표 방문 처리
		
		int minDistance = 0; // 보물이 묻혀 있는 두 곳 사이의 최단 거리
		
		while (!queue.isEmpty()) {
			// 큐에 저장된 현재 보물 관련 정보 뽑아냄
			Treasure now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			// 4가지 방햠 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [L-1][W-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= L || nextY >= W) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 이미 방문처리 됐거나 또는 바다('W')인 경우
				if (visited[nextX][nextY] || map[nextX][nextY] == 'W') {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 큐에 탐색한 좌표 정보 및 거리 정보 저장
				queue.add(new Treasure(nextX, nextY, nowDistance + 1));
				visited[nextX][nextY] = true; // 탐색한 좌표 방문 처리
				minDistance = nowDistance + 1; // 보물이 묻혀있는 두 곳 사이의 최단 거리 갱신 (현재까지 탐색한 거리 + 1)
			}
		}
		
		// 위의 너비우선탐색 완료됐으면 보물 최단 거리 반환
		return minDistance;
	}
	


}