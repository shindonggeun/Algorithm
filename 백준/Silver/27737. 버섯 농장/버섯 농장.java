import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int x, y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int M;
	static int K;
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 배열 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	static int mushroomCount = 0;
	static boolean mushroomUse = false;	// 문제 조건에서 버섯포자를 하나라도 사용하고 라는 조건때문에 boolean타입 변수 필요
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];	// (0, 0) ~ (N-1, N-1)
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 버섯이 자랄수 있는 칸이면서 동시에 방문하지 않은 좌표(칸)인 경우
				if(map[i][j] == 0 && !visited[i][j]) {
					mushroomUse = true;
					mushroomCount++;
					bfs(i, j, K);
				}
			}
		}
		
		int resultCount = M - mushroomCount;	// 최종으로 남은 버섯포자의 개수
		
		// 최종으로 남은 버섯포자의 개수가 0보다 작거나 또는 버섯포자를 하나라도 사용하지 않은 경우
		if(resultCount < 0 || !mushroomUse) {
			System.out.println("IMPOSSIBLE");
		}
		else {
			System.out.println("POSSIBLE");
			System.out.println(resultCount);
		}
	}
	
	public static void bfs(int startX, int startY, int k) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		k--;	// 버섯 한개 심었으므로 칸 줄여줌
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (N-1, N-1) 이외인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
					continue;
				}
				
				// 버섯을 심을 수 없는 칸이거나 이미 방문한 좌표인 경우
				if(map[nextX][nextY] == 1 || visited[nextX][nextY]) {
					continue;
				}
				// 최대 심을 수 있는 칸이 0보다 작은 경우 버섯 심을 수 없음(메서드 종료)
				if(k <= 0) {
					return;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
				k--;	// 버섯 한개 심었으므로 칸 줄여줌
			}
		}
	}

}
