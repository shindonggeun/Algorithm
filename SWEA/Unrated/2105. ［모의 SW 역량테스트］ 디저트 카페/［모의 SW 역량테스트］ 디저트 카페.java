import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int[][] map;
	// 4가지 방향 배열 (하 + 우, 하 + 좌, 상 + 좌, 상 + 우)
	static int[] dx = {1, 1, -1, -1};	
	static int[] dy = {1, -1, -1, 1};
	static int startX;
	static int startY;
	static boolean[] dessertEat;
	static int maxDessertCount;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxDessertCount = -1;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					startX = i;
					startY = j;
					dessertEat = new boolean[101];
					dessertEat[map[i][j]] = true;
					searchDessertCheck(i, j, 0, 1);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(maxDessertCount).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void searchDessertCheck(int x, int y, int dir, int moveCount) {
		for(int d=dir; d<4; d++) {
			int nextX = x + dx[d];
			int nextY = y + dy[d];
			
			if(nextX == startX && nextY == startY && moveCount > 2) {
				maxDessertCount = Math.max(maxDessertCount, moveCount);
				return;
			}
			
			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
				continue;
			}
			
			if(dessertEat[map[nextX][nextY]]) {
				continue;
			}
			
			dessertEat[map[nextX][nextY]] = true;
			searchDessertCheck(nextX, nextY, d, moveCount + 1);
			dessertEat[map[nextX][nextY]] = false;
			
		}
	}

}
