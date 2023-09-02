import java.util.*;
import java.io.*;

public class Main {
	
	// 토마토의 좌표 정보를 담고있는 내부 클래스
	static class Tomato {
		int z;
		int x;
		int y;
		
		public Tomato(int z, int x, int y) {
			this.z = z;
			this.x = x;
			this.y = y;
		}
	}
	
	static int M;	// 가로 칸 수
	static int N;	// 세로 칸 수
	static int H;	// 상자의 수
	static int[][][] map;
	static boolean[][][] visited;	// 3차원 방문 배열
	// 6가지 방향 배열 (상, 하, 좌, 우, 위 (z좌표만 이동), 아래 (z좌표만 이동))
	static int[] dx = {1, -1, 0, 0, 0, 0};	// x축과 z축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1, 0, 0};	// y축과 z축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	static int[] dz = {0, 0, 0, 0, 1, -1};	// x축과 y축이 고정되어 있을 때 z좌표가 움직이는 방향 배열
	static Queue<Tomato> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());	// 가로 칸 수 입력
		N = Integer.parseInt(st.nextToken());	// 세로 칸 수 입력
		H = Integer.parseInt(st.nextToken());	// 상자의 수 입력
		
		map = new int[H][N][M];	// [0][0][0] ~ [H-1][N-1][M-1]
		visited = new boolean[H][N][M];
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					// 맵에서 해당 좌표가 익은 토마토 칸(1)인 경우
					if(map[i][j][k] == 1) {
						queue.add(new Tomato(i, j, k));	// 큐에 저장
					}
				}
			}
		}
		
		int minDay = bfs();
		System.out.println(minDay);
	}
	
	// 너비우선탐색 메서드
	public static int bfs() {
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			Tomato now = queue.poll();
			int nowZ = now.z;
			int nowX = now.x;
			int nowY = now.y;
			
			// 6가지 방향 탐색
			for(int i=0; i<6; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				int nextZ = nowZ + dz[i];
				
				// 탐색한 좌표가 [0][0][0] ~ [H-1][N-1][M-1] 이외의 좌표인 경우
				if(nextZ < 0 || nextX < 0 || nextY < 0 || nextZ >= H || nextX >= N || nextY >= M) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 토마토가 들어있지 않은 칸(-1)인 경우
				if(visited[nextZ][nextX][nextY] || map[nextZ][nextX][nextY] == -1) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 익지않은 토마토(0)인 경우
				if(map[nextZ][nextX][nextY] == 0) {
					map[nextZ][nextX][nextY] = map[nowZ][nowX][nowY] + 1;	// 맵에 토마토 익게끔 걸린 시간 저장
					visited[nextZ][nextX][nextY] = true;	// 탐색한 좌표 방문처리 (토마토 익었음)
					queue.add(new Tomato(nextZ, nextX, nextY));	// 큐에 탐색한 좌표 저장
				}
			}
		}
		
		// 위의 너비우선탐색 과정 다 거쳤으면 map에서 최대값을 뽑아내야 한다
		// 토마토가 모두 익은 경우
		if(checkZeroTomato()) {
			int day = findday();
			return day-1;
		}
		// 그 이외의 경우는 -1 반환하게끔 (즉, 토마토가 모두 익지 않은 상황이면)
		return -1;
		
	}
	
	// 토마토가 모두 익었는지 안익었는지 판단해주는 메서드
	public static boolean checkZeroTomato() {
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					// 토마토가 익지 않았으면(0)
					if(map[i][j][k] == 0) {
						return false;	// false 반환
					}
				}
			}
		}
		
		// 토마토가 모두 익었으면 true 반환
		return true;
	}
	
	// 토마토가 모두 익었을 때 맵에서 익은지 며칠 됐는지 확인해주는 메서드
	public static int findday() {
		int maxDay = Integer.MIN_VALUE;
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					maxDay = Math.max(maxDay, map[i][j][k]);
				}
			}
		}
		
		return maxDay;
	}

}
