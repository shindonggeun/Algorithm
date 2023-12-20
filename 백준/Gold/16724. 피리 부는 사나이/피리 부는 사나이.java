import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static char[][] map;
	static boolean[][] visited;
	static boolean[][] isCycle;	
	static int safeZone;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M];
		isCycle = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		safeZone = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j]) {
					dfs(i, j);
				}
			}
		}
		
		System.out.println(safeZone);

	}
	
	public static void dfs(int nowX, int nowY) {
		visited[nowX][nowY] = true;
		char dir = map[nowX][nowY];
		int dIdx = 0;
		// 하
		if(dir == 'D') {
			dIdx = 0;
		}
		// 상
		else if(dir == 'U') {
			dIdx = 1;
		}
		// 좌
		else if(dir == 'L') {
			dIdx = 2;
		}
		// 우
		else {
			dIdx = 3;
		}
		
		int nextX = nowX + dx[dIdx];
		int nextY = nowY + dy[dIdx];
		
		if(!visited[nextX][nextY]) {
			dfs(nextX, nextY);
		}
		
		if(!isCycle[nextX][nextY]) {
			safeZone++;
		}
		
		isCycle[nowX][nowY] = true;
	}

}
