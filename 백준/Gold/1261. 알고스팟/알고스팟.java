import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;	// 가로 크기
	static int M;	// 세로 크기
	static int[][] map;	// 미로
	static int[][] dist;	// 출발지에서 도착지까지 각 좌표의 최소비용을 저장할 2차원 배열
	static final int INF = Integer.MAX_VALUE;	// 다익스트라 알고리즘에서 최소 연료 비용 초기화시 초기에 사용할 무한대 값
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 가로 크기 입력
		M = Integer.parseInt(st.nextToken());	// 세로 크기 입력
		map = new int[M][N];	// [0][0] ~ [M-1][N-1]
		dist = new int[M][N];
		
		for(int i=0; i<M; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j) - '0';
				dist[i][j] = INF;	// 각 좌표의 최소비용 무한대 값으로 초기화
			}
		}
		
		dijkstra(0, 0);	// 다익스트라 알고리즘 메서드 호출
		System.out.println(dist[M-1][N-1]);
	}
	
	// 시작지에서 도착지까지의 최소비용을 계산하기 위한 다익스트라 알고리즘 메서드
	public static void dijkstra(int startX, int startY) {
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		dist[startX][startY] = 0;
		pq.add(new Position(startX, startY));
		
		while(!pq.isEmpty()) {
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
					continue;
				}
				
				int cost = dist[nowX][nowY] + map[nextX][nextY];
				
				if(cost < dist[nextX][nextY]) {
					dist[nextX][nextY] = cost;
					pq.add(new Position(nextX, nextY));
				}
			}
		}
	}

}
