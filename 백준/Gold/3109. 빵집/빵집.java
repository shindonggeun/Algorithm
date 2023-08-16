import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보를 저장해주는 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int R;	// 행의 개수 (y 좌표)
	static int C;	// 열의 개수 (x 좌표)
	static char[][] map;
	static boolean[][] visited;	// 2차원 방문배열
	static boolean gasSteelOk;	// 원웅이가 파이프라인 통해서 가스 훔쳤는지 여부
	// 3가지 방향 배열(오른쪽 대각선 위, 오른쪽, 오른쪽 아래 대각선)
	static int[] dx = {-1, 0, 1};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열(상, 하)
	static int[] dy = {1, 1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열(우)
	static int pipelineCount;	// 원웅이가 놓을 수 있는 파이프라인 최대 개수 
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];	// (0, 0) ~ (C-1, R-1)
		visited = new boolean[R][C];
		pipelineCount = 0;
		
		for(int i=0; i<R; i++) {
			String input = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		// 처음 열에 해당하는 행들 다 탐색해주기
		for(int i=0; i<R; i++) {
			gasSteelOk = false;	// 아직 파이프라인 설치 못했다는 의미로 false
			dfs(i, 0);	// 깊이우선탐색 실시
		}
		System.out.println(pipelineCount);
	}
	
	// 깊이우선탐색 메서드
	public static void dfs(int startX, int startY) {
		Position now = new Position(startX, startY);
		int nowX = now.x;
		int nowY = now.y;
		visited[nowX][nowY]= true;
		
		// 해당 좌표가 마지막 열에 도달한 경우 (종료조건)
		if(nowY == C-1) {
			gasSteelOk = true;	// 파이프라인 통해서 가스 훔침 (즉, 가스 훔칠 파이프라인 완성했음)
			pipelineCount++;	// 파이프라인 최대 개수 증가
			return;	// 깊이우선탐색 종료
		}
		
		// 3가지 방향 탐색 (오른쪽 대각선 위, 오른쪽, 오른쪽 대각선 아래)
		for(int i=0; i<3; i++) {
			int nextX = nowX + dx[i];
			int nextY = nowY + dy[i];
			
			// (0, 0) ~ (C-1, R-1) 이외의 방향 탐색한 경우
			if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
				continue;
			}
			
			// 이미 방문한 좌표인 경우
			if(visited[nextX][nextY]) {
				continue;
			}
			
			// 탐색한 좌표가 건물('x')인 경우
			if(map[nextX][nextY] == 'x') {
				continue;
			}
			
			// 가스 훔칠 파이프라인 완성됐으면
			if(gasSteelOk) {
				return;	// 해당 탐색 좌표에서 더이상 탐색하지 않도록 메서드 종료시켜주기 (스택에 쌓여있는 메서드들 다 종료할 수 있게끔)
			}
			
			visited[nextX][nextY] = true;	// 해당 좌표 방문 처리
			dfs(nextX, nextY);	// 재귀 호출 (깊이우선탐색 다시 실시)
		}
	}
	
	

}
