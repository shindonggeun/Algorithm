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
	static int[][] map;
	static boolean[][] visited;
	
	// 나이트가 이동할수 있는 8가지 방향 배열
	static int[] dx = {-1, 1, -2, 2, -2, 2, -1, 1};	// x축 고정되어 있을 때 y좌표만 움직이는 방향 배열 
	static int[] dy = {-2, -2, -1, -1, 1, 1, 2, 2};	// y축이 고정되어 있을 때 x좌표만 움직이는 방향 배열
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][N];	// (0, 0) ~ (N-1, N-1)
		visited = new boolean[N][N];
		st = new StringTokenizer(br.readLine());
		// 나이트의 시작 위치
		int startX = Integer.parseInt(st.nextToken()) - 1;	
		int startY = Integer.parseInt(st.nextToken()) - 1;
		
		bfs(startX, startY);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken()) - 1;
			int endY = Integer.parseInt(st.nextToken()) - 1;
			
			sb.append(map[endX][endY] + " ");
		}
		System.out.println(sb);
	}
	
	public static int bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now  = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 나이트가 이동할 수 있는 8가지 방향 탐색
			for(int i=0; i<8; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 방향이 (0, 0) ~ (N-1, N-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				// 방문한 좌표거나 최소거리가 저장된 경우들은 넘어감
				if(visited[nextX][nextY] || map[nextX][nextY] != 0) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
				map[nextX][nextY] = map[nowX][nowY] + 1;	// 시작 좌표에서 탐색한 좌표 이동거리 저장해줌
			}
		}
		// 위의 너비우선탐색을 다 했는데도 못찾는 경우면 0 리턴
		return 0;
	}

}
