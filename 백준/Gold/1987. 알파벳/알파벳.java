import java.util.*;
import java.io.*;

public class Main {
	
	static int R;	// 세로 칸수
	static int C;	// 가로 칸수
	static char[][] map;
	static boolean[] visited;	// 'A' ~ 'Z' 방문 여부를 나타내는 배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int maxMoveCount;	// 말이 지날 수 있는 최대 칸 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];	// [0][0] ~ [R-1][C-1]
		visited = new boolean[26];	// 'A' ~ 'Z' [0] ~ [25]
		for (int i=0; i<R; i++) {
			String input = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		maxMoveCount = 1;	// 최대 이동한 칸수는 1로 초기화 (말이 지나는 칸도 시작 칸수에 포함하므로)
		dfs(0, 0, 1);	// 깊이우선탐색 실시 (백트래킹) -> [0][0] 부터 시작, 이동한 칸수는 1부터 시작
		System.out.println(maxMoveCount);
	}
	
	// 깊이우선탐색 메서드 (백트래킹)
	public static void dfs(int nowX, int nowY, int moveCount) {
		// 해당 알파벳이 이미 나온 경우 (기저조건)
		if (visited[map[nowX][nowY] - 'A'] ) {
			// 최대 움직인 칸수 갱신 
			// 탐색한 방향으로 깊이우선탐색 실시했는데 해당 알파벳이 이미 나왔으므로 움직인칸수를 - 1 해줘야한다
			// 이유: 깊이우선탐색 실시할 때 움직인칸수를 + 1 해준상태로 탐색하였으므로
			maxMoveCount = Math.max(moveCount-1, maxMoveCount);
			return;	// 메서드 종료
		}
		
		visited[map[nowX][nowY] - 'A'] = true;	// 해당 알파벳 방문 처리
		
		// 4가지 방향 탐색
		for (int i=0; i<4; i++) {
			int nextX = nowX + dx[i];
			int nextY = nowY + dy[i];
			
			// 탐색한 방향이 [0][0] ~ [R-1][C-1] 이외의 좌표인 경우
			if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
				continue;	// 다른 방향 탐색하도록 넘어감
			}
			
			dfs(nextX, nextY, moveCount + 1);	// 탐색한 방향으로 다시 깊이우선탐색 실시
		}
		
		visited[map[nowX][nowY] - 'A'] = false;	// 해당 알파벳 방문처리 해제
	}
	
	

}
