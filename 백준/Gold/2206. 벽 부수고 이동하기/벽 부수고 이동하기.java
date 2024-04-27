import java.util.*;
import java.io.*;

public class Main {
	
	// 현재 좌표 정보를 담을 내부 클래스
	static class Position {
		int x;
		int y;
		int distance;
		boolean crack;	// 벽 부쉈는지 여부
		
		public Position(int x, int y, int distance, boolean crack) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.crack = crack;
		}
	}
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][][] visited;	// 3차원 방문 배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// [0][0] ~ [M-1][N-1]
		visited = new boolean[2][N][M];	// [0] => 벽 안부숨, [1] => 벽 부숨
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		int resultDistance = bfs(0, 0);
		System.out.println(resultDistance);
	}
	
	// 시작지점에서 도착지점까지 가기 위해 사용할 너비우선탐색 메서드
	public static int bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 1, false));
		visited[0][startX][startY] = true;	// 벽을 부수지 않은 상태의 시작 좌표 방문처리
		
		while (!queue.isEmpty()) {
			// 큐에서 현재 좌표 정보 빼내기
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			boolean nowCrack = now.crack;
			
			// 도착지점에 도달한 경우
			if (nowX == N-1 && nowY == M-1) {
				return nowDistance;	// 현재까지 이동한 거리 반환
			}
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 벽(1)인 경우
				if (map[nextX][nextY] == 1) {
					// 현재 벽을 부순적이 없는 경우 -> 벽을 부수고 이동할거임
					if (!nowCrack) {
						// 탐색한 좌표 정보 큐에 저장 (벽 부순 여부도 같이 저장)
						queue.add(new Position(nextX, nextY, nowDistance + 1, true));
						visited[1][nextX][nextY] = true;	// 벽을 부순 상태의 탐색한 좌표 방문처리
						
					}
				}
				// 탐색한 좌표가 이동가능(0)한 경우
				else {
					// 현재 벽을 부순적이 없으면서 동시에 벽을 부수지 않은 상태의 탐색한 좌표가 방문하지 않은 경우
					if (!nowCrack && !visited[0][nextX][nextY]) {
						// 탐색한 좌표 정보 큐에 저장 (벽 부수지 않은 여부도 같이 저장)
						queue.add(new Position(nextX, nextY, nowDistance + 1, false));
						visited[0][nextX][nextY] = true;	// 벽을 부수지 않은 상태의 탐색한 좌표 방문 처리
					}
					
					// 현재 벽을 부순적이 있으면서 동시에 벽을 부순 상태의 탐색한 좌표가 방문하지 않은 경우
					if (nowCrack && !visited[1][nextX][nextY]) {
						// 탐색한 좌표 정보 큐에 저장 (벽 부순 여부도 같이 저장)
						queue.add(new Position(nextX, nextY, nowDistance + 1, true));
						visited[1][nextX][nextY] = true;	// 벽을 부순 상태의 탐색한 좌표 방문 처리
					}
				}
			}
		}
		
		// 너비우선탐색 진행 했는데도 도착 좌표까지 갈 수 없는 경우 -1 반환
		return -1;
	}

}
