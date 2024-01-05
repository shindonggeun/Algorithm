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
	
	static int N;
	static int[][] map;
	static int[][] dist;
	static final int INF = Integer.MAX_VALUE;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
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
		
		zeroOneBfs(0, 0);
		
		System.out.println(dist[N-1][N-1]);

	}
	
	public static void zeroOneBfs(int startX, int startY) {
		Deque<Position> deque = new ArrayDeque<>();
		deque.add(new Position(startX, startY));
		dist[startX][startY] = 0;
		
		while(!deque.isEmpty()) {
			Position now = deque.pollFirst();
			int nowX = now.x;
			int nowY = now.y;
			
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				// 탐색한 좌표의 방 색을 바꾼 횟수가 현재 좌표의 방 색을 바꾼 횟수보다 많은 경우
				if(dist[nextX][nextY] > dist[nowX][nowY]) {
					// 탐색한 좌표가 흰 방(1)인 경우 (즉, 비용 들지않고 이동 가능함)
					if(map[nextX][nextY] == 1) {
						// 탐색한 좌표의 방 색 바꾼 횟수가 현재좌표에서의 방 색 바꾼 횟수와 동일 (비용 0 들음)
						dist[nextX][nextY] = dist[nowX][nowY];	
						// 비용이 들지 않았으므로 (즉, 가중치가 0)이므로 덱에 앞쪽에 추가
						deque.addFirst(new Position(nextX, nextY));	
					}
					// 탐색한 좌표가 검은 방(0)인 경우 (즉, 비용이 1 들음)
					else if(map[nextX][nextY] == 0) {
						// 탐색한 좌표의 방 색 바꾼 횟수가 현재좌표에서의 방 색 바꾼 횟수 + 1임 (비용 1 들음)
						dist[nextX][nextY] = dist[nowX][nowY] + 1;
						// 비용이 들었으므로 (즉, 가중치가 1)이므로 덱에 뒤쪽에 추가
						deque.addLast(new Position(nextX, nextY));
					}
				}
			}
		}
	}

}
