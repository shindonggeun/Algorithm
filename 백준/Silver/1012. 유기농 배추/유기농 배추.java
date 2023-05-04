import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x, y;
		
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
	// 4가지 방향 (상, 하, 좌, 우)
	static int[] dx = {0, 0, 1, -1};	// x방향 배열 (상, 하)
	static int[] dy = {1, -1, 0, 0};	// y방향 배열 (좌, 우)
	static int wormCount;	// 배추흰지렁이 마리 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());	// 가로 길이
			N = Integer.parseInt(st.nextToken());	// 세로 길이
			K = Integer.parseInt(st.nextToken());	// 배추가 심어져 있는 위치의 개수
			wormCount = 0;
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int tempX = Integer.parseInt(st.nextToken());
				int tempY = Integer.parseInt(st.nextToken());
				
				map[tempY][tempX] = 1;	// 해당 위치에 배추 심어져있음
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					// 방문처리가 되지 않았으면서 배추가 심어져 있는 곳인 경우
					if(!visited[i][j] && map[i][j] == 1) {
						wormCount++;	// 배추흰지렁이 개수 증가
						bfs(i, j);	// 너비우선탐색 호출
					}
				}
			}
			sb.append(wormCount).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 이동한 x, y좌표가 범위를 벗어난 경우 (음수좌표 또는 (M-1, N-1) 좌표 넘어간 경우)
                // 맵의 좌표는 (0,0) ~ (M-1, N-1) 까지이다
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 방문처리가 됐거나 또는 배추가 심어져있지 않은 땅이면(0) 넘어감
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;   // 해당좌표 방문처리
			}
		}
	}

}
