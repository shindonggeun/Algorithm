import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보를 담고 있는 내부 클래스
	static class Position {
		int x;
		int y;
		int move; // 이동 횟수
		
		public Position(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}
	
	static int N; // 세로 크기
	static int M; // 가로 크기
	static int[][] map;
	static boolean[][] visited; // 각 좌표마다 방문여부를 나타내는 2차원 방문 배열
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M]; // [0][0] ~ [N-1][M-1]
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		int moveCount = bfs(0, 0); // 시작 좌표에서부터 너비 우선 탐색 실시
		System.out.println(moveCount);
	}
	
	// 시작 좌표에서부터 시작하여 도착 좌표까지의 최소 이동거리를 구하는 너비 우선 탐색 메서드
	public static int bfs(int startX, int startY) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		// 큐에 시작 좌표 정보 및 이동한 횟수 저장 (이동한 횟수에 시작 위치도 포함)
		queue.add(new Position(startX, startY, 1));
		visited[startX][startY] = true; // 시작 좌표 방문 처리
		
		while (!queue.isEmpty()) {
			Position now = queue.poll(); // 큐에 저장된 현재 좌표 정보 뽑아냄
			int nowX = now.x;
			int nowY = now.y;
			int nowMove = now.move;
			
			// 현재 좌표가 도착 위치에 도달한 경우
			if (nowX == N-1 && nowY == M-1) {
				return nowMove; // 현재까지 이동한 횟수 반환
			}
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 이동할수 없는 칸(0)인 경우
				if (visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue; // 다음 방향 탐색하돌고 넘어감
				}
				
				// 큐에 탐색한 좌표 정보 저장
				queue.add(new Position(nextX, nextY, nowMove + 1));
				visited[nextX][nextY] = true; // 탐색한 좌표 방문 처리
			}
		}
		
		// 위의 너비우선탐색 과정 끝났는데도 도착 위치에 도달하지 못한 경우 -1 반환
		return -1;
	}

}