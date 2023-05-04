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
	
	static int N;	// 세로 길이(y축)
	static int M;	// 가로 길이(x축)
	static int K;	// 음식물 쓰레기의 개수
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 (상, 하, 좌, 우)
	static int[] dx = {-1, 1, 0, 0};	// x축 고정되어 있을 떄 이동방향 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축 고정되어 있을 때 이동방향 (좌, 우)
	static int size;	// 음식물의 크기
	static int max = 0;	// 가장 큰 음식물의 크기
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 세로 길이 저장
		M = Integer.parseInt(st.nextToken());	// 가로 길이 저장
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// (0,0) ~ (M-1, N-1)
		visited = new boolean[N][M];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());	// 위에서부터 시작되는 좌표 (N)
			int c = Integer.parseInt(st.nextToken());	// 왼쪽에서부터 시작되는 좌표 (M)
			
			map[r-1][c-1] = 1;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 방문 처리 안된 상태이면서 음식물 쓰레기가 떨어진 곳(1)인 경우 탐색
				if(!visited[i][j] && map[i][j] == 1) {
					size = 0;	// 음식물의 크기 0으로 초기화한 뒤
					bfs(i, j);	// 너비우선탐색 시작
					max = Math.max(max, size);	// 음식물 크기 비교해서 최대값 저장해줌
				}
			}
		}
		
		System.out.println(max);
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		size++;	// 음식물의 크기 증가
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 범위 벗어난 경우 
				// (0,0) ~ (M-1, N-1) 범위만 가능하다
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 탐색한 좌표가 방문처리 됐거나 또는 음식물이 없는 경우(0)
				if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
				size++;	// 음식물의 크기 증가
			}
		}
	}

}
