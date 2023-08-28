import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표정보를 저장하는 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static int[][] map;
	static int[][] dist;	// 맵에서 해당 좌표까지 오는데 방 색을 바꾼 횟수를 저장해주는 배열
	static final int INF = Integer.MAX_VALUE;
	
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];	// [0][0] ~ [N-1][N-1]
		dist = new int[N][N];	
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = input.charAt(j) - '0';
				dist[i][j] = INF;
			}
		}
		
		bfs();
		System.out.println(dist[N-1][N-1]);
	}
	
	// 너비우선탐색 메서드
	public static void bfs() {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(0, 0));	// 큐에 시작좌표 정보 집어넣음
		dist[0][0] = 0;	// [0][0]부터 방 색 바꾼횟수 0으로 
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][N-1] 이외의 좌표인 경우 
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표의 방 색을 바꾼 횟수가 현재 좌표의 방 색을 바꾼 횟수보다 많은 경우 
				// 다익스트라 알고리즘 이용
				if(dist[nextX][nextY] > dist[nowX][nowY]) {
					// 탐색한 좌표가 흰방(1)인 경우 (즉, 이동 가능한 좌표인 경우)
					if(map[nextX][nextY] == 1) {
						queue.add(new Position(nextX, nextY));
						dist[nextX][nextY] = dist[nowX][nowY];
					}
					// 탐색한 좌표가 검은방(0)인 경우 (즉, 이동 불가능한 좌표인 경우) 
					else if(map[nextX][nextY] == 0) {
						queue.add(new Position(nextX, nextY));
						dist[nextX][nextY] = dist[nowX][nowY] + 1;	// 방 색 바꾼 횟수 증가
					}
				}
			}
		}
	}
	

}
