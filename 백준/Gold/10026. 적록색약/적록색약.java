import java.util.*;
import java.io.*;

public class Main {
	
	// 해당 색상의 좌표 정보를 담고 있는 내부 클래스
	static class Color {
		int x;
		int y;
		
		public Color(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N; // 그리드의 크기
	static char[][] map; // 색상들을 담고 있는 2차원 배열 (그리드 - 맵)
	static boolean[][] visited; // 해당 좌표들의 방문 여부를 담고 있는 2차원 방문 배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N]; // [0][0] ~ [N-1][N-1]
		visited = new boolean[N][N]; // [0][0] ~ [N-1][N-1]
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		// 1. 적록색약이 아닌 사람이 봤을 때 구역의 개수 구하는 과정 (정상인 사람)
		int normalAreaCount = 0; // 정상인 사람이 그리드를 봤을 때 구역의 개수
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 해당 좌표를 방문하지 않은 경우
				if (!visited[i][j]) {
					bfs(i, j); // 해당 좌표에서부터 너비 우선 탐색 실시
					normalAreaCount++; // 정상인 구역의 개수 증가
				}
			}
		}
		
		// 2. 해당 그리드 적록색약 버전으로 바꾸는 과정 ('G' -> 'R') 
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 해당 좌표가 초록색('G')인 경우
				if (map[i][j] == 'G') {
					map[i][j] = 'R'; // 해당 좌표 빨간색으로 변경
				}
			}
		}
		
		// 3. 적록색약인 사람이 봤을 때 구역의 개수를 구하는 과정 (비정상)
		visited = new boolean[N][N]; // 방문 배열 초기화
		int abnormalAreaCount = 0; // 적록색약인 사람이 그리드를 봤을 때 구역의 개수
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 해당 좌표를 방문하지 않은 경우
				if (!visited[i][j]) {
					bfs(i, j); // 해당 좌표에서부터 너비 우선 탐색 실시
					abnormalAreaCount++; // 비정상인 구역의 개수 증가
				}
			}
		}
		
		System.out.println(normalAreaCount + " " + abnormalAreaCount);

	}
	
	// 해당 색상의 구역을 찾는 너비우선탐색 메서드
	public static void bfs(int startX, int startY) {
		// 너비 우선 탐색 알고리즘을 이용하기위해 큐 선언 및 생성
		Queue<Color> queue = new LinkedList<>();
		queue.add(new Color(startX, startY)); // 큐에 현재 시작 좌표 정보 저장
		visited[startX][startY] = true; // 시작 좌표 방문 처리
		char ch = map[startX][startY]; // 시작 좌표의 색상 저장
		
		while (!queue.isEmpty()) {
			// 큐에서 현재 색상 좌표 정보 뽑아냄
			Color now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 이미 방문처리 됐거나 또는 탐색한 좌표의 색상이 시작 좌표의 색상과 다른 경우
				if (visited[nextX][nextY] || map[nextX][nextY] != ch) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				queue.add(new Color(nextX, nextY)); // 큐에 탐색한 좌표 정보 저장
				visited[nextX][nextY] = true; // 탐색한 좌표 방문 처리
			}
		}
	}

}