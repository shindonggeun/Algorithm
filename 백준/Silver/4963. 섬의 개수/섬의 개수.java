import java.util.*;
import java.io.*;

public class Main {
	
	// 섬의 좌표 정보를 담고 있는 내부 클래스
	static class Island {
		int x;
		int y;
		
		public Island(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int W; // 너비
	static int H; // 높이
	static int[][] map; // 지도 정보
	static boolean[][] visited; // 각 좌표의 방문여부를 나타내는 배열
	// 8가지 방향 배열 (상, 상 + 우, 우, 하 + 우, 하, 하 + 좌, 좌, 상 + 좌)
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			// 입력이 "0 0"이 나온 경우
			if (W == 0 && H == 0) {
				break; // 입력 종료 할 수 있게끔 무한반복 종료
			}
			
			map = new int[H][W]; // [0][0] ~ [H-1][W-1]
			visited = new boolean[H][W]; // [0][0] ~ [H-1][W-1]
			
			for (int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int islandCount = 0; // 섬의 개수
			for (int i=0; i<H; i++) {
				for (int j=0; j<W; j++) {
					// 땅(1)이면서 동시에 해당 좌표가 방문하지 않은 경우
					if (map[i][j] == 1 && !visited[i][j]) {
						bfs(i, j); // 너비 우선 탐색 실시
						islandCount++; // 섬의 개수 증가
					}
				}
			}
			
			sb.append(islandCount).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 너비 우선 탐색을 통해 섬의 개수를 찾는 메서드
	public static void bfs(int startX, int startY) {
		Queue<Island> queue = new LinkedList<>();
		queue.add(new Island(startX, startY)); // 시작 좌표 큐에 저장
		visited[startX][startY] = true; // 시작 좌표 방문 처리
		
		while (!queue.isEmpty()) {
			// 현재 섬의 좌표 정보 큐에서 뽑아냄
			Island now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 8가지 방향 탐색
			for (int i=0; i<8; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [H-1][W-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 바다(0)이거나 또는 이미 방문한 경우
				if (map[nextX][nextY] == 0 || visited[nextX][nextY]) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				queue.add(new Island(nextX, nextY)); // 큐에 탐색한 좌표 정보 저장
				visited[nextX][nextY] = true; // 탐색한 좌표 정보 방문 처리
			}
		}
	}

}