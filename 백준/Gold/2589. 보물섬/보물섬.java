import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보 및 거리를 담고있는 내부 클래스
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
	
	static int L;	// 세로 크기
	static int W;	// 가로 크기
	static char[][] map;
	static boolean[][] visited;	// 방문배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int resultDistance;	// 보물이 묻혀 있는 두 곳 사이의 최단 거리

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new char[L][W];	// [0][0] ~ [L-1][W-1]
		
		for (int i=0; i<L; i++) {
			String input = br.readLine();
			for (int j=0; j<W; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		resultDistance = 0;
		
		for (int i=0; i<L; i++) {
			for (int j=0; j<W; j++) {
				// 해당 좌표가 육지('L')인 경우
				if (map[i][j] == 'L') {
					visited = new boolean[L][W];	// 방문배열 초기화
					int distance = bfs(i, j);	// 해당 좌표 너비우선탐색 실시
					resultDistance = Math.max(resultDistance, distance);
				}
			}
		}
		
		System.out.println(resultDistance);

	}
	
	// 보물이 묻혀 있는 두 곳 사이의 거리를 찾도록 해주는 너비우선탐색 메서드
	public static int bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));
		visited[startX][startY] = true;
		// 보물이 묻혀 있는 두 곳 사이의 최단 거리 (문제에서는 최단거리라고 표현되어 있지만 가장 긴 길이 찾으면 됨)
		int maxDistance = 0;	
		
		// 너비우선탐색 시작
		while (!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [L-1][W-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= L || nextY >= W) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 바다('W')인 경우
				if (visited[nextX][nextY] || map[nextX][nextY] == 'W') {
					continue;	// 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표 정보 큐에 저장
				queue.add(new Position(nextX, nextY, nowDistance + 1));
				visited[nextX][nextY] = true;	// 탐색한 좌표 방문처리
				maxDistance = Math.max(maxDistance, nowDistance + 1);	// 최단거리 갱신
			}
		}
		
		// 너비우선탐색 끝났으면 보물이 묻혀있는 두 곳 사이의 최단거리 반환
		return maxDistance;
	}

}
