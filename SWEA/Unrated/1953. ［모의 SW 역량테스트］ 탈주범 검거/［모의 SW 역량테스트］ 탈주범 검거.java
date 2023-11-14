import java.util.*;
import java.io.*;

public class Solution {
	
	static class Position {
		int x;
		int y;
		int direction;
		int time;
		
		public Position(int x, int y, int direction, int time) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.time = time;
		}
	}
	
	static int N;	// 세로 크기
	static int M;
	static int[][] map;
	static int R;
	static int C;
	static int L;
	static int[][] visited;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	// 터널 구조물 타입(모양)에 따른 방향 정해주기
	// 상, 하, 좌, 우 -> 방향배열에서 인덱스 0, 1, 2, 3
	static int[][] tunnelDirection = {
			{0},
			{0, 1, 2, 3},	// 구조물 타입 1일 때 상, 하, 좌, 우
			{0, 1},			// 구조물 타입 2일 때 상, 하
			{2, 3},			// 구조물 타입 3일 때 좌, 우
			{0, 3},			// 구조물 타입 4일 때 상, 우
			{1, 3},			// 구조물 타입 5일 때 하, 우
			{1, 2},			// 구조물 타입 6일 때 하, 좌
			{0, 2}			// 구조물 타입 7일 때 상, 좌
	};
	static int possiblePositionCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];	// [0][0] ~ [N-1][M-1]
			visited = new int[N][M];
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			possiblePositionCount = 0;
			bfs();
			positionCounting();
			sb.append("#").append(tc).append(" ").append(possiblePositionCount).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void bfs() {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(R, C, map[R][C], 1));
		visited[R][C] = 1;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDir = now.direction;
			int nowTime = now.time;
			
			if(nowTime == L) {
				return;
			}
			
			for(int d=0; d<tunnelDirection[nowDir].length; d++) {
				int nextDir = tunnelDirection[nowDir][d];
				int nextX = nowX + dx[nextDir];
				int nextY = nowY + dy[nextDir];
				
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					 continue;
				}
				
				if(map[nextX][nextY] == 0 || visited[nextX][nextY] != 0) {
					continue;
				}
				
				int nextTurnelType = map[nextX][nextY];	// 다음 위치의 터널 타입
				
				// 상
				if(nextDir == 0) {
					// 3, 4, 7
					if(nextTurnelType == 3 || nextTurnelType == 4 || nextTurnelType == 7) {
						continue;
					}
				}
				// 하
				else if(nextDir == 1) {
					// 3, 5, 6
					if(nextTurnelType == 3 || nextTurnelType == 5 || nextTurnelType == 6) {
						continue;
					}
				}
				// 좌
				else if(nextDir == 2) {
					// 2, 6, 7
					if(nextTurnelType == 2 || nextTurnelType == 6 || nextTurnelType == 7) {
						continue;
					}
				}
				// 우
				else if(nextDir == 3) {
					// 2, 4, 5
					if(nextTurnelType == 2 || nextTurnelType == 4 || nextTurnelType == 5) {
						continue;
					}
				}
				
				visited[nextX][nextY] = nowTime + 1;
				queue.add(new Position(nextX, nextY, map[nextX][nextY], nowTime + 1));
			}
			
		}
	}
	
	public static void positionCounting() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j] > 0 && visited[i][j] <= L) {
					possiblePositionCount++; // 가능한 위치의 개수를 증가
				}
			}
		}
	}
	
	

}
