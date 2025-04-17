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
	
	static int T; // 테스트 케이스 수
	static int M; // 가로 길이
	static int N; // 세로 길이
	static int K; // 배추가 심어져 있는 위치 개수
	static int[][] map; // 지도 정보 (배추밭 정보)
	static boolean[][] visited; // 각 좌표들의 방문 여부를 담고 있는 배열
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1 , 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int testCase=0; testCase<T; testCase++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M]; // [0][0] ~ [N-1][M-1]
			visited = new boolean[N][M]; // [0][0] ~ [N-1][M-1]
			
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken()); // 배추 위치 (열)
				int row = Integer.parseInt(st.nextToken()); // 배추 위치 (행)
				
				map[row][col] = 1; // 해당 좌표에 배추 심어져 있다는 표시 (1)
			}
			
			int wormCount = 0; // 배추흰지렁이의 개수
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					// 해당 좌표가 배추(1)가 심어져 있으면서 동시에 방문하지 않은 좌표인 경우
					if (map[i][j] == 1 && !visited[i][j]) {
						bfs(i, j); // 해당 좌표에서부터 너비 우선 탐색 실시
						wormCount++; // 배추흰지렁이 개수 증가
					}
				}
			}
			
			sb.append(wormCount).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 해당 좌표에서부터 너비 우선 탐색을 실시하여 배추밭이 심어져있는 지역을 탐색하는 메서드
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY)); // 해당 시작 좌표 정보 큐에 저장
		visited[startX][startY] = true; // 해당 시작 좌표 방문 처리
		
		while (!queue.isEmpty()) {
			// 해당 좌표 정보 큐에서 뽑아냄
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
				
				// 탐색한 좌표가 배추가 심어져 있지 않은 땅(0)이거나 또는 방문한 좌표인 경우
				if (map[nextX][nextY] == 0 || visited[nextX][nextY]) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				visited[nextX][nextY] = true; // 탐색한 좌표 방문 처리
				queue.add(new Position(nextX, nextY)); // 탐색한 좌표 정보 큐에 저장
			}
		}
	}

}