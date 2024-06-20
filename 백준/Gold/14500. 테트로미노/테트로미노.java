import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int maxSum;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		maxSum = Integer.MIN_VALUE;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					makeTetromino(i, j, 1, map[i][j]);
					visited[i][j] = false;
				}
			}
		}
		
		System.out.println(maxSum);
		
	}
	
	public static void makeTetromino(int nowX, int nowY, int depth, int sum) {
		if (depth == 4) {
			maxSum = Math.max(maxSum, sum);
			return;
		}
		
		
		for (int i=0; i<4; i++) {
			int nextX = nowX + dx[i];
			int nextY = nowY + dy[i];
			
			if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
				continue;
			}
			
			if (visited[nextX][nextY]) {
				continue;
			}
			
			if (depth == 2) {
				visited[nextX][nextY] = true;
				makeTetromino(nowX, nowY, depth + 1, sum + map[nextX][nextY]);
				visited[nextX][nextY] = false;
			}
			
			visited[nextX][nextY] = true;
			makeTetromino(nextX, nextY, depth + 1, sum + map[nextX][nextY]);
			visited[nextX][nextY] = false;
		}
		
	}
	
}