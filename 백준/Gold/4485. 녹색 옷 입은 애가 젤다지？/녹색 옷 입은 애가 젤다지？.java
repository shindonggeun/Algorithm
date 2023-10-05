import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보를 저장하는 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;	// 동굴의 크기
	static int[][] map;	
	static int[][] dist;	// 최소 비용을 저장하는 배열	
	static final int INF = Integer.MAX_VALUE;	// 다익스트라 알고리즘에서 최소 비용 초기화시 사용할 무한한 값
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int start = 1;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			// 0인 입력이 주어지면 전체 입력 종료
			if(N == 0) {
				break;	// 무한반복 빠져나옴
			}
			
			map = new int[N][N];	// [0][0] ~ [N-1][N-1]
			dist = new int[N][N];	
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = INF;
				}
			}
			
			bfs();	// 너비우선탐색 실시 (다익스트라 알고리즘 이용)
			sb.append("Problem ").append(start).append(": ").append(dist[N-1][N-1]).append("\n");
			start++;	
		}
		
		System.out.print(sb);
	}
	
	// 너비우선탐색 메서드
	public static void bfs() {
		// 다익스트라 알고리즘에서 사용할 우선순위 큐 이용
		// 최소 비용을 우선순위 큐로 처리
		PriorityQueue<Position> pq = new PriorityQueue<>(new Comparator<Position>() {

			@Override
			public int compare(Position p1, Position p2) {
				// 최소비용 오름차순 정렬
				return dist[p1.x][p1.y] - dist[p2.x][p2.y];
			}
		});
		
		pq.add(new Position(0, 0));	// 시작 위지 우선순위 큐에 저장
		dist[0][0] = map[0][0];	// 시작위치의 최소 비용 초기화
		
		// 큐가 빌때까지 반복
		while(!pq.isEmpty()) {
			Position now = pq.poll();	// 우선순위 큐에서 현재 위치 꺼냄
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우 (범위 벗어난 경우) 
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				int cost = dist[nowX][nowY] + map[nextX][nextY];	// 다음 위치까지의 비용 계산 (금액 계산)
				// 계산한 비용이 최소 비용보다 작은 경우
				if(cost < dist[nextX][nextY]) {
					dist[nextX][nextY] = cost;	// 최소 비용 갱신
					pq.add(new Position(nextX, nextY));	// 우선순위 큐에 탐색한 좌표 추가
				}
			}
		}
	}

}
