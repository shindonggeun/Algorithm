import java.util.*;
import java.io.*;

public class Main {
	
	static class Robot {
		int x;
		int y;
		int dir;
		
		public Robot(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (북, 동, 남, 서)
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());
		int startDir = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cleanCount = simulate(startX, startY, startDir);
		
		System.out.println(cleanCount);
	}
	
	public static int simulate(int startX, int startY, int startDir) {
		int nowX = startX;
		int nowY = startY;
		int nowDir = startDir;
		int cleanCount = 0;
		
		while (true) {
			// 현재 위치 청소
			if (!visited[nowX][nowY]) {
				visited[nowX][nowY] = true;
				cleanCount++;
			}
			
			boolean clean = false;
			// 주변 4칸 검사
			for (int i=0; i<4; i++) {
				// 반시계방향 (왼쪽)으로 90도 회전하는 방향
				// 북 = 0, 동 = 1, 남 = 2, 서 = 3
				switch(nowDir) {
					// 현재 로봇이 바라보는 방향이 북쪽인 경우 
					case 0:
						nowDir = 3;
						break;
					// 현재 로봇이 바라보는 방향이 동쪽인 경우
					case 1:
						nowDir = 0;
						break;
					case 2:
						nowDir = 1;
						break;
					case 3:
						nowDir = 2;
						break;
				}
				
				int nextX = nowX + dx[nowDir];
				int nextY = nowY + dy[nowDir];
				
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				if (visited[nextX][nextY] || map[nextX][nextY] == 1) {
					continue;
				}
				
				nowX = nextX;
				nowY = nextY;
				clean = true;
				break;
			}
			
			
			if (!clean) {
				// 주변 4칸 모두 청소되어 있거나 벽인 경우
				int backDir = 0; // 후진 방향
				
				// 북 = 0, 동 = 1, 남 = 2, 서 = 3
				switch (nowDir) {
					// 로봇이 바라보는 방향이 북쪽인 경우
					case 0:
						backDir = 2;
						break;
					case 1:
						backDir = 3;
						break;
					case 2:
						backDir = 0;
						break;
					case 3:
						backDir = 1;
						break;
				}
				
				int backX = nowX + dx[backDir];
				int backY = nowY + dy[backDir];
				
				if (backX < 0 || backY < 0 || backX >= N || backY >= M) {
					break;
				}
				
				if (map[backX][backY] == 1) {
					break;
				}
				
				nowX = backX;
				nowY = backY;
			}
		}
		
		return cleanCount;
	}

}