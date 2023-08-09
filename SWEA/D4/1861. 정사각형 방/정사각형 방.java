import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int[][] map;
	// 4가지 방향 배열 (상, 하, 좌, 우) -> 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	static int minNum;			// 방에 적힌 최소 정수값
	static int maxMoveCount;	// 최대 몇 개 방 이동했는지
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];	// (0, 0) ~ (N-1, N-1)
			minNum = Integer.MAX_VALUE;	// 가장 많이 방을 이동했을 때 최소 정수값 
			maxMoveCount = 0;	// 가장 많이 방을 이동할 수 있는 횟수
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int tempCount = 0;	// 방 이동한 횟수
			
			// 너비우선탐색 실시하기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					tempCount = bfs(i, j);
					// 너비우선탐색을 실시해서 방 이동한 횟수가 최대로 이동한 횟수보다 많은 경우
					if (tempCount > maxMoveCount) {
						maxMoveCount = tempCount; // 최대로 이동한 횟수 갱신해줌
						minNum = map[i][j]; // 가장 많이 방을 이동했을 때 최소 정수값 갱신
					} else if (tempCount == maxMoveCount && map[i][j] < minNum) {
						maxMoveCount = tempCount; // 최대로 이동한 횟수 갱신해줌
						minNum = map[i][j]; // 가장 많이 방을 이동횄을 대 최소 정수값 갱신
					}	
				}
			}
			
			sb.append("#").append(tc).append(" ").append(minNum).append(" ").append(maxMoveCount).append("\n");
		}
		System.out.print(sb);

	}
	
	// 너비우선탐색 메서드
	public static int bfs(int startX, int startY) {
		// 큐 쓸 필요 없다
		int moveCount = 1;	// 방 이동한 횟수 1로 세팅
		boolean isOk;	// 4가지 방향 탐색 시 이동하는 방 제대로 탐색 되는지 여부를 나타내는 변수
		
		while(true) {
			isOk = false;
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = startX + dx[i];
				int nextY = startY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (N-1, N-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표의 정수값에서 시작좌표의 값의 차이가 1인 경우(즉, 제대로 이동한 경우)
				if(map[nextX][nextY] - map[startX][startY] == 1) {
					moveCount++;
					startX = nextX;	// 탐색한 좌표를 다시 시작좌표로
					startY = nextY;	// 탐색한 좌표를 다시 시작좌표로
					isOk = true;	// 방 제대로 탐색 완료
					break;	// 4가지 방향 탐색할 필요 없이 반복문 빠져나옴
				}
			}
			// 탐색했는데 1씩 증가하는 방향으로 탐색이 안되는 경우
			if(!isOk) {
				break;	// 무한반복 빠져나옴
			}
		}
		
		return moveCount;	// 방 이동한 횟수 반환해줌
		
	}

}
