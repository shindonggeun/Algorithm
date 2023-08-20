import java.util.*;
import java.io.*;

public class Main {
	
	static int R;	// 행의 개수 
	static int C;	// 열의 개수
	static char[][] map;
	static boolean[] alpabetVisited;	// 알파벳 방문 여부
	
	// 4가지 방향 배열 (상, 하, 좌, 우) -> 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	static int maxMoveCount;	// 말이 최대로 이동한 칸 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];	// (0, 0) ~ (C-1, R-1) => [0][0] ~ [R-1][C-1]
		alpabetVisited = new boolean[26];	// [0] = 'A' 방문 여부, ... , [25] = 'Z' 방문 여부
		maxMoveCount = Integer.MIN_VALUE;	// 말이 최대로 이동한 칸 수 최소값으로 설정
		
		for(int i=0; i<R; i++) {
			String input = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		dfs(0, 0, 0);	 // 깊이우선탐색 실시 (시작좌표 [0][0]부터 시작)
		System.out.println(maxMoveCount);
	}
	
	// 깊이우선탐색 메서드
	public static void dfs(int startX, int startY, int moveCount) {
		int alpabetIdx = map[startX][startY] - 'A';
		// 해당 알파벳을 방문한 경우 (즉, 이미 나온 경우) (종료조건)
		if(alpabetVisited[alpabetIdx]) {
			maxMoveCount = Math.max(maxMoveCount, moveCount);	// 말이 최대로 이동한 칸 수 갱신
			return;	// 메서드 종료
		}
		// 해당 알파벳 방문하지 않은 경우 (즉, 한번도 안나온 경우)
		else {
			alpabetVisited[alpabetIdx] = true;
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = startX + dx[i];
				int nextY = startY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [R-1][C-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
					continue;
				}
				
				dfs(nextX, nextY, moveCount + 1);	// 깊이우선탐색 메서드 재귀 호출
			}
		}
		
		// 탐색 다 했으면 다시 해당 알파벳 사용처리 해제 (백트래킹)
		alpabetVisited[alpabetIdx] = false;
	}

}
