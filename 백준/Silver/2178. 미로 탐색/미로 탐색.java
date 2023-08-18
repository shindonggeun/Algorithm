import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 및 이동한 거리를 저장해주는 내부 클래스
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
	
	static int N;	// 행의 개수
	static int M;	// 열의 개수
	static int[][] map;
	static boolean[][] visited;
	
	// 4가지 방향 배열 (상, 하, 좌, 우) -> 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1) -> [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		int minDistance = bfs(0, 0);	// 시작위치 (0, 0)부터 너비우선탐색 시작
		System.out.println(minDistance);
	}
	
	// 너비우선탐색 메서드
	public static int bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 1));	// 큐에 해당 시작좌표와 거리 정보 넣음
		visited[startX][startY] = true;	// 시작좌표 방문처리
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			// 끝 좌표에 도달한 경우
			if(nowX == N-1 && nowY == M-1) {
				return nowDistance;	// 이동한 거리 반환
			}
			
			// 4가지 방향 탐색 (상, 하, 좌, 우) -> 배열에서는 하, 상, 좌, 우
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (M-1, N-1) -> [0][0] ~ [N-1][M-1] 이외의 좌표를 탐색한 경우 
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 넘어감
				}
				
				// 이미 방문한 좌표거나 또는 이동할 수 없는 칸(0)인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;	// 넘어감
				}
				
				queue.add(new Position(nextX, nextY, nowDistance+1));
				visited[nextX][nextY] = true;	// 탐색한 좌표 방문처리
			}
			
		}
		
		// 너비우선탐색을 다 거쳤는데도 끝 좌표에 도달하지 못한 경우 0 반환 
		// 근데 문제에서 도착위치로 이동할 수 있는 경우만 입력으로 주어진다고 했으므로 여기 과정은 거치지 않는다
		return 0;
	}

}
