import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		int jump;
		
		public Position(int x, int y, int jump) {
			this.x = x;
			this.y = y;
			this.jump = jump;
		}
	}
	
	static int N;
	static int M;
	static char[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	static int minJump;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];	// [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken()) - 1;
		int startY = Integer.parseInt(st.nextToken()) - 1;
		int endX = Integer.parseInt(st.nextToken()) - 1;
		int endY = Integer.parseInt(st.nextToken()) - 1;
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		djikstra(startX, startY, endX, endY);
		System.out.println(minJump);
	}
	
	public static void djikstra(int startX, int startY, int endX, int endY) {
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> a.jump - b.jump);
		pq.add(new Position(startX, startY, 0));
		visited[startX][startY] = true;
		
		while(!pq.isEmpty()) {
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowJump = now.jump;
			
			if(nowX == endX && nowY == endY) {
				minJump = nowJump;
				return;
			}
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if(visited[nextX][nextY]) {
					continue;
				}
				
				visited[nextX][nextY] = true;
				pq.add(new Position(nextX, nextY, map[nextX][nextY] == '0' ? nowJump : nowJump + 1));
			}
		}
	}

}
