import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표정보를 저장해주는 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;	// 동굴의 크기
	static int[][] map;	// 동굴을 나타내는 맵
	static int[][] dist;	// 최소 비용(최소 금액)을 저장하는 배열
	static final int INF = Integer.MAX_VALUE;	// 다익스트라 알고리즘에서 최소 비용 초기화시 사용할 무한대 값
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int start = 1;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			// 0이 입력된 경우 전체 입력 종료하게끔
			if(N == 0) {
				break;
			}
			
			map = new int[N][N];	// [0][0] ~ [N-1][N-1]
			dist = new int[N][N];	
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = INF;	// 모든 좌표의 최소 비용 일단 INF으로 초기화
				}
			}
			
			dijkstra(0, 0);	// 시작점 (0, 0)에서부터 시작 (다익스트라 알고리즘 메서드 호출)
			// [N-1][N-1] 동굴까지 이동할때의 최소 금액 출력
			sb.append("Problem ").append(start).append(": ").append(dist[N-1][N-1]).append("\n");
			start++;
		}
		System.out.print(sb);

	}
	
	// 다익스트라 알고리즘 메서드 
	public static void dijkstra(int startX, int startY) {
		// 다익스트라 알고리즘을 사용하기 위해 우선순위 큐 생성 및 선언
		// 최소 금액 오름차순 정렬
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		pq.add(new Position(startX, startY));	// 시작 좌표 정보 우선순위 큐에 저장
		dist[startX][startY] = map[startX][startY];	// 시작 좌표 정보 동굴에서 시작 좌표의 루피로 저장
		
		while(!pq.isEmpty()) {
			Position now = pq.poll();	// 현재 좌표 뽑아내기
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				// 다음 위치까지의 비용 계산 (금액 계산)
				int cost = dist[nowX][nowY] + map[nextX][nextY];
				// 계산한 비용이 탐색한 위치(좌표)의 최소 비용보다 작은 경우 
				if(cost < dist[nextX][nextY]) {
					dist[nextX][nextY] = cost;	// 탐색한 좌표의 최소 비용 갱신
					pq.add(new Position(nextX, nextY));	// 우선순위 큐에 탐색한 좌표 저장 (다음 좌표 탐색하게끔)
				}
			}
		}
	}

}
