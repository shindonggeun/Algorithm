import java.util.*;
import java.io.*;

public class Main {
	
	// 구름의 좌표 정보를 담고 있는 내부 클래스
	static class Cloud {
		int x;
		int y;
		
		public Cloud(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N; // 격자 크기
	static int M; // 이동 명령 개수
	static int[][] map; // 지도 격자
	static boolean[][] visited; // 2차원 방문 배열
	// 8가지 방향 배열 (방향없음, 좌, 좌 + 상, 상, 우 + 상, 우, 우 + 하, 하, 좌 + 하) -> 1번 인덱스부터 시작
	static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static List<Cloud> cloudList; // 구름들의 정보를 담고 있는 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N]; // [0][0] ~ [N-1][N-1]
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cloudList = new ArrayList<>(); // 리스트 초기화
		
		// 초기 생성되는 비구름 리스트에 저장
		cloudList.add(new Cloud(N-1, 0));
		cloudList.add(new Cloud(N-1, 1));
		cloudList.add(new Cloud(N-2, 0));
		cloudList.add(new Cloud(N-2, 1));
		
		// M번의 이동 명령 처리
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken()); // 이동 방향 입력
			int move = Integer.parseInt(st.nextToken()); // 이동 거리 입력
			
			visited = new boolean[N][N]; // 방문 배열 초기화 [0][0] ~ [N-1][N-1]
			
			moveCloud(dir, move); // 구름 이동 시키기 실행
			raining(); // 비 내리기 실행
			waterCopyBug(); // 물복사 버그 마법 실행
			generateCloud(); // 새로운 구름 생성하기
		}
		
		int result = getTotalWater(); // 최종 물의 양 계산하기
		
		System.out.println(result);
	}
	
	// 구름을 움직이게 하는 메서드
	public static void moveCloud(int dir, int move) {
		List<Cloud> newCloudList = new ArrayList<>(); // 새로운 구름 리스트 선언 및 초기화
		
		// 현재 구름 리스트 탐색
		for (Cloud cloud: cloudList) {
			// 이동 시킨 후 x, y 좌표 계산 과정
			int nextX = (cloud.x + dx[dir] * move) % N;
			int nextY = (cloud.y + dy[dir] * move) % N;
			
			// 탐색한 x 좌표가 음수인 경우
			if (nextX < 0) {
				nextX += N; // N을 더해 양수로 변환
			}
			
			// 탐색한 y 좌표가 음수인 경우
			if (nextY < 0) {
				nextY += N; // N을 더해 양수로 변환
			}
			
			newCloudList.add(new Cloud(nextX, nextY)); // 탐색한 위치의 구름을 새로운 구름 리스트에 추가
		}
		
		cloudList = newCloudList; // 새로운 구름 리스트를 현재 구름 리스트로 업데이트
	}
	
	// 비를 내리게 하는 메서드
	public static void raining() {
		// 현재 구름 리스트 탐색
		for (Cloud cloud: cloudList) {
			map[cloud.x][cloud.y]++; // 구름이 있는 위치에 비 내리게 하기
			visited[cloud.x][cloud.y] = true; // 구름이 있었다는 표시 (방문 처리)
		}
	}
	
	// 물복사 버그 마법을 실행하는 메서드
	public static void waterCopyBug() {
		// 현재 구름 리스트 탐색
		for (Cloud cloud: cloudList) {
			int waterCount = 0; // 대각선 방향의 물의 양을 세기 위한 변수 0으로 초기화
			// 대각선 방향 탐색 (좌상, 우상, 우하, 좌하)
			for (int i=2; i<=8; i+=2) {
				int nextX = cloud.x + dx[i];
				int nextY = cloud.y + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표의 격자값이 0보다 큰 경우 (즉, 물이 있는 경우)
				if (map[nextX][nextY] > 0) {
					waterCount++; // 물의 양 증가
				}
			}
			
			map[cloud.x][cloud.y] += waterCount; // 물복사 버그 마법 실행
		}
	}
	
	// 물의 양이 2 이상인 칸에 구름을 생기게 하는 메서드
	public static void generateCloud() {
		List<Cloud> newCloudList = new ArrayList<>(); // 새로운 구름 리스트 선언 및 초기화
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 물의 양의 2 이상이면서 방문 처리 안된 좌표인 경우 (즉, 이전에 구름이 없었던 칸인 경우)
				if (map[i][j] >= 2 && !visited[i][j]) {
					map[i][j] -= 2; // 구름이 생겼으므로 해당 칸의 물의 양 2만큼 줄여줌
					newCloudList.add(new Cloud(i, j)); // 해당 좌표값을 가지는 새로운 구름 추가
				}
			}
		}
		
		cloudList = newCloudList; // 새로운 구름 리스트를 현재 구름 리스트로 업데이트
	}
	
	// 최종 물의 양을 계산하는 메서드
	public static int getTotalWater() {
		int total = 0; // 총 물의 양 0으로 초기화
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				total += map[i][j]; // 각 칸의 물의 양을 더해줌
			}
		}
		
		return total; // 총 물의 양 반환
	}

}