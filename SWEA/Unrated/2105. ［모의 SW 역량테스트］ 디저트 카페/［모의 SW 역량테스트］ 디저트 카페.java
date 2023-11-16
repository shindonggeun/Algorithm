import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;	// 가로, 세로 크기
	static int[][] map;
	static boolean[] eatDessert;	// 디저트 번호에 따른 먹었는지 여부를 나타내는 배열
	// 4가지 방향 배열 (하 + 우, 하 + 좌, 상 + 좌, 상 + 우) (대각선 방향 배열)
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	static int startX;	// 시작 좌표
	static int startY;	// 시작 좌표
	static int maxEatDessertCount;	// 최대로 먹은 디저트 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];	// [0][0] ~ [N-1][N-1]
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxEatDessertCount = -1;	// 최대로 먹은 디저트 개수 -1로 초기화 (디저트 먹을 수 없는 경우는 -1)
			
			// 모든 좌표 다 탐색하기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 시작 좌표 갱신
					startX = i;
					startY = j;
					// 디저트 번호에 따른 먹었는지 여부를 나타내는 배열 초기화
					eatDessert = new boolean[100+1];	// [1] ~ [100]
					eatDessert[map[i][j]] = true;	// 해당 좌표에 따른 디저트 번호(종류) 먹었다고 표시
					dessertCheck(i, j, 0, 1);	// 디저트 카페 투어할 수 있도록 메서드 호출
				}
			}
			
			sb.append("#").append(tc).append(" ").append(maxEatDessertCount).append("\n");
		}
		System.out.print(sb);

	}
	
	// 디저트 카페 투어 할 수 있도록 해주는 메서드 (디저트 종류 탐색하기) (깊이우선탐색 + 백트래킹 알고리즘 이용)
	public static void dessertCheck(int nowX, int nowY, int dir, int moveCount) {
		// 해당 방향부터 시작해서 4가지 방향 탐색 (하, 상, 좌, 우)
		for(int d=dir; d<4; d++) {
			int nextX = nowX + dx[d];
			int nextY = nowY + dy[d];
			
			// 탐색한 좌표가 시작좌표에 도달하면서 동시에 디저트 카페 투어를 하기 위해 움직인 횟수가 3 초과인 경우 (종료조건)
			if(nextX == startX && nextY == startY && moveCount > 3) {
				maxEatDessertCount = Math.max(maxEatDessertCount, moveCount);	// 최대로 먹은 디저트 개수 갱신
				return;	// 메서드 종료
			}
			
			// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
				continue;	// 다른 방향을 탐색할 수 있도록 넘어감
			}
			
			// 탐색한 좌표가 이미 먹은 디저트 종류인 경우
			if(eatDessert[map[nextX][nextY]]) {
				continue;	// 다른 방향을 탐색할 수 있도록 넘어감
			}
			
			eatDessert[map[nextX][nextY]] = true;	// 탐색한 좌표에 해당하는 디저트 종류 먹었다는 표시 (true)
			dessertCheck(nextX, nextY, d, moveCount + 1);
			eatDessert[map[nextX][nextY]] = false;	// 탐색한 좌표에 해당하는 디저트 종류 먹었다는 표시 해제 (false)
		}
	}

}
