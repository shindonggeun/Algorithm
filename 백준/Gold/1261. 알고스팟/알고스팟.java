import java.util.*;
import java.io.*;

public class Main {
	
	// 맵의 좌표 정보를 담고있는 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int M;	// 가로 크기
	static int N;	// 세로 크기
	static int[][] map;	// 미로
	static int[][] dist;	// 시작지점부터 각 위치까지의 최소 비용을 저장하는 2차원 배열
	static final int INF = Integer.MAX_VALUE;	// 다익스트라 알고리즘에서 사용할 무한대를 나타내는 상수
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());	// 가로 크기 입력
		N = Integer.parseInt(st.nextToken());	// 세로 크기 입력
		
		map = new int[N][M];	// [0][0] ~ [N-1][M-1]
		dist = new int[N][M];
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
				dist[i][j] = INF;	// 시작 지점부터 각 위치까지의의 최소 비용을 무한대로 초기화
			}
		}
		
		dijkstra(0, 0);	// 시작지점에서부터 끝 지점까지의 최소 비용 계산하는 메서드 호출
		System.out.println(dist[N-1][M-1]);
	}
	
	// 시작지점에서부터 끝 지점까지의 최소 비용 계산하는 메서드 (다익스트라 알고리즘 이용)
	public static void dijkstra(int startX, int startY) {
		// 다익스트라 알고리즘을 이용하기 위해 우선순위 큐 선언 및 생성
		// 최소 비용 오름차순 정렬
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		pq.add(new Position(startX, startY));	// 시작 위치 우선순위 큐에 저장
		dist[startX][startY] = 0;	// 시작 지점의 최소비용 0으로 초기화
		
		// 다익스트라 알고리즘 이용
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 현재 좌표 정보 뽑아냄
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 다른 방향 탐색하도록 넘어감
				}
				
				// 다음 위치까지의 비용 계산
				int cost = dist[nowX][nowY] + map[nextX][nextY];
				// 탐색한 위치의 비용이 탐색한 위치의 최소 비용보다 작은 경우
				if (cost < dist[nextX][nextY]) {
					dist[nextX][nextY] = cost;	// 탐색한 위치의 최소 비용 갱신
					pq.add(new Position(nextX, nextY));	// 탐색한 위치 우선순위 큐에 저장
				}
			}
		}
	}

}
