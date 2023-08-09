import java.util.*;
import java.io.*;

public class Solution {

	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int[][] map;
//	static boolean[][] visited;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	static int maxMoveCount;	// 방 이동 최대 횟수
	static int minNum;	// 방 번호 최소값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];	// (0, 0)  ~ (N-1, N-1)
//			visited = new boolean[N][N];	
			maxMoveCount = 0;	
			minNum = Integer.MAX_VALUE;		
			
			// 맵에 방번호값들 입력받기
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					bfs(i, j);
				}
			}
			
			sb.append(minNum).append(" ").append(maxMoveCount).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
//		visited[startX][startY] = true;	// 시작좌표 방문처리
		int moveCount = 1;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowValue = map[nowX][nowY];
			
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (N-1, N-1) 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
//				// 탐색한 좌표가 방문한 경우
//				if(visited[nextX][nextY]) {
//					continue;
//				}
				
				// 탐색한 좌표의 방 번호값과 현재좌표의 방번호값의 차이가 1인 경우
				if(map[nextX][nextY] == nowValue + 1) {
					queue.add(new Position(nextX, nextY));
//					visited[nextX][nextY] = true;	// 방문처리
					moveCount++;
				}
			}
		}
		
		// 방 이동한 횟수가 방을 최대로 이동한 횟수보다 큰 경우 
		if(moveCount > maxMoveCount) {
			minNum = map[startX][startY];	// 방번호 최소값 갱신해주기
			maxMoveCount = moveCount;	// 방 최대로 이동한 횟수 갱신해주기
		}
		// 방 이동한 횟수가 방을 최대로 이동한 횟수와 같은 경우
		else if(moveCount == maxMoveCount) {
			minNum = Math.min(minNum, map[startX][startY]);	// 방번호 최소값 갱신해주기
		}
	}

}
