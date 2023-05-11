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
	
	static int N;	// 세로 크기 (y 좌표)
	static int M;	// 가로 크기 (x 좌표)
	static char[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (상, 히, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 이동하는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 이동하는 방향 배열 (좌, 우)
	static int humanCount = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];	// (0, 0) ~ (M-1, N-1)
		visited = new boolean[N][M];
		int startX = 0;
		int startY = 0;
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'I') {
					startX = i;
					startY = j;
				}
			}
		}
		
		bfs(startX, startY);	// 도연이가 있는 위치 너비우선탐색 실시
		
		// 아무도 만나지 못한 경우는 "TT" 출력
		if(humanCount == 0) {
			System.out.println("TT");
		}
		else {
			System.out.println(humanCount);
		}
		
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
				
				// 탐색한 좌표가 (0, 0) ~ (M-1, N-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if(visited[nextX][nextY] || map[nextX][nextY] == 'X') {
					continue;
				}
				
				if(map[nextX][nextY] == 'P') {
					humanCount++;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
	}

}
