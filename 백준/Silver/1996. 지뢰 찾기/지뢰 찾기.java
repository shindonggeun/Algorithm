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
	static char[][] map;
	static char[][] resultMap;
	// 8가지 방향 탐색하는 방향 배열(상, 하, 좌, 우, 상+좌, 상+우, 하+좌, 하+우)
	static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};	// x축이 고정되어 있을 때 y 좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};	// y축이 고정되어 있을 때 x 좌표가 움직이는 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new char[N][N];	// (0, 0) ~ (N-1, N-1)	
		resultMap = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] != '.') {
					resultMap[i][j] = '*';
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == '.') {
					bfs(i, j);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(resultMap[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int mineCount = 0;
			// 8가지 방향 탐색
			for(int i=0; i<8; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (N-1, N-1) 이외의 좌표 탐색한 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				if(map[nextX][nextY] != '.') {
					mineCount += (map[nextX][nextY] - '0');
				}
			}
			
			if(mineCount >= 10) {
				resultMap[nowX][nowY] = 'M';
			}
			else {
				resultMap[nowX][nowY] = (char)(mineCount + '0');
			}
		}
	}
	

}
