import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		int moveCount;
		
		public Position(int x, int y, int moveCount) {
			this.x = x;
			this.y = y;
			this.moveCount = moveCount;
		}
	}
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int start;
	static int end;
	static int maxDistance;
	static int maxSum;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		start = 0;
		end = 0;
		maxDistance = 0;
		maxSum = 0;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] != 0) {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(maxSum);
		
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));
		
		visited = new boolean[N][M];
		visited[startX][startY] = true;
		start = map[startX][startY];
		int distance = 0;
		
		while (!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowMoveCount = now.moveCount;
			
			if (nowMoveCount > distance) {
				distance = nowMoveCount;
				end = map[nowX][nowY];
			}
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if (visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY, nowMoveCount + 1));
				visited[nextX][nextY] = true;
			}
		}
		
		if (distance > maxDistance) {
			maxDistance = distance;
			maxSum = start + end;
		}
		else if (distance == maxDistance) {
			maxSum = Math.max(maxSum, start + end);
		}
		
		
	}

}