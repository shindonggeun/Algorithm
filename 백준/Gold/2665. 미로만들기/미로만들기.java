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
	
	static int N; // 미로의 크기
	static int[][] map;
	static int[][] dist; // 시작지점에서부터 각 좌표까지의 최소 비용(최소 검은 방 변환 수)를 담은 배열
	static final int INF = Integer.MAX_VALUE; // 다익스트라 알고리즘에서 사용하는 상수 무한대 값으로 초기화
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N]; // [0][0] ~ [N-1][N-1]
		dist = new int[N][N];
		
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = input.charAt(j) - '0';
				dist[i][j] = INF; // 각 좌표들의 최소 비용 무한대로 초기화
			}
		}
		
		dijkstra(0, 0); // 시작좌표에서부터 출발하여 끝점까지의 최단 경로 계산할 수 있게끔 다익스트라 메서드 호출 
		System.out.println(dist[N-1][N-1]);

	}
	
	// 시작좌표에서부터 끝점까지의 최단 경로를 계산해주는 다익스트라 메서드
	public static void dijkstra(int startX, int startY) {
		// 다익스트라 알고리즘을 사용하기 위해 우선순위 큐 선언 및 생성
		// 가중치를 기준으로 오름차순 정렬
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		pq.add(new Position(startX, startY)); // 시작 좌표 정보 우선순위 큐에 저장
		dist[startX][startY] = 0; // 시작 좌표의 최소 비용 0으로 초기화
		
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 현재 좌표 정보 뽑아냄
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue; // 다른 방향 탐색할 수 있게끔 넘어감
				}
				
				int cost = 0; // 탐색한 좌표까지의 거리 비용 0으로 초기화
				
				// 탐색한 좌표가 흰 방(1)인 경우
				if (map[nextX][nextY] == 1) {
					// 현재 탐색한 좌표까지의 거리 비용은 현재 좌표까지의 최소 비용과 동일
					// 즉, 변경되지 않음
					cost = dist[nowX][nowY];  
				}
				// 탐색한 좌표가 검은 방(0)인 경우
				else {
					// 현재 탐색한 좌표까지의 거리 비용 현재 좌표까지의 최소 비용 + 1한 값으로 초기화
					// 즉, 검은 방을 흰 방으로 변경하는 비용 추가
					cost = dist[nowX][nowY] + 1;
				}
				
				// 탐색한 좌표까지의 거리 비용이 탐색한 좌표의 최소 비용보다 적은 경우
				if (cost < dist[nextX][nextY]) {
					dist[nextX][nextY] = cost; // 탐색한 좌표의 최소 비용 갱신
					pq.add(new Position(nextX, nextY)); // 우선순위 큐에 탐색한 좌표 정보 저장
				}
			}
		}
	}

}