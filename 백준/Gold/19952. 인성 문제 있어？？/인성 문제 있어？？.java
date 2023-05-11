import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x, y, power;
	
		public Position(int x, int y, int power) {
			this.x = x;
			this.y = y;
			this.power = power;
		}
	}
	
	static int H;	// 세로 길이 (y 좌표)
	static int W;	// 가로 길이 (x 좌표)
	static int F;	// 초기 힘 (남아있는 힘)
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 이동할 수 있는 방향 배열(상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 이동할 수 있는 방향 배열(좌, 우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=0; tc<testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());	// 세로 길이 (y 좌표)
			W = Integer.parseInt(st.nextToken());	// 가로 길이 (x 좌표)
			int O = Integer.parseInt(st.nextToken());	// 장애물의 개수
			F = Integer.parseInt(st.nextToken());
			int startX = Integer.parseInt(st.nextToken()) - 1;
			int startY = Integer.parseInt(st.nextToken()) - 1;
			int endX = Integer.parseInt(st.nextToken()) - 1;
			int endY = Integer.parseInt(st.nextToken()) - 1;
			map = new int[H][W];	// (0, 0) ~ (W-1, H-1)
			visited = new boolean[H][W];
			
			for(int i=0; i<O; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				int L = Integer.parseInt(st.nextToken());
				
				map[x][y] = L;
			}
			
			if(bfs(startX, startY, endX, endY)) {
				sb.append("잘했어!!").append("\n");
			}
			else {
				sb.append("인성 문제있어??").append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	public static boolean bfs(int startX, int startY, int endX, int endY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, F));
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowPower = now.power;
			
			// 도착지에 도달한 경우
			if(nowX == endX && nowY == endY) {
				return true;
			}
			
			// 남은 힘이 0 이하인 경우 움직이지 못함
			if(nowPower == 0) {
				continue;
			}
			
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (W-1, H-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문한 경우 
				if(visited[nextX][nextY]) {
					continue;
				}
				
				// 점프해야하는 높이 = 이동높이 - 현재위치 높이
				int jump = map[nextX][nextY] - map[nowX][nowY];
				// 남아있는 힘이 점프해야하는 높이보다 작은 경우 
				if(nowPower < jump) {
					continue;
				}
				
				// 남아있는 힘이 점프해야하는 높이보다 크거나 같은 경우 이동 가능
				
				queue.add(new Position(nextX, nextY, nowPower-1));
				visited[nextX][nextY] = true;
			}
		}
		// 너비우선탐색 과정 다 거쳤는데도 목적지 좌표로 이동 못하는 경우 false 반환
		return false;
	}

}
