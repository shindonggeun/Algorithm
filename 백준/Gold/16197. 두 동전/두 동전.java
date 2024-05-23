import java.util.*;
import java.io.*;

public class Main {
	
	static class Coin {
		int x;
		int y;
		
		public Coin(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class TwoCoins {
		int x1;
		int y1;
		int x2;
		int y2;
		int count;	// 버튼 누른 횟수
		
		public TwoCoins(int x1, int y1, int x2, int y2, int count) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.count = count;
		}
		
	}
	
	static int N;
	static int M;
	static char[][] map;
	static boolean[][][][] visited;	// [첫번째 동전 행][첫번째 동전 열][두번째 동전 행][두번째 동전 열]
	static Coin[] coinArr;
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];	// [0][0] ~ [N-1][M-1]
		visited = new boolean[N][M][N][M];
		coinArr = new Coin[2];
		int idx = 0;
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'o') {
					coinArr[idx++] = new Coin(i, j);
				}
			}
		}
		
		int result = bfs();
		System.out.println(result);

	}
	
	public static int bfs() {
		Queue<TwoCoins> queue = new LinkedList<>();
		queue.add(new TwoCoins(coinArr[0].x, coinArr[0].y, coinArr[1].x, coinArr[1].y, 0));
		visited[coinArr[0].x][coinArr[0].y][coinArr[1].x][coinArr[1].y] = true;
		
		while (!queue.isEmpty()) {
			TwoCoins now = queue.poll();
			int nowX1 = now.x1;
			int nowY1 = now.y1;
			int nowX2= now.x2;
			int nowY2 = now.y2;
			int nowCount = now.count;
			
			if (nowCount >= 10) {
				break;
			}
			
			// 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX1 = nowX1 + dx[i];
				int nextY1 = nowY1 + dy[i];
				int nextX2 = nowX2 + dx[i];
				int nextY2 = nowY2 + dy[i];
				
				// 첫번째 동전이 벽에 가로막힌 경우
				if (nextX1 >= 0 && nextY1 >= 0 && nextX1 < N && nextY1 < M && map[nextX1][nextY1] == '#') {
					// 첫번째 동전 움직이지 않게끔
					nextX1 = nowX1;
					nextY1 = nowY1;
				}
				
				// 두번째 동전이 벽에 가로막힌 경우
				if (nextX2 >= 0 && nextY2 >= 0 && nextX2 < N && nextY2 < M && map[nextX2][nextY2] == '#') {
					// 두번째 동전 움직이지 않게끔
					nextX2 = nowX2;
					nextY2 = nowY2;
				}
				
				int outCoin = 0;	// 떨어진 동전의 수
				// 첫번째 동전이 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if (nextX1 < 0 || nextY1 < 0 || nextX1 >= N || nextY1 >= M) {
					outCoin++;	// 떨어진 동전의 수 증가
				}
				// 두번째 동전이 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if (nextX2 < 0 || nextY2 < 0 || nextX2 >= N || nextY2 >= M) {
					outCoin++;	// 떨어진 동전의 수 증가
				}
				
				// 떨엊니 동전의 수가 0개면서 동시에 첫번째 동전과 두번째 동전이 방문한 좌표가 아닌경우
				if (outCoin == 0 && !visited[nextX1][nextY1][nextX2][nextY2]) {
					queue.add(new TwoCoins(nextX1, nextY1, nextX2, nextY2, nowCount + 1));
					visited[nextX1][nextY1][nextX2][nextY2] = true;
				}
				else if (outCoin == 1) {
					return nowCount + 1;
				}
			}
		}
		
		return -1;
	}
	

}