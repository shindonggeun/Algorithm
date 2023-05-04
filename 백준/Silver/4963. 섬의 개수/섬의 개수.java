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
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int islandCount;	// 섬의 개수를 나타내는 변수
	// 8가지 방향 (상, 하, 좌, 우, 오른쪽 대각선 위, 오른쪽 대각선 아래, 왼쪽 대각선 위, 왼쪽 대각선 아래)
	static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};	// x방향 배열
	static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};	// y방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String str = "";
		StringBuilder sb = new StringBuilder();
		
		while(!(str = br.readLine()).equals("0 0")) {
			String[] input = str.split(" ");
			N = Integer.parseInt(input[0]); // 너비 W (x좌표)
			M = Integer.parseInt(input[1]);	// 높이 H (y좌표)
			
			map = new int[M][N];
			visited = new boolean[M][N];
			islandCount = 0;
			
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<M; i++) {
				for(int j=0; j<N; j++) {
					// 땅이면서(1) 방문처리가 안됐으면
					if(map[i][j] == 1 && !visited[i][j]) {
						islandCount++;	// 섬의 개수 증가
						bfs(i, j);	// 너비우선탐색 실행
					}
				}
			}
			
			sb.append(islandCount).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;	// 시작 위치 방문처리
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 8가지 방향 (상, 하, 좌, 우, 오른쪽 대각선 위, 오른쪽 대각선 아래, 왼쪽 대각선 위, 왼쪽 대각선 아래) 탐색
			for(int i=0; i<8; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 이동한 x, y좌표가 범위를 벗어난 경우 (음수좌표 또는 (M-1, N-1) 좌표 넘어간 경우)
                // 맵의 좌표는 (0,0) ~ (M-1, N-1) 까지이다
                if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
                    continue;
                }
                
                // 방문처리가 됐거나 또는 바다인곳이면(0) 넘어감
                if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
                    continue;
                }
                
                queue.add(new Position(nextX, nextY));
                visited[nextX][nextY] = true;   // 해당좌표 방문처리
			}
		}
	}

}