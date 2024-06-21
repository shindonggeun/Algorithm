import java.util.*;
import java.io.*;

public class Main {
	
	static class Monkey {
		int x;
		int y;
		int ability;
		int move;
		
		public Monkey(int x, int y, int ability, int move) {
			this.x = x;
			this.y = y;
			this.ability = ability;
			this.move = move;
		}
	}
	
	static int K;
	static int W;
	static int H;
	static int[][] map;
	static boolean[][][] visited;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] fourDx = {1, -1, 0, 0};
	static int[] fourDy = {0, 0, -1, 1};
	// 8가지 방향 배열 (시계 방향 순으로)
	static int[] eightDx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] eightDy = {1, 2, 2, 1, -1, -2, -2, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W]; // [0][0] ~ [H-1][W-1]
		visited = new boolean[K+1][H][W];
		
		for (int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = bfs(0, 0);
		
		System.out.println(result);
	}
	
	public static int bfs(int startX, int startY) {
		Queue<Monkey> queue = new LinkedList<>();
		queue.add(new Monkey(startX, startY, 0, 0));
		visited[0][startX][startY] = true;
		
		while (!queue.isEmpty()) {
			Monkey now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowAbility = now.ability;
			int nowMove = now.move;
			
			if (nowX == H-1 && nowY == W-1) {
				return nowMove;
			}
			
			for (int i=0; i<4; i++) {
				int nextX = nowX + fourDx[i];
				int nextY = nowY + fourDy[i];
				
				if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
					continue;
				}
				
				if (visited[nowAbility][nextX][nextY] || map[nextX][nextY] == 1) {
					continue;
				}
				
				queue.add(new Monkey(nextX, nextY, nowAbility, nowMove + 1));
				visited[nowAbility][nextX][nextY] = true;
			}
			
			if (nowAbility < K) {
				// 8가지 방향 탐색
				for (int i=0; i<8; i++) {
					int nextX = nowX + eightDx[i];
					int nextY = nowY + eightDy[i];
					
					if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
						continue;
					}
					
					if (visited[nowAbility+1][nextX][nextY] || map[nextX][nextY] == 1) {
						continue;
					}
					
					queue.add(new Monkey(nextX, nextY, nowAbility + 1, nowMove + 1));
					visited[nowAbility+1][nextX][nextY] = true;
				}
			}
		}
		
		return -1;
	}

}