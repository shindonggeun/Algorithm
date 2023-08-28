import java.util.*;
import java.io.*;

public class Main {
	
	// 해당 좌표의 정보를 저장해주는 내부 클래스
	static class Position {
		int x;
		int y;
		int distance;
		
		public Position(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	static int N;
	static boolean[][] visited;	// 2차원 방문 배열
	// 6가지 방향 탐색
	static int[] dx = {-2, -2, 0, 0, 2, 2};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {-1, 1, -2, 2, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];	// [0][0] ~ [N-1][N-1]
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());
		int endX = Integer.parseInt(st.nextToken());
		int endY = Integer.parseInt(st.nextToken());
		
		int resultDistance = bfs(startX, startY, endX, endY);
		System.out.println(resultDistance);
	}
	
	// 너비우선탐색 메서드
	public static int bfs(int startX, int startY, int endX, int endY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));	// 큐에 시작좌표 정보 집어넣음
		visited[startX][startY] = true;	// 시작좌표 방문처리
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			// 해당 좌표가 도착지점에 도달한 경우
			if(nowX == endX && nowY == endY) {
				return nowDistance;	// 현재 이동거리 (최소 이동횟수) 반환
			}
			
			// 6가지 방향 탐색
			for(int i=0; i<6; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문한 좌표인 경우
				if(visited[nextX][nextY]) {
					continue;	// 넘어감
				}
				
				queue.add(new Position(nextX, nextY, nowDistance + 1));	// 탐색한 좌표 정보 큐에 집어넣음 (거리 1 증가)
				visited[nextX][nextY] = true;	// 탐색한 해당 좌표 방문처리
			}
		}
		return -1;
	}

}
