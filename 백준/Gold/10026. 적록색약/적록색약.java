import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int normalCount;	// 정상인 사람이 봤을 때 구역의 개수
	static int abnormalCount;	// 적록색약인 사람이 봤을 때 구역의 개수
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		visited = new boolean[N][N];
		normalCount = 0;
		abnormalCount = 0;
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		// 1. 정상적인 사람이 봤을 때 구역의 개수 구하기
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
					normalCount++;
				}
			}
		}
		
		// 2. 빨간색과 초록색 같게끔 만들어주기 (R == G)
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 해당 좌표가 빨간색인 경우
				if (map[i][j] == 'R') {
					map[i][j] = 'G';	// 초록색으로 만들어주기
				}
			}
		}
		
		// 3. 적록색약인 사람이 봤을 때 구역의 개수 구하기 
		visited = new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
					abnormalCount++;
				}
			}
		}
		
		
		System.out.println(normalCount + " " + abnormalCount);

	}
	
	// 너비우선탐색 메서드
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX ,startY));
		visited[startX][startY] = true;	// 시작좌표 방문 처리
		char color = map[startX][startY];	// 해당 시작좌표의 색상 추출
		
		while (!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 다음 좌표 탐색하게끔 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 탐색한 좌표가 해당 시작좌표의 색상과 다른 경우
				if (visited[nextX][nextY] || map[nextX][nextY] != color) {
					continue;	// 다음 좌표 탐색하게끔 넘어감
				}
				
				// 그 이외의 경우는 큐에 좌표정보 집어넣음
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
	}

}
