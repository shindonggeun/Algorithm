import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보를 이용하기 위한 내부 클래스
	static class Position {
		int x;
		int y;
		int moveCount;	// 이동한 칸수
		
		public Position(int x, int y, int moveCount) {
			this.x = x;
			this.y = y;
			this.moveCount = moveCount;
		}
	}
	
	static int N;	// 행의 개수
	static int M;	// 열의 개수
	static int[][] map;
	static boolean[][] visited;	// 각 좌표 방문배열
	static int resultMoveCount;	// 최종 이동한 칸수
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		resultMoveCount = 0;
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		bfs(0, 0);	// 너비우선탐색 실시 (시작점 [0][0])
		System.out.println(resultMoveCount);
	}
	
	// 너비우선탐색 메서드
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 1));
		visited[startX][startY] = true;	// 시작좌표 방문처리
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowMoveCount = now.moveCount;
			
			// 현재 좌표가 도착지점에 도달한 경우
			if (nowX == N-1 && nowY == M-1) {
				resultMoveCount = nowMoveCount;	// 현재까지 이동한 칸수 최종 이동한 칸수에 저장
				return;	// 메서드 종료
			}
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// [0][0] ~ [N-1][M-1] 이외의 좌표를 탐색한 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문처리 됐거나 또는 이동할 수 없는 칸(0)인 경우
				if (visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;	// 넘어감
				}
				
				// 큐에 탐색한 좌표 정보와 현재 이동한 칸수 + 1 저장
				queue.add(new Position(nextX, nextY, nowMoveCount + 1));
				visited[nextX][nextY] = true;	// 탐색한 좌표 방문처리
			}
		}
	}

}
