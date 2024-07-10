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
	
	static int N; // 세로 크기
	static int M; // 가로 크기
	static int[][] map;
	static boolean[][] visited; // 방문 배열
	static int[][] resultMap; // 각 지점에서 목표지점까지의 거리를 출력할 배열
	static Queue<Position> queue; // 너비우선탐색 알고리즘에 사용할 큐 선언
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		resultMap = new int[N][M];
		queue = new LinkedList<>(); // 큐 생성
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				resultMap[i][j] = map[i][j];
				// 지도에서 해당 좌표가 원래 갈 수 있는 땅(2)인 경우
				if (map[i][j] == 2) {
					queue.add(new Position(i, j)); // 큐에 해당 좌표 정보 저장
					resultMap[i][j] = 0; // 해당 좌표에서 부터 목표지점까지의 거리 0으로 저장
					visited[i][j] = true; // 해당 좌표 방문 처리
				}
			}
		}
		
		bfs(); // 너비우선탐색 메서드 호출
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				// 해당 좌표가 방문처리가 되지 않았으면서 동시에 갈 수 있는 땅(1)인 경우
				if (!visited[i][j] && map[i][j] == 1) {
					sb.append(-1).append(" "); // -1 출력
				}
				else {
					sb.append(resultMap[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 해당 지점에서부터 목표 지점까지의 거리를 구해줄 너비우선탐색 메서드
	public static void bfs() {
		while (!queue.isEmpty()) {
			// 큐에 저장된 현재 좌표 정보 뽑아냄
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐ㅁ색한 좌표가 이미 방문처리 됐거나 또는 원래 갈 수 없는 (0)인 경우
				if (visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				queue.add(new Position(nextX, nextY)); // 큐에 탐색한 좌표 정보 저장
				visited[nextX][nextY] = true; // 탐색한 좌표 방문처리
				resultMap[nextX][nextY] = resultMap[nowX][nowY] + 1; // 탐색한 좌표까지의 거리 갱신
			}
		}
	}

}