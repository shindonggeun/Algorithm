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
	
	static int w;	// 너비 (열의 개수)
	static int h;	// 높이 (행의 개수)
	static int[][] map;
	static boolean[][] visited;
	// 8가지 방향 배열 (상, 하, 좌, 우, 상+우, 하+우, 하+좌, 상+좌)
	static int[] dx = {1, -1, 0, 0, 1, -1, -1, 1};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String input = br.readLine();
			// 무한반복 탈출조건 -> "0 0"이 입력되면 탈출
			if(input.equals("0 0")) {
				break;
			}
			
			String[] inputSplit = input.split(" ");
			w = Integer.parseInt(inputSplit[0]);
			h = Integer.parseInt(inputSplit[1]);
			map = new int[h][w];	// (0, 0) ~ (w-1, h-1)
			visited = new boolean[h][w];
			
			for(int i=0; i<h; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int islandCount = 0;	// 섬의 개수 
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					// 방문안한 좌표면서 동시에 땅(1)인 경우
					if(!visited[i][j] && map[i][j] == 1) {
						bfs(i, j);	// 너비우선탐색 실시
						islandCount++;	// 섬의 개수 증가
					}
				}
			}
			// StringBuilder에 섬의 개수 저장
			sb.append(islandCount).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();	// 너비우선탐색 이용하기 위해 큐 선언 및 생성
		queue.add(new Position(startX, startY));	// 큐에 해당 시작좌표 넣어줌
		visited[startX][startY] = true;	// 해당 시작좌표 방문처리
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 8가지 방향 탐색 (상, 하, 좌, 우, 상+우, 하+우, 하+좌, 상+좌)
			for(int i=0; i<8; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (w-1, h-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= h || nextY >= w) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 바다(0)인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));	
				visited[nextX][nextY] = true;
			}
		}
	}
	
	

}
