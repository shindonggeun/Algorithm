import java.util.*;
import java.io.*;

public class Main {
	
	// 토마토의 좌표 정보를 담고 있는 내부 클래스
	static class Tomato {
		int x;
		int y;
		
		public Tomato(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int M; // 상자의 가로 칸의 수
	static int N; // 상자의 세로 칸의 수
	static int[][] map;
	static boolean[][] visited; // 2차원 방문 배열
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<Tomato> queue; // 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성 (익은 토마토의 초기 좌표 정보 담기)
	static int maxValue; // 상자에서 해당 좌표의 토마토가 익기까지 걸리는 시간(값) 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M];
		queue = new LinkedList<>(); // 큐 생성
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 해당 좌표가 익은 토마토(1)인 경우
				if (map[i][j] == 1) {
					queue.add(new Tomato(i, j)); // 큐에 해당 익은 토마토 좌표 정보 저장
					visited[i][j] = true; // 해당 좌표 방문 처리
				}
			}
		}
		
		int minDay = tomatoSpread(); // 너비우선탐색 메서드 호출해서 토마토 익게끔 하기
		
		System.out.println(minDay); // 모든 토마토가 익는데 걸리는 최소 시간 출력
	}
	
	// 초기 익은 토마토부터 시작해서 상자의 모든 토마토를 익게끔 만들게 하는 메서드 (너비우선탐색 메서드)
	public static int tomatoSpread() {
		while (!queue.isEmpty()) {
			// 큐에서 현재 토마토의 좌표 정보 뽑아냄
			Tomato now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 토마토가 들어있지 않은 칸(-1)인 경우
				if (visited[nextX][nextY] || map[nextX][nextY] == -1) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 익지 않은 토마토(0)인 경우
				if (map[nextX][nextY] == 0) {
					queue.add(new Tomato(nextX, nextY)); // 큐에 탐색한 좌표(토마토 정보) 저장
					visited[nextX][nextY] = true; // 탐색한 좌표 방문 처리
					map[nextX][nextY] = map[nowX][nowY] + 1; // 맵의 현재 
				}
			}
		}
		
		// 위의 너비우선탐색 과정 다 끝났으면 현재 상자내에서 모든 토마토가 익었는지 확인
		// 모든 토마토가 익은 경우
		if (checkAllTomato()) {
			// 현재 상자내의 최대값에서 - 1한 값 반환 (즉, 모든 토마토가 익기까지 걸리는 시간 - 1)
			// 걸리는 시간을 1초부터 시작했으므로 -1 해줘야함
			return maxValue - 1;
		}
		
		// 모든 토마토가 익지 않은 경우 -1 반환
		return -1;
	}
	
	// 상자내의 모든 토마토가 익었는지 확인하는 메서드
	public static boolean checkAllTomato() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				// 해당 좌표가 익지 않은 토마토(0)인 경우
				if (map[i][j] == 0) {
					return false; // 모든 토마토 익지 않았음을 나타내는 false 반환
				}
				
				// 상자 내의 최대값 갱신 (즉, 모든 토마토가 익기까지 걸리는 시간) 
				maxValue = Math.max(maxValue, map[i][j]);
			}
		}
		
		// 모든 토마토 익었음
		return true;
	}

}