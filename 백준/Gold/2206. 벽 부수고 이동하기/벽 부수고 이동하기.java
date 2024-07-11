import java.util.*;
import java.io.*;

public class Main {
	
	// 해당 좌표의 정보를 담고 있는 내부 클래스
	static class Position {
		int x;
		int y;
		int distance; // 해당 좌표까지 이동한 거리
		boolean crack; // 벽을 부쉈는지 여부
		
		public Position(int x, int y, int distance, boolean crack) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.crack = crack;
		}
	}
	
	static int N; // 세로 길이
	static int M; // 가로 길이
	static int[][] map;
	static boolean[][][] visited; // 3차원 방문 배열, [0] = 벽을 안부순 상태, [1] = 벽을 부순 상태
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		visited = new boolean[2][N][M]; // [0] = 벽을 안부순 상태, [1] = 벽을 부순 상태
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		int minDistance = bfs(0, 0); // [0][0] 좌표에서부터 시작하여 너비우선탐색 실시
		System.out.println(minDistance);

	}
	
	// [0][0]부터 [N-1][M-1] 위치까지의 최단 경로를 구해주는 너비우선탐색 메서드
	public static int bfs(int startX, int startY) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 1, false)); // 큐에 시작좌표 정보 저장
		visited[0][startX][startY] = true; // 벽을 안부순상태의 시작좌표 방문처리
		
		while (!queue.isEmpty()) {
			// 큐에서 현재 좌표 정보 뽑아냄
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			boolean nowCrack = now.crack;
			
			// 현재 좌표가 도착지점에 도달한 경우
			if (nowX == N-1 && nowY == M-1) {
				return nowDistance; // 현재까지의 이동 거리 반환
			}
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 벽(1)인 경우
				if (map[nextX][nextY] == 1) {
					// 현재 벽을 부수지 않은 상태인 경우
					if (!nowCrack) {
						// 탐색한 좌표가 방문하지 않은 좌표인 경우 (벽을 부순상태의 탐색한 좌표)
						// 즉, 벽 부숴서 이동 가능
						if (!visited[1][nextX][nextY]) {
							// 큐에 탐색한 좌표 정보 저장 (현재 벽 부숨)
							queue.add(new Position(nextX, nextY, nowDistance + 1, true));
							visited[1][nextX][nextY] = true; // 벽을 부순 상태로 탐색한 좌표 방문 처리
						}
					}
				}
				// 탐색한 좌표가 이동가능한 좌표(0)인 경우
				else {
					// 현재 벽을 부수지 않은 상태인 경우
					if (!nowCrack) {
						// 탐색한 좌표가 방문하지 않은 좌표인 경우 (벽을 부수지 않은 상태의 탐색한 좌표)
						// 즉, 그냥 이동 가능
						if (!visited[0][nextX][nextY]) {
							// 큐에 탐색한 좌표 정보 저장 (벽 현재까지 부수지 않음)
							queue.add(new Position(nextX, nextY, nowDistance + 1, false));
							visited[0][nextX][nextY] = true; // 탐색한 좌표 방문 처리
						}
					}
					// 현재 벽을 부순 상태인 경우
					else {
						// 탐색한 좌표가 방문하지 않은 좌표인 경우 (벽을 부순 상태의 탐색한 좌표)
						if (!visited[1][nextX][nextY]) {
							// 큐에 탐색한 좌표 정보 저장 (벽 현재까지 부쉈음)
							queue.add(new Position(nextX, nextY, nowDistance + 1, true));
							visited[1][nextX][nextY] = true; // 탐색한 좌표 방문 처리
						}
					}
				}
			}
		}
		
		// 위에 너비우선탐색 실시했는데도 도착지점까지 도달하지 못한 경우 -1 반환
		return -1;
	}

}