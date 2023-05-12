import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int z, x, y;
		
		public Position(int z, int x, int y) {
			this.z = z;
			this.x = x;
			this.y = y;
		}
	}
	
	// 3차원 배열
	static int N;	// 가로 길이 (x 좌표)
	static int M; 	// 세로 길이 (y 좌표)
	static int H;	// 높이 (z 좌표)
	static int[][][] map;
	static boolean[][][] visited;
	static Queue<Position> queue = new LinkedList<>();
	// 6가지 방향 탐색 (상, 하, 좌, 우, 위(z 좌표만 이동), 아래(z 좌표만 이동) )
	static int[] dx = {1, -1, 0, 0, 0, 0};	// x축과 z축이 고정되어 있을 때 y좌표만 이동하는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1, 0, 0};	// y축과 z축이 고정되어 있을 때 x좌표만 이동하는 방향 배열 (좌, 우)
	static int[] dz = {0, 0, 0, 0, 1, -1};	// x축과 y축이 고정되어 있을 때 z좌표만 이동하는 방향 배열 (위, 아래)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][M][N];	// (0, 0, 0) ~ (N-1, M-1, H-1)
		visited = new boolean[H][M][N];
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<M; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<N; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					// 처음에 토마토가 익은(1) 위치 queue에 저장 (토마토가 익은 칸들 너비우선탐색 돌리기 위해)
					if(map[i][j][k] == 1) {
						queue.add(new Position(i, j, k));
					}
				}
			}
		}
		
		int minDays = bfs();
		System.out.println(minDays);
	}
	
	public static int bfs() {
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowZ = now.z;
			int nowX = now.x;
			int nowY = now.y;
			
			// 6가지 방향 탐색
			for(int i=0; i<6; i++) {
				int nextZ = nowZ + dz[i];
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0, 0) ~ (N-1, M-1, H-1) 이외의 좌표인 경우
				if(nextZ < 0 || nextX < 0 || nextY < 0 || nextZ >= H || nextX >= M || nextY >= N) {
					continue;
				}
				
				if(visited[nextZ][nextX][nextY] || map[nextZ][nextX][nextY] == -1) {
					continue;
				}
				
				// 익지않은 토마토만 탐색해줘야한다!!
				if(map[nextZ][nextX][nextY] == 0) {
					map[nextZ][nextX][nextY] = map[nowZ][nowX][nowY] + 1;
					visited[nextZ][nextX][nextY] = true;
					queue.add(new Position(nextZ, nextX, nextY));
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
	public static boolean checkZero(int[][][] map) {
		for(int i=0; i<H; i++) {
			for(int j=0; j<M; j++) {
				for(int k=0; k<N; k++) {
					// 토마토가 익지 않은게 존재하면 false
					if(map[i][j][k] == 0) {
						return false;
					}
				}
			}
		}
		// 위 과정 다 탐색했는데도 0 없으면 true (토마토 모두 익은 상태임)
		return true;
	}
	// 3차원 배열에서 최대값 뽑아내는 메서드
	public static int findMax(int[][][] map) {
		int max = Integer.MIN_VALUE;
			
		for(int[][] twoArray: map) {
			for(int[] oneArray: twoArray) {
				for(int element: oneArray) {
					max = Math.max(max, element);
				}
			}
		}
		return max;
	}

}
