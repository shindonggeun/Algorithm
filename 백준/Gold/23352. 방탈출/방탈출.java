import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보 및 움직인 거리를 담고 있는 내부 클래스
	static class Position {
		int x;
		int y;
		int moveCount; // 움직인 횟수 (이동 거리)
		
		public Position(int x, int y, int moveCount) {
			this.x = x;
			this.y = y;
			this.moveCount = moveCount;
		}
	}
	
	static int N; // 지도의 세로 크기
	static int M; // 지도의 가로 크기
	static int[][] map;
	static boolean[][] visited; // 각 좌표마다 방문 여부를 체크하는 배열
	static int maxDistance; // 가장 긴 최단 거리
	static int maxSum; // 가장 큰 결과값 (조건을 만족하는 시작 방의 값 + 끝 방의 값)
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		
		// 1. 지도에서 각 방들의 정보를 입력하는 과정
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		maxDistance = 0; // 가장 긴 최단 거리 0으로 초기화
		maxSum = 0; // 가장 큰 결과값 0으로 초기화
		
		// 2. 각 방들에서 출발하는 과정
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				// 해당 방이 0이 아닌 경우 (들어갈 수 있는 경우)
				if (map[i][j] != 0) {
					bfs(i, j); // 해당 좌표에서부터 너비 우선 탐색 실시
				}
			}
		}
		
		System.out.println(maxSum);
		
	}
	
	// 너비 우선 탐색 알고리즘을 이용하여 최장 경로를 탐색하는 메서드
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY, 0));
		
		visited = new boolean[N][M]; // 방문 여부 배열 초기화 [0][0] ~ [N-1][M-1]
		visited[startX][startY] = true; // 해당 시작 좌표 방문 처리
		
		int start = map[startX][startY]; // 시작 방의 숫자 저장
		int end = 0; // 최장 거리의 끝 방 숫자
		int distance = 0; // 현재 너비 우선 탐색에서 가장 긴 거리
		
		while (!queue.isEmpty()) {
			// 큐에서 현재 좌표 정보 뽑아냄
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowMoveCount = now.moveCount;
			end = map[nowX][nowY]; // 현재 방의 숫자를 최종 도착 방으로 저장
			
			// 현재 움직인 거리가 너비 우선 탐색을 실시해서 찾은 거리보다 큰 경우
			if (nowMoveCount > distance) {
				distance = nowMoveCount; // 최장 거리 갱신 (가장 먼 거리로 갱신)
			}
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우 (범위를 벗어난 경우)
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 해당 방이 0인 경우
				if (visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 큐에 탐색한 좌표 정보 및 이동 거리 + 1 해서 저장
				queue.add(new Position(nextX, nextY, nowMoveCount + 1));
				visited[nextX][nextY] = true; // 탐색한 좌표 방문 처리
			}
		}
		
		// 너비 우선 탐색 다 실시하고 난 뒤 최장 거리 비교하는 과정
		// 너비 우선 탐색을 실시하여 찾은 거리가 현재까지의 최장 거리보다 큰 경우
		if (distance > maxDistance) {
			maxDistance = distance; // 최장 거리 갱신
			maxSum = start + end; // 시작 방 + 끝 방 숫자 합으로 결과값 갱신
		}
		// 너비 우선 탐색을 실시하여 찾은 거리가 현재까지의 최장 거리와 같은 경우
		else if (distance == maxDistance) {
			maxSum = Math.max(maxSum, start + end); // 둘 중 더 큰값으로 결과값 갱신
		}
		
		
	}

}