import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보를 담고 있는 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int R; // 맵의 세로 크기
	static int C; // 맵의 가로 크기
	static int T; // 가희가 이동하는 시간
	static char[][] map;
	// 5가지 방향 배열 (그대로, 하, 상, 좌, 우)
	static int[] dx = {0, 1, -1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	static int maxEatCount; // 가희가 먹을 수 있는 고구마의 최대 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new char[R][C]; // [0][0] ~ [R-1][C-1]
		
		// 가희의 시작 지점 좌표 초기화
		int startX = 0;
		int startY = 0;
		
		for (int i=0; i<R; i++) {
			String input = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
				// 해당 좌표가 가희가 있는 좌표('G')인 경우
				if (map[i][j] == 'G') {
					// 시작좌표 설정
					startX = i;
					startY = j;
				}
			}
		}
		
		maxEatCount = Integer.MIN_VALUE; // 가희가 먹은 고구마 최대 개수 최소값으로 초기화
		
		// 가희의 시작좌표에서부터 깊이우선탐색 실시 (백트래킹)
		eatGoguma(0, startX, startY, 0);
		
		System.out.println(maxEatCount);
	}
	
	// 가희가 고구마를 최대한 먹을 수 있도록 이동시키는 메서드
	// depth: 가희가 현재 이동한 시간
	// nowX: 가희의 현재 x 좌표 (행)
	// nowY: 가희의 현재 y 좌표 (열)
	// eatCount: 가희가 현재까지 고구마를 먹은 개수
	public static void eatGoguma(int depth, int nowX, int nowY, int eatCount) {
		// 가희가 이동한 시간이 T초가 된 경우 (기저 조건)
		if (depth == T) {
			maxEatCount = Math.max(maxEatCount, eatCount); // 가희가 먹은 고구마 최대 개수 갱신
			return; // 메서드 종료
		}
		
		// 현재까지 먹은 고구마 수 + 남은 시간 동안 이동할 수 있는 최대 횟수가 가희가 먹은 최대 고구마 개수보다 작거나 같은 경우 (추가된 기저조건)
        if (eatCount + (T - depth) <= maxEatCount) {
            return; // 메서드 종료
        }
		
		// 5가지 방향 탐색 (이동하지 않고 그자리 머물기, 하, 상, 좌, 우)
		for (int i=0; i<5; i++) {
			int nextX = nowX + dx[i];
			int nextY = nowY + dy[i];
			
			// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
			if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
				continue; // 다음 방향 탐색하도록 넘어감
			}
			
			// 탐색한 좌표가 장애물('#')인 경우
			if (map[nextX][nextY] == '#') {
				continue; // 다음 방향 탐색하도록 넘어감
			}
			
			// 탐색한 좌표가 고구마('S')인 경우
			if (map[nextX][nextY] == 'S') {
				map[nextX][nextY] = '.'; // 탐색한 좌표 고구마를 먹었다고 처리
				// 탐색한 좌표로 깊이우선탐색 실시 (고구마 먹은 개수 1 증가)
				eatGoguma(depth+1, nextX, nextY, eatCount + 1);
				map[nextX][nextY] = 'S'; // 탐색한 좌표 고구마 다시 원복 (백트래킹)
			}
			// 탐색한 좌표가 빈칸('.')이거나 또는 가희의 시작 좌표('G')인 경우
			else if (map[nextX][nextY] == '.' || map[nextX][nextY] == 'G') {
				// 탐색한 좌표로 깊이우선탐색 실시
				eatGoguma(depth+1, nextX, nextY, eatCount);
			}
		}
		
	}

}