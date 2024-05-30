import java.util.*;
import java.io.*;

public class Main {
	
	// 산봉우리의 좌표 정보를 담고있는 내부 클래스 
	static class Mountain {
		int x;
		int y;
		
		public Mountain(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;	// 맵의 행의 개수
	static int M;	// 맵의 열의 개수
	static int[][] map;
	static boolean[][] visited;	// 각 좌표마다 방문 여부를 나타내는 배열
	// 8가지 방향 배열 (맨 위부터 시계방향으로)
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;	// 산봉우리의 개수
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				// 해당 좌표가 방문처리 되지 않았으면서 동시에 해당 좌표의 높이가 0이 아닌 경우
				if (!visited[i][j] && map[i][j] != 0) {
					// 해당 좌표부터 너비우선탐색을 실시하였을 때 문제 조건에 부합하는 산봉우리인 경우
					if (bfs(i, j)) {
						count++;	// 산봉우리의 개수 증가
					}
				}
			}
		}
		
		System.out.println(count);

	}
	
	// 문제에 부합하는 산봉우리를 찾는 너비우선탐색 메서드
	public static boolean bfs(int startX, int startY) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Mountain> queue = new LinkedList<>();
		queue.add(new Mountain(startX, startY));	// 산봉우리의 시작좌표정보 큐에 저장
		visited[startX][startY] = true;	// 시작 좌표 방문처리
		
		int currentHeight = map[startX][startY];	// 시작좌표부터 시작하는 현재 산봉우리의 높이
		boolean isPeak = true;	// 산봉우리 여부를 판단하는 변수
		
		while (!queue.isEmpty()) {
			// 큐에서 현재 산봉우리의 정보 뽑아냄
			Mountain now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 8가지 방향 탐색
			for (int i=0; i<8; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표인 산봉우리의 높이가 현재 산봉우리의 높이보다 큰 경우
				if (map[nextX][nextY] > currentHeight) {
                    isPeak = false;	// 산봉우리가 아님
                }
                
				// 탐색한 좌표가 방문하지 않았으면서 동시에 탐색한 좌표의 산봉우리 높이가 현재 산봉우리의 높이와 같은 경우
                if (!visited[nextX][nextY] && map[nextX][nextY] == currentHeight) {
                    visited[nextX][nextY] = true;	// 탐색한 좌표 방문처리
                    queue.add(new Mountain(nextX, nextY));	// 큐에 탐색한 좌표 정보 저장
                }
			}
		}
		
		// 산봉우리 여부 반환
		return isPeak;
	}

}