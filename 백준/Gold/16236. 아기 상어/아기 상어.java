import java.util.*;
import java.io.*;

public class Main {
	
	static class Shark {
		int x; // 행
		int y; // 열
		int move; // 상어가 이동한 횟수
		
		public Shark(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}
	
	static int N;
	static int[][] map;
	static Shark shark;
	static int sharkSize;
	static int eatCount;
	static boolean eatCheck;
	static int result;
	// 4가지 방향 배열 (상, 좌, 우, 하)
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N]; // [0][0] ~ [N-1][N-1]
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new Shark(i, j, 0);
					map[i][j] = 0; // 초기 아기 상어 위치 빈칸으로 설정
				}
			}
		}
		
		sharkSize = 2;
		eatCount = 0;
		
		int result = bfs();
		System.out.println(result);
		
	}
	
	public static int bfs() {
		int totalMove = 0;
		
		while (true) {
			PriorityQueue<Shark> pq = new PriorityQueue<>((a, b) -> {
				if (a.move == b.move) {
					if (a.x == b.x) {
						return a.y - b.y;
					}
					return a.x - b.x;
				}
				return a.move - b.move;
			});
			
			boolean[][] visited = new boolean[N][N];
			
			pq.add(shark);
			visited[shark.x][shark.y] = true;
			
			boolean eatCheck = false;
			
			while (!pq.isEmpty()) {
				shark = pq.poll();
				int nowX = shark.x;
				int nowY = shark.y;
				int nowMove = shark.move;
				
				if (map[nowX][nowY] != 0 && map[nowX][nowY] < sharkSize) {
					map[nowX][nowY] = 0;
					eatCount++;
					totalMove += nowMove;
					shark = new Shark(nowX, nowY, 0);
					eatCheck = true;
					break;
				}
				
				for (int i=0; i<4; i++) {
					int nextX = nowX + dx[i];
					int nextY = nowY + dy[i];
					
					if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
						continue;
					}
					
					if (visited[nextX][nextY] || map[nextX][nextY] > sharkSize) {
						continue;
					}
					
					pq.add(new Shark(nextX, nextY, shark.move + 1));
					visited[nextX][nextY] = true;
				}
			}
			
			if (!eatCheck) {
				break;
			}
			
			if (sharkSize == eatCount) {
				sharkSize++;
				eatCount = 0;
			}
		}
		
		return totalMove;
	}
	
	

}