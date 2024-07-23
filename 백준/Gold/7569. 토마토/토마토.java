import java.util.*;
import java.io.*;

public class Main {
	
	static class Tomato {
		int z;
		int x;
		int y;
		
		public Tomato(int z, int x, int y) {
			this.z = z;
			this.x = x;
			this.y = y;
		}
	}
	
	static int M; // 가로 칸 수
	static int N; // 세로 칸 수
	static int H; // 상자의 수 (높이)
	static int[][][] map;
	static boolean[][][] visited;
	static Queue<Tomato> queue;
	// 6가지 방향 배열 (위(z), 아래(z), 왼쪽(y), 오른쪽(y), 앞(x), 뒤(x))
	static int[] dz = {-1, 1, 0, 0, 0, 0};
	static int[] dx = {0, 0, 0, 0, 1, -1};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int maxValue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M];
		visited = new boolean[H][N][M];
		queue = new LinkedList<>();
		
		for (int z=0; z<H; z++) {
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<M; j++) {
					map[z][i][j] = Integer.parseInt(st.nextToken());
					if (map[z][i][j] == 1) {
						queue.add(new Tomato(z, i, j));
						visited[z][i][j] = true;
					}
				}
			}
		}
		
		maxValue = Integer.MIN_VALUE;
		
		int minDay = tomatoSpread();

		System.out.println(minDay);
	}
	
	public static int tomatoSpread() {
		while (!queue.isEmpty()) {
			Tomato now = queue.poll();
			int nowZ = now.z;
			int nowX = now.x;
			int nowY = now.y;
			
			for (int i=0; i<6; i++) {
				int nextZ = nowZ + dz[i];
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if (nextZ < 0 || nextX < 0 || nextY < 0 || nextZ >= H || nextX >= N || nextY >= M) {
					continue;
				}
				
				if (visited[nextZ][nextX][nextY] || map[nextZ][nextX][nextY] == -1) {
					continue;
				}
				
				if (map[nextZ][nextX][nextY] == 0) {
					queue.add(new Tomato(nextZ, nextX, nextY));
					visited[nextZ][nextX][nextY] = true;
					map[nextZ][nextX][nextY] = map[nowZ][nowX][nowY] + 1;
				}
			}
		}
		
		if (checkAllTomato()) {
			return maxValue - 1;
		}
		
		return -1;
	}
	
	public static boolean checkAllTomato() {
		for (int z=0; z<H; z++) {
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (map[z][i][j] == 0) {
						return false;
					}
					
					maxValue = Math.max(maxValue, map[z][i][j]);
				}
			}
		}
		
		return true;
	}

}
