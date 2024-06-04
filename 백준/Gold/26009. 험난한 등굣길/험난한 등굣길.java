import java.util.*;
import java.io.*;

public class Main {

	// 좌표 정보 및 움직인 횟수 정보를 담고 있는 내부 클래스
	static class Position {
		int x;
		int y;
		int move;

		public Position(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}

	static int N; // 행의 개수
	static int M; // 열의 개수
	static int K; // 정체 구역의 수
	static int[][] map;
	static boolean[][] visited; // 방문 배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	// 4가지 대각선 방향 배열 (시계 방향 순서로 -> 오른쪽 아래, 왼쪽 아래, 왼쪽 위, 오른쪽 위
	static int[] diagonalX = {1, 1, -1, -1};
	static int[] diagonalY = {1, -1, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		
		K = Integer.parseInt(br.readLine());
		
		// 정체 구역 입력받기
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			
			trafficSetUp(x, y, d); // 정체구역 설정하도록 메서드 호출
		}
		
		int result = bfs(); // 시작지에서 도착지까지 최소 이동 횟수를 구하기 위해 너비우선탐색 알고리즘 실행
		
		// 수업을 들으러 갈 수 없는 경우 (도착지에 도달하지 못하는 경우)
		if (result == -1) {
			System.out.println("NO");
		}
		// 도착지에 도달할 수 있는 경우
		else {
			System.out.println("YES");
			System.out.println(result);
		}
	}

	// 정체구역을 설정해주는 메서드
	public static void trafficSetUp(int centerX, int centerY, int radius) {
		map[centerX][centerY] = -1; // 정체 구역의 중심을 정체구역으로 표시
		int remainDistance = radius; // 현재 정체 구간 남아있는 거리

		// 중심으로부터 거리 radius 이내의 모든 칸을 탐색
		while (remainDistance >= 0) {
			int deltaX = remainDistance; // x 방향으로 이동할 거리
			int deltaY = radius - remainDistance; // y 방향으로 이동할 거리

			// 대각선 4가지 방향 탐색
			for (int i = 0; i < 4; i++) {
				int nextX = centerX + deltaX * diagonalX[i];
				int nextY = centerY + deltaY * diagonalY[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				map[nextX][nextY] = -1; // 탐색한 좌표 정체구역으로 표시
			}
			
			remainDistance--; // 현재 정체 구간 남아있는 거리 감소
		}
	}

	// 시작지에서부터 도착지까지 최소 이동 횟수를 구하는 너비우선탐색 메서드
	public static int bfs() {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(0, 0, 0)); // 시작 좌표 및 움직인 횟수 정보 큐에 저장
		visited[0][0] = true; // 시작좌표 방문 처리

		while (!queue.isEmpty()) {
			// 큐에서 현재 좌표 정보 뽑아냄
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowMove = now.move;

			// 현재 좌표가 도착지에 도달한 경우
			if (nowX == N - 1 && nowY == M - 1) {
				return nowMove; // 현재까지 이동한 횟수 반환
			}

			// 4가지 방향 탐색
			for (int i = 0; i < 4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];

				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue; // 다음 방향 탐색하도록 넘어감
				}

				// 탐색한 좌표가 이미 방문처리 됐거나 또는 정체구간(-1)인 경우
				if (visited[nextX][nextY] || map[nextX][nextY] == -1) {
					continue; // 다음 방향 탐색하도록 넘어감
				}

				// 큐에 탐색한 좌표 정보 및 현재까지 이동한 횟수 + 1 해서 저장
				queue.add(new Position(nextX, nextY, nowMove + 1));
				visited[nextX][nextY] = true; // 탐색한 좌표 방문처리
			}
		}

		// 위의 너비우선탐색 실시했는데도 수업을 들으러 갈 수 없는 경우 (즉, 도착지까지 도달하지 못하는 경우) -1 반환
		return -1;
	}

}