import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보를 담고 있는 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N; // 게임 구역의 크기
	static int[][] map; // 게임판을 나타내는 2차원 배열
	static boolean[][] visited; // 2차원 방문 배열
	// 2가지 방향 배열 (우, 하)
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());

		map = new int[N][N]; // [0][0] ~ [N-1][N-1]
		visited = new boolean[N][N]; // [0][0] ~ [N-1][N-1]
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 너비우선탐색 메서드를 호출하여 해당 승리지점(오른쪽 맨아래칸)에 접근 가능한 경우 (true) -> "HaruHaru" 출력
		// 승리지점에 접근 불가능한 경우 (false) -> "Hing" 출력
		System.out.println(bfs() == true ? "HaruHaru" : "Hing");
	}
	
	// 시작지점([0][0])에서 출발하여 도착지점([N-1][N-1])까지 도달하는지 여부를 판단하는 메서드 (너비우선탐색)
	public static boolean bfs() {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(0, 0)); // 시작지점 큐에 저장
		visited[0][0] = true; // 시작지점 방문 처리
		
		while (!queue.isEmpty()) {
			// 큐에서 현재 좌표 정보 뽑아냄
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowMove = map[nowX][nowY]; // 현재 이동할 수 있는 칸 수
			
			// 현재 좌표가 도착지점에 도달한 경우
			if (nowX == N-1 && nowY == N-1) {
				return true; // 승리지점에 접근 가능하므로 true 반환
			}
			
			// 2가지 방향 탐색 (우, 하)
			for (int i=0; i<2; i++) {
				// 탐색할 좌표 설정 (현재 좌표 + 이동할 좌표 * 현재 이동할 수 있는 칸 수)
				int nextX = nowX + dx[i] * nowMove;
				int nextY = nowY + dy[i] * nowMove;
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 이미 방문한 경우
				if (visited[nextX][nextY]) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 큐에 탐색한 좌표 정보 저장
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true; // 탐색한 좌표 방문 처리
			}
		}
		
		// 위의 너비우선탐색 과정을 거쳤는데도 도착지점에 도달하지 못한 경우 false 반환
		return false;
	}

}