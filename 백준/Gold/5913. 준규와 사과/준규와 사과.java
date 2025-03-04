import java.util.*;
import java.io.*;

public class Main {

	static final int SIZE = 5; // 격자 크기 5x5 고정
	static int K; // 장애물 개수
	static int[][] map; // 장애물 위치를 저장할 배열
	static boolean[][] visited; // 방문 여부를 체크할 배열
	// 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int result; // 사과를 모두 수확하는 방법의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		K = Integer.parseInt(br.readLine());
		
		map = new int[SIZE][SIZE]; // [0][0] ~ [SIZE-1][SIZE-1]
		visited = new boolean[SIZE][SIZE];
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			map[x][y] = 1; // 해당 좌표에 장애물 표시 (1)
		}
		
		result = 0; // 결과값 초기화
		
		dfs(0, 0, 0, false); // 준규의 이동 시작점 (0,0)에서 출발
		
		System.out.println(result);

	}
	
	// 깊이 우선 탐색 알고리즘을 이용한 백트래킹 메서드
	// x: 현재 x 좌표
	// y: 현재 y 좌표
	// depth: 현재까지 방문한 칸의 개수 (즉, 현재까지 수확한 사과의 개수)
	// flag: 준규 (0,0)에서 해빈 (4,4)로 전환하는 플래그 변수
	public static void dfs(int x, int y, int depth, boolean flag) {
		// 1. 준규가 절반을 이동했으면 혜빈이가 탐색 시작
		// 준규가 모든 칸의 절반을 이동한 경우 (즉, 준규가 땅에 있는 사과 절반을 수확한 경우) (기저 조건)
		if (depth == (SIZE * SIZE - K) / 2) {
			// 준규의 탐색이 끝난 경우 (false)
			if (!flag) {
				dfs(x, y, 0, true); // 현재 위치에서 혜빈이가 출발
			}
			// 혜빈이가 도착지점 (4,4)에 도달한 경우
			else if (x == SIZE - 1 && y == SIZE - 1) {
				result++; // 사과를 모두 수확하는 방법의 수 증가
			}
			
			return; // 메서드 종료
		}
		
		visited[x][y] = true; // 현재 위치 방문 처리
		
		// 4가지 방향 탐색 (상, 하, 좌, 우)
		for (int i=0; i<4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			// 탐색한 좌표가 이동 가능한 경우
			if (isValid(nextX, nextY)) {
				dfs(nextX, nextY, depth + 1, flag); // 다음 좌표로 깊이 우선 탐색 다시 실시
			}
		}
		
		visited[x][y] = false; // 현재 위치 방문 처리 해제 (백트래킹)
	}
	
	// 특정 위치로 이동 가능한지 검사하는 메서드
	// x: 이동할 x 좌표
	// y: 이동할 y 좌표
	public static boolean isValid(int x, int y) {
		// 격자 범위를 벗어난 경우
		if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
			return false; // 이동 불가능
		}
		
		// 이미 방문한 칸이거나 또는 장애물 (1)인 경우
		if (visited[x][y] || map[x][y] == 1) {
			return false; // 이동 불가능
		}
		
		// 그 이외의 경우는 이동 가능
		return true;
	}

}