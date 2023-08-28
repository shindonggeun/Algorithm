import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표정보를 담은 내부 클래스
	static class Position {
		int x;
		int y;
		int distance;	// 이동거리
		int crack;	// 벽 부순 횟수
		
		public Position(int x, int y, int distance, int crack) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.crack = crack;
		}
	}
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][][] visited;
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// [0][0] ~ [N-1][M-1]
		visited = new boolean[2][N][M];	// 3차원 방문배열 => [0] = 마법의 지팡이 사용 X, [1] = 마법의 지팡이 사용
		
		// 시작 좌표 입력
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken()) - 1;
		int startY = Integer.parseInt(st.nextToken()) - 1;
		
		// 도착 좌표 입력
		st  = new StringTokenizer(br.readLine());
		int endX = Integer.parseInt(st.nextToken()) - 1;
		int endY = Integer.parseInt(st.nextToken()) - 1;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int resultDistance = bfs(startX, startY, endX, endY);
		System.out.println(resultDistance);
	}
	
	// 너비우선 탐색 메서드
	public static int bfs(int startX, int startY, int endX, int endY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0, 0));	// 큐에 해당 좌표정보와 이동거리 및 벽 부순횟수 저장
		visited[0][startX][startY] = true;	// 벽 부수지 않은 상태에서의 시작좌표 방문처리
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			Position now = queue.poll();	// 좌표정보 큐에서 뽑기
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			int nowCrack = now.crack;
			
			// 도착지점에 도달한 경우
			if(nowX == endX && nowY == endY) {
				return nowDistance;	// 현재 이동거리 반환
			}
			
			// 4가지 방향 탐색 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표를 탐색한 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 빈칸(0)인 경우
				if(map[nextX][nextY] == 0) {
					// 방문하지 않은 좌표인 경우
					if(!visited[nowCrack][nextX][nextY]) {
						queue.add(new Position(nextX, nextY, nowDistance+1, nowCrack));
						visited[nowCrack][nextX][nextY] = true;
					}
				}
				
				// 탐색한 좌표가 벽(1)인 경우
				if(map[nextX][nextY] == 1) {
					// 벽 부순횟수가 1 미만이면서 동시에 벽을 부쉈을 때 방문하지 않은 좌표인 경우
					if(nowCrack < 1 && !visited[nowCrack+1][nextX][nextY]) {
						queue.add(new Position(nextX, nextY, nowDistance+1, nowCrack+1));
						visited[nowCrack+1][nextX][nextY] = true;
					}
				}
				
			}
		}
		
		// 위의 너비우선탐색으로 경우 다 탐색했는데도 도착하지 못한 경우 -1 반환
		return -1;
	}

}
