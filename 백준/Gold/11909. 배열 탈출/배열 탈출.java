import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보와 버튼 누른 수를 저장해서 관리할 내부 클래스
	static class Position {
		int x;
		int y;
		int button; // 버튼 누른 수
		
		public Position(int x, int y, int button) {
			this.x = x;
			this.y = y;
			this.button = button;
		}
	}
	
	static int N; // 가로, 세로 크기
	static int[][] map;
	static int[][] dist; // 각 좌표까지의 최소 비용을 저장할 배열
	static final int INF = Integer.MAX_VALUE; // 다익스트라 알고리즘에서 사용할 무한대를 나타내는 상수
	// 2가지 방향 배열 (하, 우)
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N]; // [0][0] ~ [N-1][N-1]
		dist = new int[N][N]; // [0][0] ~ [N-1][N-1]
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dist[i][j] = INF; // 각 좌표들 일단 무한대로 초기화
			}
		}
		
		dijkstra(0, 0); // (0, 0)부터 (N-1)(N-1)까지의 최단거리를 구하기 위해 다익스트라 메서드 호출
		
		System.out.println(dist[N-1][N-1]);

	}
	
	// 최단 거리를 구하기 위한 다익스트라 메서드
	public static void dijkstra(int startX, int startY) {
		// 다익스트라 알고리즘을 이용하기 위해 우선순위 큐 선언 및 생성
		// 정렬 기준 최단거리 기준으로 오름차순 정렬
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		pq.add(new Position(startX, startY, 0)); // 우선순위 큐에 좌표정보 및 버튼 누른 수 집어넣음
		dist[startX][startY] = 0; // 시작 좌표의 최단거리 0으로 초기화
		
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 현재 좌표 정보 뽑아냄
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowButton = now.button;
			
			// 2가지 방향 탐색
			for (int i=0; i<2; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue; // 다음 방향으로 탐색하도록 넘어감
				}
				
				int requireButton = 0; // 다음 방향으로 넘어가기 위한 필요한 버튼 수
				// 현재 좌표의 비용이 탐색한 좌표의 비용보다 작거나 같은 경우
				if (map[nowX][nowY] <= map[nextX][nextY]) {
					// 필요한 버튼 수는 탐색할 좌표의 비용에서 현재 좌표 비용을 빼고 + 1
					requireButton = map[nextX][nextY] - map[nowX][nowY] + 1;
				}
				
				int cost = nowButton + requireButton; // 현재까지 누른 버튼 수와 필요한 버튼 수를 더한 값이 비용
				
				// 해당 비용이 탐색한 좌표의 최소 비용보다 작은 경우
				if (cost < dist[nextX][nextY]) {
					dist[nextX][nextY] = cost; // 최소 비용 갱신
					pq.add(new Position(nextX, nextY, cost)); // 우선순위 큐에 탐색한 좌표 정보 및 해당 비용 저장
				}
			}
		}
	}

}