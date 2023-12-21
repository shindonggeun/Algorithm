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
	
	static int H;	// 높이 (행의 개수)
	static int W;	// 너비 (열의 개수)
	static char[][] map;	// 맵을 나타내는 2차원 배열
	static boolean[][] visited;	// 각 좌표의 방문여부를 나타내는 2차원 배열
	static int[][] dist;	// 시작지점에서 각 칸까지의 최단거리를 저장하는 2차원 배열
	static final int INF = Integer.MAX_VALUE;	// 다익스트라 알고리즘에서 사용할 무한대를 의미하는 상수
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];	// [0][0] ~ [H-1][W-1]
		visited = new boolean[H][W];
		dist = new int[H][W];
		
		// 시작점과 끝점의 위치 변수 설정
		int startX = 0;
		int startY = 0;
		int endX = 0;
		int endY = 0;
		
		for(int i=0; i<H; i++) {
			String input = br.readLine();
			for(int j=0; j<W; j++) {
				map[i][j] = input.charAt(j);
				dist[i][j] = INF;	// 각 좌표마다 최단 거리를 무한대로 초기화
				
				// 시작점과 끝점 위치 설정
				if(map[i][j] == 'S') {
					startX = i;
					startY = j;
				}
				else if(map[i][j] == 'E') {
					endX = i;
					endY = j;
				}
			}
		}
		
		dijkstra(startX , startY);	// 시작지점에서 다익스트라 알고리즘 실행
		
		System.out.println(dist[endX][endY]);	// 시작지점에서 끝점까지의 최단거리 출력

	}
	
	// 시작지점에서 끝점까지의 최단거리르 구하는 메서드 (다익스트라 알고리즘 이용)
	public static void dijkstra(int startX, int startY) {
		// 다익스트라 알고리즘을 이용하기 위해 우선순위 큐 선언 및 생성
		// 최단거리 짧은 순으로 정렬 (오름차순 정렬)
		PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
		pq.add(new Position(startX, startY));	// 시작지점의 좌표정보 우선순위 큐에 저장
		dist[startX][startY] = 0;	// 시작지점에서의 최단거리 0으로 설정
		
		// 다익스트라 알고리즘 이용
		while(!pq.isEmpty()) {
			// 우선순위 큐에서 현재 좌표정보 뽑아냄
			Position now = pq.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 현재 좌표가 방문하지 않은 경우
			if(!visited[nowX][nowY]) {
				visited[nowX][nowY] = true;	// 현재 좌표 방문처리
				// 현재 위치가 벽에 인접한지 확인
				boolean nowPositionWallCheck = wallCheck(nowX, nowY);
				
				// 4가지 방향 탐색 (하, 상, 좌, 우)
				for(int i=0; i<4; i++) {
					int nextX = nowX + dx[i];
					int nextY = nowY + dy[i];
					
					// 탐색한 좌표가 [0][0] ~ [H-1][W-1] 이외의 좌표인 경우
					if(nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
						continue;	// 다음 방향 탐색하도록 넘어감
					}
					
					// 탐색한 좌표가 벽인 경우
					if(map[nextX][nextY] == '#') {
						continue;	// 다음 방향 탐색하도록 넘어감
					}
					
					// 탐색한 위치가 벽에 인접한지 확인
					boolean nextPositionWallCheck = wallCheck(nextX, nextY);
					
					int time = 0;	// 이동하는데 걸리는 시간
					
					// 벽에 인접한 칸에서 벽에 인접한 칸으로 이동 가능한 경우
					// 즉, 현재 위치가 벽에 인접하면서 탐색한 위치도 벽에 인접한 경우 (벽을 타고 이동 가능한 경우)
					if(nowPositionWallCheck && nextPositionWallCheck) {
						time = 0;	// 걸리는 시간 0초
					}
					// 벽을 타고 이동할 수 없는 경우 (그낭 이동)
					else {
						time = 1;	// 걸리는 시간 1초
					}
					
					// 다음 위치까지의 거리 계산 (현재 좌표에서의 최단 거리 + 걸리는 시간)
					int nextDist = dist[nowX][nowY] + time;
					// 다음 위치까지의 거리가 탐색한 좌표의 최단거리보다 작은 경우
					if(nextDist < dist[nextX][nextY]) {
						dist[nextX][nextY] = nextDist;	// 탐색한 좌표의 최단 거리 갱신
						pq.add(new Position(nextX, nextY));	// 탐색한 좌표 정보 우선순위 큐에 저장
					}
					
				}
			}
			
			
		}
	}
	
	// 해당 좌표가 벽에 인접해 있는지 확인하는 메서드
	public static boolean wallCheck(int nowX, int nowY) {
		// 4가지 방향 탐색 (하, 상, 좌, 우)
		for(int i=0; i<4; i++) {
			int nextX = nowX + dx[i];
			int nextY = nowY + dy[i];
			// 탐색한 좌표가 벽('#')인 경우 (즉, 벽에 인접해 있는 경우)
			if(map[nextX][nextY] == '#') {
				return true;	// 인접해 있으므로 true 반환
			}
		}
		
		// 벽에 인접해 있지 않은 경우 false 반환
		return false;
	}

}
