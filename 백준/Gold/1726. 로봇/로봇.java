import java.util.*;
import java.io.*;

public class Main {
	
	static class Robot {
		int x;
		int y;
		int dir;
		int count;	// 명령 횟수
		
		public Robot(int x, int y, int dir, int count) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.count = count;
		}
	}
	
	static int M;	// 세로 길이
	static int N;	// 가로 길이
	static int[][] map;
	static boolean[][][] visited;
	// 4가지 방향 배열 (index 1부터 시작) -> 동(1), 서(2), 남(3), 북(4)
	static int[] dx = {0, 0, 0, 1, -1};
	static int[] dy = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		visited = new boolean[5][M][N];
		
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken()) - 1;
		int startY = Integer.parseInt(st.nextToken()) - 1;
		int startDir = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int endX = Integer.parseInt(st.nextToken()) - 1;
		int endY = Integer.parseInt(st.nextToken()) - 1;
		int endDir = Integer.parseInt(st.nextToken());
		
		int minCount = bfs(startX, startY, startDir, endX, endY, endDir);
		System.out.println(minCount);
	}
	
	public static int bfs(int startX, int startY, int startDir, int endX, int endY, int endDir) {
		Queue<Robot> queue = new LinkedList<>();
		queue.add(new Robot(startX, startY, startDir, 0));
		visited[startDir][startX][startY] = true;
		
		while (!queue.isEmpty()) {
			Robot now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDir = now.dir;
			int nowCount = now.count;
			
			if (nowX == endX && nowY == endY && nowDir == endDir) {
				return nowCount;
			}
			
			// 명령 Go K
			for (int k=1; k<=3; k++) {
				int nextX = nowX + dx[nowDir] * k;
				int nextY = nowY + dy[nowDir] * k;
				
				if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
					continue;
				}
				
				if (map[nextX][nextY] == 1) {
					break;
				}
				
				if (!visited[nowDir][nextX][nextY]) {
					visited[nowDir][nextX][nextY] = true;
					queue.add(new Robot(nextX, nextY, nowDir, nowCount + 1));
				}
			}
			
			// 명령 Turn Dir
			int left = 0;	// 왼쪽으로 90도
			int right = 0;	// 오른쪽으로 90도
			
			// 왼쪽으로 90도 회전하는 경우 -> 동(1) -> 북(4) -> 서(2) -> 남(3)
			// 오른쪽으로 90도 회전하는 경우 -> 동(1) -> 남(3) -> 서(2) -> 북(4)
			switch (nowDir) {
				case 1: // 현재 방향이 동쪽인 경우
					left = 4;	// 동쪽(1) 방향에서 왼쪽으로 90도 회전 -> 북(4)
					right = 3;	// 동쪽(1) 방향에서 오른쪽으로 90도 회전 -> 남(3)
					break;
				case 2:	// 현재 방향이 서쪽인 경우
					left = 3;
					right = 4;
					break;
				case 3:	// 현재 방향이 남쪽인 경우
					left = 1;
					right = 2;
					break;
				case 4:	// 현재 방향이 북쪽인 경우
					left = 2;
					right = 1;
					break;
					
			}
			
			if (!visited[left][nowX][nowY]) {
				visited[left][nowX][nowY] = true;
				queue.add(new Robot(nowX, nowY, left, nowCount + 1));
			}
			
			if (!visited[right][nowX][nowY]) {
				visited[right][nowX][nowY] = true;
				queue.add(new Robot(nowX, nowY, right, nowCount + 1));
			}
		}
		
		return -1;
	}

}