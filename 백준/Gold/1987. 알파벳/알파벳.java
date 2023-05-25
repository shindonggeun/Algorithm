import java.util.*;
import java.io.*;

public class Main {

	static int R;
	static int C;
	static char[][] map;
	static boolean[] visited = new boolean[26];	// 0: 'A', ... , 25: 'Z'
	// 4가지 방향 탐색 (쌍, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	static int maxCount = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());	// 세로 길이 (y좌표)
		C = Integer.parseInt(st.nextToken());	// 가로 길이 (x좌표)
		map = new char[R][C];	// (0, 0) ~ (C-1, R-1)
		
		for(int i=0; i<R; i++) {
			String input = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		dfs(0, 0, 0);	// (0, 0) 좌표부터 탐색 시작
		System.out.println(maxCount);
	}
	
	public static void dfs(int startX, int startY, int count) {
		// 해당 알파벳이 이미 나온 경우
		if(visited[map[startX][startY] - 'A']) {
			maxCount = Math.max(maxCount, count);
			return;
		}
		else {
			visited[map[startX][startY] - 'A'] = true;	// 해당 알파벳 사용 처리
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = startX + dx[i];
				int nextY = startY + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
					continue;
				}
				
				dfs(nextX, nextY, count + 1);
			}
			
			// 탐색 다 했으면 다시 해당 알파벳 사용처리 해제 (백트래킹)
			visited[map[startX][startY] - 'A'] = false;
		}
	}

}
