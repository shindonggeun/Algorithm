import java.util.*;
import java.io.*;

public class Main {
	
	// 토마토의 좌표 정보를 담고 있는 내부 클래스
	static class Tomato {
		int z;	// z 좌표
		int x;	// x 좌표
		int y;	// y 좌표
		
		public Tomato(int z, int x, int y) {
			this.z = z;
			this.x = x;
			this.y = y;
		}
	}
	
	static int M;	// 가로 칸 수
	static int N;	// 세로 칸 수
	static int H;	// 상자의 수 (높이)
	static int[][][] map;	// [높이][세로][가로]
	static boolean[][][] visited;
	// 6가지 방향 배열 (위(z), 아래(z), 왼쪽(y), 오른쪽(y), 앞(x), 뒤(x))
	static int[] dz = {-1, 1, 0, 0, 0, 0};
	static int[] dx = {0, 0, 0, 0, 1, -1};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static Queue<Tomato> queue;	
	static int maxDay;	// 토마토가 모두 익는데 걸리는 시간 (최소 시간)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M];
		visited = new boolean[H][N][M];
		queue = new LinkedList<>();
		
		for (int z=0; z<H; z++) {
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<M; j++) {
					map[z][i][j] = Integer.parseInt(st.nextToken());
					// 해당 좌표가 익은 토마토(1)인 경우
					if (map[z][i][j] == 1) {
						// 큐에 토마토 정보 저장
						queue.add(new Tomato(z, i, j));
						visited[z][i][j] = true;	// 해당 좌표 방문 처리
					}
				}
			}
		}
		
		
		maxDay = Integer.MIN_VALUE;	// 토마토가 모두 익는데 걸리는 시간 최소값으로 일단 초기화
		int result = bfs();	// 너비우선탐색 실시
		
		System.out.println(result);
	}
	
	// 토마토가 익히기 위해 너비우선탐색 알고리즘을 이용한 메서드
	public static int bfs() {
		// 너비우선탐색 실시
		while (!queue.isEmpty()) {
			// 현재 토마토 좌표 정보 큐에서 뽑아내기
			Tomato now = queue.poll();
			int nowZ = now.z;
			int nowX = now.x;
			int nowY = now.y;
			
			// 6가지 방향 배열 탐색
			for (int i=0; i<6; i++) {
				int nextZ = nowZ + dz[i];
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0][0] ~ [H-1][N-1][M-1] 이외의 좌표인 경우
				if (nextZ < 0 || nextX < 0 || nextY < 0 || nextZ >= H || nextX >= N || nextY >= M) {
					continue;	// 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 이미 방문처리 되어있거나 또는 토마토가 들어있지 않은 칸(-1)인 경우
				if (visited[nextZ][nextX][nextY] || map[nextZ][nextX][nextY] == -1) {
					continue;	// 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 익지 않은 토마토(0)인 경우
				if (map[nextZ][nextX][nextY] == 0) {
					// 탐색한 좌표 정보에 현재 좌표에서 토마토를 익히는데 걸린 시간 + 1 해서 갱신
					map[nextZ][nextX][nextY] = map[nowZ][nowX][nowY] + 1;
					visited[nextZ][nextX][nextY] = true;	// 탐색한 좌표 방문 처리
					queue.add(new Tomato(nextZ, nextX, nextY));	// 큐에 토마토 정보 저장
				}
			}
		}
		
		// 너비우선탐색이 끝난 경우 토마토 모두 익었는지 확인
		// 토마토가 모두 익은 경우
		if (checkAllTomato()) {
			// 토마토가 모두 익는데 걸리는 시간 반환
			return maxDay - 1;
		}
		
		// 토마토가 모두 익지 않은 경우 -1 반환
		return -1;
		
	}
	
	// 토마토가 모두 익었는지 확인해면서 동시에 토마토가 모두 익는데 걸리는 시간을 계산해주는 메서드
	public static boolean checkAllTomato() {
		for (int z=0; z<H; z++) {
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					// 탐색한 좌표에 있는 토마토가 익지 않은(0) 경우
					if (map[z][i][j] == 0) {
						return false;	// 토마토 모두 익지 않았음을 반환
					}
					maxDay = Math.max(maxDay, map[z][i][j]);	// 토마토가 모두 익는데 걸리는 시간 갱신
				}
			}
		}
		
		return true;	// 토마토 모두 익었음을 반환
	}

}