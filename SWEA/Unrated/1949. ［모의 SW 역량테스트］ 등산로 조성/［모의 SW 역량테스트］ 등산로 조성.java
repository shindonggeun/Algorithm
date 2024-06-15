import java.util.*;
import java.io.*;

public class Solution {

	static int N; // 지도 한변의 길이
	static int K; // 최대 공사 가능 깊이
	static int[][] map; // 지도
	static boolean[][] visited; // 방문배열
	static int maxDistance; // 만들 수 있는 가장 긴 등산로의 길이
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int testCase = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N]; // [0][0] ~ [N-1][N-1]
			visited = new boolean[N][N];
			int maxHeight = 1; // 지도에서 산의 최대 높이 1로 초기화
			maxDistance = 1; // 만들 수 있는 가장 긴 등산로의 길이 1로 초기화
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, map[i][j]); // 산의 최대 높이 갱신
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					// 해당 좌표가 산의 최대 높이인 경우
					if (maxHeight == map[i][j]) {
						// 해당 좌표에서부터 깊이우선탐색 실시
						dfs(i, j, 1, false);
					}
				}
			}
			
			// 해당 케이스의 가장 긴 등산로의 길이 출력
			sb.append("#").append(tc).append(" ").append(maxDistance).append("\n");
		}
		
		System.out.print(sb);

	}
	
	// 가장 긴 등산로의 길이를 찾아주는 깊이수선탐색 메서드 (백트래킹)
	public static void dfs(int nowX, int nowY, int nowDistance, boolean cut) {
		maxDistance = Math.max(maxDistance, nowDistance); // 가장 긴 등산로의 길이 갱신
		visited[nowX][nowY] = true; // 해당 좌표 방문처리
		
		// 4가지 방향 탐색
		for (int i=0; i<4; i++) {
			int nextX = nowX + dx[i];
			int nextY = nowY + dy[i];
			
			// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
			if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
				continue; // 다음 방향 탐색하도록 넘어감
			}
			
			// 탐색한 좌표가 이미 방문처리 되어 있는 경우
			if (visited[nextX][nextY]) {
				continue; // 다음 방향 탐색하도록 넘어감
			}
			
			// 현재 좌표의 산의 높이가 탐색한 좌표의 산의 높이보다 큰 경우 (등산로 가능)
			if (map[nowX][nowY] > map[nextX][nextY]) {
				// 탐색한 좌표에서부터 다시 깊이우선탐색 실시 (현재 길이 + 1), 산 갂은 여부도 같이 넘김
				dfs(nextX, nextY, nowDistance + 1, cut);
			}
			// 산을 깎지 않았으면서 (공사하지 않았으면서) 동시에 현재 좌표의 산의 높이가 탐색한 좌표의 산의 높이 - K 한것보다 큰 경우
			// 이 경우도 등산로 가능 (산 깎으면 되므로)
			else if (!cut && map[nowX][nowY] > map[nextX][nextY] - K) {
				int originalNextHeight = map[nextX][nextY]; // 탐색한 좌표의 원래 산의 높이 저장
				// 탐색한 좌표의 산의 높이 깎기 - 1
				// 최대 K 만큼 산 깎을 수 있다고 했으므로 현재 좌표의 산의 높이보다 - 1해서 현재 산의 높이보다 낮게 해줌
				map[nextX][nextY] = map[nowX][nowY] - 1;
				// 탐색한 좌표에서부터 산 깎았다는 여부 같이 넘겨주면서 다시 깊이우선탐색 실시 (현재 길이 + 1)
				dfs(nextX, nextY, nowDistance + 1, true);
				map[nextX][nextY] = originalNextHeight; // 탐색한 좌표의 산의 높이를 원래 산의 높이로 다시 되돌림 (백트래킹)
			}
		}
		
		visited[nowX][nowY] = false; // 해당 좌표 방문처리 해제
	}

}
