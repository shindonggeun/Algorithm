import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보 및 안전 거리 정보를 담고 있는 내부 클래스
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
	
	static int N; // 세로 크기
	static int M; // 가로 크기
	static int[][] map;
	static boolean[][] visited; // 방문 배열
	// 8가지 방향 배열 (상, 상 + 우, 우, 하 + 우, 하, 하 + 좌, 좌, 상 + 좌)
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int safeMaxDistance = 0; // 안전 거리의 최대값
		
		// 완전 탐색 이용
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				// 해당 좌표가 빈칸(0)인 경우
				if (map[i][j] == 0) {
					visited = new boolean[N][M]; // 방문 배열 초기화
					int safeDistance = bfs(i, j); // 해당 좌표에서부터 너비우선탐색 실시
					safeMaxDistance = Math.max(safeMaxDistance, safeDistance); // 안전 거리의 최대값 갱신
				}
			}
		}
		
		System.out.println(safeMaxDistance);
	}
	
	// 해당 칸에서부터 가장 가까운 아기상어가 있는 칸까지의 거리 (안전거리)를 구하는 너비우선탐색 메서드
	public static int bfs(int startX, int startY) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0)); // 시작좌표 및 안전거리 정보 큐에 저장
		visited[startX][startY] = true; // 시작 좌표 방문처리
		
		while (!queue.isEmpty()) {
			// 큐에 저장된 현재 좌표 정보 및 안전거리 정보 뽑아냄
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			// 현재 좌표가 아기 상어(1)가 있는 칸인 경우
			if (map[nowX][nowY] == 1) {
				return nowDistance; // 현재까지의 안전거리 반환
			}
			
			// 8가지 방향 탐색
			for (int i=0; i<8; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우 
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 이미 방문처리 되어 있는 경우
				if (visited[nextX][nextY]) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 큐에 탐색한 좌표 정보 및 안전거리 정보 저장
				queue.add(new Position(nextX, nextY, nowDistance + 1));
				visited[nextX][nextY] = true; // 탐색한 좌표 방문처리
			}
		}
		
		// 위의 너비우선탐색 과정 실시했는데도 아기상어가 있는 칸 (안전거리) 찾지 못하면 0 반환
		return 0;
	}

}