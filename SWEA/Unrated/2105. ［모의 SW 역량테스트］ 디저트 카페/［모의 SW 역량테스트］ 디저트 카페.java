import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int[][] map;
	// 대각선 방향으로 움직이는 방향 배열 (하 + 우, 하 + 좌, 상 + 좌, 상 + 우)
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	static boolean[][] visited;
	static HashSet<Integer> dessertSet;
	static int maxDessertCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];	// [0][0] ~ [N-1][N-1]
			maxDessertCount = -1;
			dessertSet = new HashSet<>();
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N-2; i++) {	// 사각형 모양이므로 끝에서 두 칸 떨어진 곳까지만 시작점으로 설정
				for(int j=1; j<N-1; j++) {	// 사각형 모양이므로 첫 칸과 마지막 칸은 시작점이 될 수 없음
					dessertSet.clear();
					visited = new boolean[N][N];
					dessertSet.add(map[i][j]);
					visited[i][j] = true;
					dessertSearch(i, j, i, j, 0);
					visited[i][j] = false;
					dessertSet.remove(map[i][j]);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(maxDessertCount).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void dessertSearch(int x, int y, int startX, int startY, int dir) {
		for(int d=dir; d<4; d++) {
			int nextX = x + dx[d];
			int nextY = y + dy[d];
			
			if(dessertSet.size() >= 3 && nextX == startX && nextY == startY) {
				maxDessertCount = Math.max(maxDessertCount, dessertSet.size());
				return;
			}
			
			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
				continue;
			}
			
			if(visited[nextX][nextY]) {
				continue;
			}
			
			if(dessertSet.contains(map[nextX][nextY])) {
				continue;
			}
			
			visited[nextX][nextY] = true;
			dessertSet.add(map[nextX][nextY]);
			dessertSearch(nextX, nextY, startX, startY, d);
			dessertSet.remove(map[nextX][nextY]);
			visited[nextX][nextY] = false;
		}
	}

}
