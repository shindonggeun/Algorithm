import java.util.*;
import java.io.*;

public class Main {
	
	static int R;	// 세로 길이
	static int C;	// 가로 길이
	static char[][] map;	
	static boolean[] visited;	// 알파벳 사용 여부 ('A' ~ 'Z')
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int maxMoveCount;	// 말이 최대 움직인 칸수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];	// [0][0] ~ [R-1][C-1]
		visited = new boolean[26];	// [0] ~ [25], 즉 'A' ~ 'Z'
		
		for(int i=0; i<R; i++) {
			String input = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		maxMoveCount = 1;	// 최대 움직인 칸수 초기화 (말이 지나는 칸 시작 칸수에도 포함하므로)
		dfs(0, 0, 1);	// [0][0]에서 부터 깊이우선탐색 실시 (시작 칸수도 움직인 칸수에 포함이므로 1로 넘김)
		System.out.println(maxMoveCount);

	}
	
	public static void dfs(int nowX, int nowY, int moveCount) {
		// 해당 알파벳이 이미 나온 경우 (종료조건)
		if(visited[map[nowX][nowY] - 'A']) {
			// 최대 움직인 칸수 갱신 
			// 현재 위치에서 이동할 수 없는 경우 (즉, 이미 방문한 알파벳을 만난 경우)
			// 실제로 추가 이동이 발생하지 않았음에도 moveCount가 이미 증가한 상태로 처리
			// 이를 방지하기 위해 moveCount-1을 해줘서 실제 이동한 칸수만큼 고려
			maxMoveCount = Math.max(maxMoveCount, moveCount-1);
			return;	// 메서드 종료
		}
		
		visited[map[nowX][nowY] - 'A'] = true;	// 해당 좌표에 해당하는 알파벳 방문 처리 (사용)
		
		// 4가지 방향 탐색 (하, 상, 좌, 우)
		for(int i=0; i<4; i++) {
			int nextX = nowX + dx[i];
			int nextY = nowY + dy[i];
			
			// 탐색한 좌표가 [0][0] ~ [R-1][C-1] 이외의 좌표인 경우
			if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
				continue;	// 넘어감
			}
			
			dfs(nextX, nextY, moveCount+1);	// 탐색한 좌표로 다시 깊이우선탐색 실시 (현재 움직인 칸 1 증가)
		}
		
		// 탐색 다 했으면 다시 해당 좌표에 해당하는 알파벳 방문 처리 해제 (백트래킹)
		visited[map[nowX][nowY] - 'A'] = false;
	}

}
