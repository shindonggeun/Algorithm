import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int M;	// 가로 길이
	static int N;	// 세로 길이
	static int K;	// 배추가 심어져 있는 위치의 개수
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 탐색 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열(상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열(좌, 우)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
			visited = new boolean[N][M];
			
			// 배추 심어져있는 위치의 개수만큼 반복문 돌리기
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());	// y좌표 (행) 입력
				int x = Integer.parseInt(st.nextToken());	// x좌표 (열)	입력
				
				map[x][y] = 1;	// 해당 좌표에 배추 심기(1)
			}
			
			int wormCount = 0;	// 배추흰지렁이 개수
			// 배추밭 탐색하기 
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					// 해당 좌표가 탐색 안한 좌표면서 동시에 배추가 심어져 있는 좌표(1)인 경우 
					if(!visited[i][j] && map[i][j] == 1) {
						bfs(i, j);	// 너비우선탐색 실시
						wormCount++;
					}
				}
			}
			sb.append(wormCount).append("\n");
		}
		
		System.out.print(sb);

	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();	// 너비우선탐색을 하기 위해 큐 선언 및 생성
		queue.add(new Position(startX, startY));	// 시작좌표 큐에 집어넣기
		visited[startX][startY] = true;	// 시작 좌표 방문처리
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// (0, 0) ~ (M-1, N-1) 이외의 좌표를 탐색한 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 배추가 심어져있지 않은 땅(0)인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));	// 큐에 탐색한 좌표 집어넣기
				visited[nextX][nextY] = true;	// 탐색한 좌표 방문처리
			}
		}
	}

}
