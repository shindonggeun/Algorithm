import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보 및 이동한 칸 수를 담고있는 내부 클래스
	static class Position {
		int x;
		int y;
		int distance;	// 이동한 칸수 (거리)
		
		public Position(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	static int N;	// 세로 크기
	static int M;	// 가로 크기
	static int[][] map;
	static boolean[][] visited;	// 각 좌표 방문여부를 나타내는 방문배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		// 시작위치([0][0])에서 도착위치([N-1][M-1])까지의 최소 이동 거리 구하기
		int resultDistance = bfs(0, 0);	
		System.out.println(resultDistance);
	}
	
	// 시작위치에서 도착위치까지의 최소 이동거리를 구하는 너비우선탐색 메서드
	public static int bfs(int startX, int startY) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 1));	// 시작좌표 정보 및 이동거리 큐에 저장
		visited[startX][startY] = true;	// 시작 위치 방문처리
		
		// 너비우선탐색 실시
		while(!queue.isEmpty()) {
			// 현재 좌표 정보 뽑아내기
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			
			// 현재 좌표 정보가 도착위치([N-1][M-1])에 도달한 경우 
			if(nowX == N-1 && nowY == M-1) {
				return nowDistance;	// 현재까지의 이동 거리 반환
			}
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 이동할수 없는 칸(0)인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표 정보 큐에 저장
				queue.add(new Position(nextX, nextY, nowDistance + 1));
				visited[nextX][nextY] = true;	// 탐색한 좌표 방문 처리
			}
		}
		
		// 너비우선탐색을 실시했는데도 도착위치로 이동할 수 없는 경우 -1 반환 (문제에서는 항상 도착위치로 이동함)
		return -1;
	}

}
