import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보를 담고있는 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int M;	// 배추밭 가로 길이
	static int N;	// 배추밭 세로 길이
	static int K;	// 배추가 심어져 있는 위치의 개수
	static int[][] map;	// 배추밭
	static boolean[][] visited;	// 방문배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc=0; tc<testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());	// 배추밭 가로 길이 입력
			N = Integer.parseInt(st.nextToken());	// 배추밭 세로 길이 입력
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];	// [0][0] ~ [N-1][M-1]
			visited = new boolean[N][M];
			
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());	// y좌표 (행) 입력
				int x = Integer.parseInt(st.nextToken());	// x좌표 (열) 입력
				
				map[x][y] = 1;	// 해당 좌표에 배추(1) 심기
			}
			
			int wormCount = 0;	// 배추밭흰지렁이 개수
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					// 해당 좌표가 방문이 안됐으면서 배추(1)가 심어져 있는 좌표인 경우
					if (!visited[i][j] && map[i][j] == 1) {
						bfs(i, j);	// 해당 좌표부터 너비우선탐색 실시
						wormCount++;	// 배추흰지렁이 개수 증가
					}
				}
			}
			
			sb.append(wormCount).append("\n");
		}
		System.out.print(sb);

	}
	
	// 너비우선탐색 메서드
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		
		while (!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if (visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
	}

}
