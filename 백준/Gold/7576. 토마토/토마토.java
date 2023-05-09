import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x, y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int M;	// 가로 칸 수(x좌표)
	static int N;	// 세로 칸 수(y좌표)
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 (상, 하, 좌, 우) 
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	static Queue<Position> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 토마토가 들어있는 칸이면 큐에 집어넣음(너비우선탐색 돌리기 위해)
				if(map[i][j] == 1) {
					queue.add(new Position(i, j));
				}
			}
		}
		int minDays = bfs();
		
		System.out.println(minDays);
	}
	
	public static int bfs() {
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			visited[nowX][nowY] = true;
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (M-1, N-1) 이외의 좌표인 경우 넘어감
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문한 좌표거나 토마토가 들어있지 않은 칸(-1)인 경우
				if(visited[nextX][nextY] || map[nextX][nextY] == -1) {
					continue;
				}
				
				// 익지않은 토마토만 탐색해줘야한다!!
				if(map[nextX][nextY] == 0) {
					map[nextX][nextY] = map[nowX][nowY] + 1;
					visited[nextX][nextY] = true;
					queue.add(new Position(nextX, nextY));
				}
				
			}
		}
		
		// 위의 탐색과정 다 거치면 map에서 최대값을 뽑아내야 함
		// 만약 map에서 0이 존재하면 -1을 출력해야함
		if(!checkZero(map)) {
			return -1;
		}
		else {
			int max = findMax(map);
			return max-1;
		}
	}
	
	// map에서 0이 존재하는지 여부를 판단해주는 메서드
	public static boolean checkZero(int[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 토마토가 익지 않은게 존재하면 false
				if(map[i][j] == 0) {
					return false;
				}
			}
		}
		// 위 과정 다 탐색했는데도 0 없으면 true (토마토 모두 익은 상태임)
		return true;
	}
	
	// 2차원 배열에서 최대값 뽑아내는 메서드
	public static int findMax(int[][] map) {
		int max = Integer.MIN_VALUE;
		
		for(int[] array: map) {
			for(int element: array) {
				max = Math.max(max, element);
			}
		}
		return max;
	}

}
