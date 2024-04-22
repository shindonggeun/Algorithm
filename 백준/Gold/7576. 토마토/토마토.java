import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보를 담고있는 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int M;	// 가로 칸 수
	static int N;	// 세로 칸 수
	static int[][] map;	// 토마토 상자의 상태를 담고있는 배열
	static boolean[][] visited;	// 방문 배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<Position> queue;	// 너비우선탐색을 이용하기 위한 큐 선언
	static int maxTomato;	// 모든 토마토가 익는데 걸리는 최대 일수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		queue = new LinkedList<>();	// 큐 생성 및 초기화
		maxTomato = Integer.MIN_VALUE;	// 최대 일수를 저장하기 위해 일단 최소값으로 초기화
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 해당 좌표가 토마토가 익은 경우(1)
				if (map[i][j] == 1) {
					// 큐에 현재 좌표정보 추가
					queue.add(new Position(i, j));
				}
			}
		}
		
		int minDay = bfs();	// 너비우선탐색 메서드 실행하여 토마토가 익는데 최소 일수 계산
		System.out.println(minDay);
	}
	
	// 너비우선탐색 메서드
	public static int bfs() {
		while (!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			visited[nowX][nowY] = true;
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 이미 방문처리 됐거나 또는 토마토가 들어있지 않은 경우(-1)
				if (visited[nextX][nextY] || map[nextX][nextY] == -1) {
					continue;	// 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 익지 않은 토마토인 경우(0)
				if (map[nextX][nextY] == 0) {
					map[nextX][nextY] = map[nowX][nowY] + 1;	// 익은 토마토로 변경한 뒤 일수 증가
					queue.add(new Position(nextX, nextY));	// 탐색한 좌표 정보 큐에 추가
				}
			}
		}
		
		// 토마토가 모두 익은 경우
		if (checkAllTomato()) {
			// 최대 일수에서 - 1 빼준 값 반환
			return maxTomato - 1;
		}
		else {
			// 토마토가 모두 익지 않은 경우 -1 반환
			return -1;	
		}
		
	}
	
	// 토마토가 모두 익었는지 체크하는 메서드
	public static boolean checkAllTomato() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				// 해당 좌표에서 토마토가 익지 않은 경우(0)
				if (map[i][j] == 0) {
					return false;	// 토마토 모두 익지 않았음
				}
				
				// 최대 일수 갱신
				maxTomato = Math.max(maxTomato, map[i][j]);
			}
		}
		
		// 모든 토마토가 익었음
		return true;
	}

}
