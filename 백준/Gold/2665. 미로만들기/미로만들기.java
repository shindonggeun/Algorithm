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
	
	static int N; // 바둑판의 크기
	static int[][] map; // 바둑판 모양의 방을 나타내는 2차원 배열 (0: 검은 방, 1: 흰 방)
	static int[][] dist; // 시작점에서 각 좌표들까지의 최단 거리를 저장하는 배열
	static final int INF = Integer.MAX_VALUE; // 무한대를 나타내는 상수
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N]; // [0][0] ~ [N-1][N-1]
		dist = new int[N][N]; // [0][0] ~ [N-1][N-1]
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = input.charAt(j) - '0';
				dist[i][j] = INF; // 각 좌표마다 초기 최단 거리 무한대로 설정
			}
		}
		
		// 시작좌표에서부터 도착지점까지의 최단 거리 (검은방에서 흰방을 바꾼 최소 개수)를 구하기 위한 0-1 너비우선탐색 실시
		zeroOneBfs(0, 0);
		
		System.out.println(dist[N-1][N-1]);

	}
	
	// 시작 저짐에서 도착 지점까지의 최단 거리 (검은 방에서 흰 방으로 바꾸어야 할 최소의 검은 방의 개수)를 구해주는 메서드 (0-1 너비우선탐색)
	public static void zeroOneBfs(int startX, int startY) {
		// 0-1 너비 우선 탐색 알고리즘을 이용하기 위해 덱 선언 및 생성
		Deque<Position> deque = new ArrayDeque<>();
		deque.add(new Position(startX, startY)); // 덱에 시작 좌표 정보 저장
		dist[startX][startY] = 0; // 시작 좌표의 최단 거리 0으로 설정
		
		while (!deque.isEmpty()) {
			// 덱에 저장된 현재 좌표 정보 뽑아냄
			Position now = deque.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 현재 좌표의 최단거리가 탐색한 좌표의 최단거리보다 크거나 같은 경우
				// 즉, 현재 좌표에서 이동하는 것이 최단거리가 아닌 경우
				if (dist[nowX][nowY] >= dist[nextX][nextY]) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 흰 방(1)인 경우
				if (map[nextX][nextY] == 1) {
					dist[nextX][nextY] = dist[nowX][nowY]; // 탐색한 좌표의 최단 거리를 현재 좌표까지의 최단 거리로 유지
					deque.addFirst(new Position(nextX, nextY)); // 우선적으로 탐색한 좌표 정보를 덱의 앞에 추가해줌
				}
				// 탐색한 좌표가 검은 방(0)인 경우
				else if (map[nextX][nextY] == 0) {
					// 검은 방을 흰 방으로 바꾸는 비용 추가 (현재까지의 비용 + 1을 탐색한 좌표의 비용에 갱신)
					dist[nextX][nextY] = dist[nowX][nowY] + 1;
					deque.addLast(new Position(nextX, nextY)); // 덱의 뒤에 탐색한 좌표 정보 추가해줌
				}
			}
		}
	}

}