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
	
	static final int SIZE = 500; // 구역의 크기를 나타내는 상수 (지도의 크기)
	static int N; // 위험한 구역의 개수
	static int M; // 죽음의 구역의 개수
	static int[][] map; // 각 좌표의 해당 구역 정보를 담고 있는 2차원 배열 지도 (0: 안전, 1: 위험, 2: 죽음)
	static int[][] dist; // 시작지점에서 도착지점까지의 최단 거리를 담고 있는 2차원 배열
	static final int INF = Integer.MAX_VALUE; // 무한대를 나타내는 상수
	static final int DANGER = 1; // 위험한 구역을 나타내는 상수
	static final int DEATH = 2; // 죽음의 구역을 나타내는 상수
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		map = new int[SIZE+1][SIZE+1]; // [0][0] ~ [500][500]
		dist = new int[SIZE+1][SIZE+1]; // [0][0] ~ [500][500]
		
		for (int i=0; i<=SIZE; i++) {
			for (int j=0; j<=SIZE; j++) {
				dist[i][j] = INF; // 초기 각 좌표의 최단 거리를 무한대로 초기화
			}
		}
		
		// 1. 위험한 구역을 설정하는 과정
		N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()); // 위험한 구역의 한 모서리 x1 좌표
			int y1 = Integer.parseInt(st.nextToken()); // 위험한 구역의 한 모서리 y1 좌표
			int x2 = Integer.parseInt(st.nextToken()); // 위험한 구역의 반대편 모서리 x2 좌표
			int y2 = Integer.parseInt(st.nextToken()); // 위험한 구역의 반대편 모서리 y2 좌표
			
			// 위험한 구역 설정: 해당 좌표 범위를 위험한 구역으로 설정
			setupZone(x1, y1, x2, y2, DANGER);
		}
		
		// 2. 죽음의 구역을 설정하는 과정
		M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()); // 죽음의 구역의 한 모서리 x1 좌표
			int y1 = Integer.parseInt(st.nextToken()); // 죽음의 구역의 한 모서리 y1 좌표
			int x2 = Integer.parseInt(st.nextToken()); // 죽음의 구역의 반대편 모서리 x2 좌표
			int y2 = Integer.parseInt(st.nextToken()); // 죽음의 구역의 반대편 모서리 y2 좌표
			
			// 죽음의 구역 설정: 해당 좌표 범위를 죽음의 구역으로 설정
			setupZone(x1, y1, x2, y2, DEATH);
		}
		
		dijkstra(0, 0); // 시작지점에서부터 도착지점까지의 최단거리 구하는 메서드 호출
		
		// 도착지점까지의 최단 거리 (잃은 생명의 최솟값)가 무한대 상수 (즉, 갈수 없는 경우) -1 출력
		// 갈수 있는 경우 해당 도착지점까지의 최단거리 (잃은 생명의 최소값) 출력
		System.out.println(dist[SIZE][SIZE] == INF ? -1 : dist[SIZE][SIZE]);

	}
	
	// 위험한 구역이나 죽음의 구역을 설정하는 메서드
	public static void setupZone(int x1, int y1, int x2, int y2, int value) {
		int minX = Math.min(x1, x2); // x 좌표중 작은 값 설정
		int minY = Math.min(y1, y2); // y 좌표중 작은 값 설정
		
		int maxX = Math.max(x1, x2); // x 좌표중 큰 값 설정
		int maxY = Math.max(y1, y2); // y 좌표중 큰 값 절정
		
		// 지정된 좌표 범위 내 모든 위치를 value 값(위험: 1, 죽음: 2)으로 설정
		for (int i=minX; i<=maxX; i++) {
			for (int j=minY; j<=maxY; j++) {
				// 이미 설정된 값보다 높은 위험도를 가진 구역이 아닌 경우
				// 즉, 죽음이 위험보다 우선순위 높으므로 해당 값으로 설정해줘야함
				if (map[i][j] < value) {
					map[i][j] = value; // 구역을 해당 값으로 설정 (1: 위험, 2: 죽음)
				}
			}
		}
	}
	
	// 시작지점에서부터 도착지점까지 최단거리를 찾는 메서드 (다익스트라) 
	public static void dijkstra(int startX, int startY) {
		// 다익스트라 알고리즘을 이용하기 위해 우선순위 큐 선언 및 생성
		// 최단거리를 기준으로 오름차순 정렬 (최단 거리가 작은 순으로 좌표 정렬)
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		pq.add(new Position(startX, startY)); // 시작 좌표 우선순위 큐에 저장
		dist[startX][startY] = 0; // 시작 좌표의 최단 거리 0으로 초기화
		
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 현재 좌표 정보 뽑아냄
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [500][500] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX > SIZE || nextY > SIZE) {
					continue; // 다음 방향 탐색하도록 넝어감
				}
				
				// 탐색한 좌표가 죽음의 구역(2)인 경우
				if (map[nextX][nextY] == DEATH) {
					continue; // 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표까지의 거리 계산 (잃은 체력 즉, 위험도 계산)
				int cost = dist[nowX][nowY] + map[nextX][nextY];
				
				// 탐색한 좌표까지의 거리가 탐색한 좌표의 최단 거리보다 작은 경우
				if (cost < dist[nextX][nextY]) {
					dist[nextX][nextY] = cost; // 최단 거리 갱신
					pq.add(new Position(nextX, nextY)); // 우선순위 큐에 탐색한 좌표 정보 저장
				}
			}
		}
	}

}