import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x, y, distance;
		
		public Position(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int resultDistance;
	// 체스판에서 나이트가 한번에 이동할 수 있는 8가지 방향 (시계방향부터 시작)
	static int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};	// x축 고정되어 있을 때 y축 좌표가 이동하는 방향좌표(ex. 상, 하)
	static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};	// y축 고정되어 있을 때 x축 좌표가 이동하는 방향좌표(ex. 좌, 우) 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=0; tc<testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			resultDistance = bfs(startX, startY, endX, endY, 0);
			sb.append(resultDistance).append("\n");
		}
		System.out.print(sb);
	}
	
	public static int bfs(int startX, int startY, int endX, int endY, int distance) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, distance));
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			if(nowX == endX && nowY == endY) {
                return nowDistance;
            }
			
			// 8가지 방향 탐색
			for(int i=0; i<8; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// (0, 0) ~ (N-1, N-1)까지만 허용, 그 이외 좌표는 넘어감
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				// 방문한 좌표인 경우 넘어감
				if(visited[nextX][nextY]) {
					continue;
				}
				
				// 이동가능한 경우 거리 1씩 증가
				queue.add(new Position(nextX, nextY, nowDistance + 1));	
				visited[nextX][nextY] = true;
			}
		}
		// 위의 너비우선탐색을 다 했는데도 못찾는 경우면 0 리턴
		return 0;
	}

}
