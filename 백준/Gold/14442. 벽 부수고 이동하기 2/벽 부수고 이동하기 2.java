import java.util.*;
import java.io.*;

public class Main {

	static class Position {
		int x, y;
		int distance;
		int crack;
		
		public Position(int x, int y, int distance, int crack) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.crack = crack;
		}
	}
	
	static int N;	// 세로 개수 (y좌표)
	static int M;	// 가로 개수 (x좌표)
	static int K;	// 벽 부순 개수
	static char[][] map;	
	static boolean[][][] visited;	// 방문여부 배열(3차원 배열로 선언해서 몇번 벽 부쉈는지)
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열(상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열(좌, 우)
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];	// (0, 0) ~ (M-1, N-1)
		// K+1 잊지말자!
		visited = new boolean[K+1][N][M];	// 방문배열 3차원 배열로 생성 ([0] -> 한번도 안부숨, ... ,[K] -> 벽 K번 부숨) 
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		int minDistance = bfs(0, 0);
		System.out.println(minDistance);
	}
	
	public static int bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0, 0));
		visited[0][startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDistance = now.distance;
			int nowCrack = now.crack;
			
			// 목표지점에 도달한 경우
			if(nowX == N-1 && nowY == M-1) {
				return nowDistance + 1;
			}
			
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// (0, 0) ~ (M-1, N-1) 이외의 좌표를 탐색한 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 탐색한 좌표가 이동할 수 있는 좌표('0')인 경우
				if(map[nextX][nextY] == '0') {
					// 방문하지 않은 좌표인 경우
					if(!visited[nowCrack][nextX][nextY]) {
						queue.add(new Position(nextX, nextY, nowDistance + 1, nowCrack));
						visited[nowCrack][nextX][nextY] = true;
					}
					
					
				}
				// 탐색한 좌표가 이동할 수 없는 좌표('1')인 경우
				else {
					// 벽 부순횟수가 K 미만이면서 방문하지 않은 좌표인 경우 (해당 좌표 벽 부수기 가능한 경우)
					if(nowCrack < K && !visited[nowCrack + 1][nextX][nextY]) {
						queue.add(new Position(nextX, nextY, nowDistance + 1, nowCrack + 1));
						visited[nowCrack + 1][nextX][nextY] = true;
					}
				}
				
			}
		}
		// 위의 경우 다 탐색했는데 도착 좌표까지 갈 수 없는 경우 -1 반환
		return -1;
	}

}
