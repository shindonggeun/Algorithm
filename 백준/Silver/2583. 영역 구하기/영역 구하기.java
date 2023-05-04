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
	
	static int N;	// 너비 (x축 길이)
	static int M;	// 높이 (y축 길이)
	static int K;	// 직사각형 개수 (K개의 직사각형 좌표가 주어짐)
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// 상, 하 (x축이 고정된채로 이동하는 배열)
	static int[] dy = {0, 0, -1, 1};	// 좌, 우 (y축이 고정된채로 이동하는 배열)
	static int num;	// 직사각형 영역을 구분해주는 숫자
	static int[] rect;	// 각 영역으로 분리된 직사각형의 개수들을 저장해주는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());	// 높이 입력받음 (y축 길이)
		N = Integer.parseInt(st.nextToken());	// 너비 입력받음 (x축 길이)
		K = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		visited = new boolean[M][N];
		num = 0;
		rect = new int[M*N];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int startX=y1; startX<y2; startX++) {
				for(int startY=x1; startY<x2; startY++) {
					map[startX][startY] = 1;	// 색칠된 영역
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				// 방문 안했으면서 색칠 안된 영역(0)인 부분 탐색하기
				if(!visited[i][j] && map[i][j] == 0) {
					num++;
					bfs(i, j);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		Arrays.sort(rect);	// 각 영역으로 분리된 직사각형의 개수들이 저장된 배열 오름차순 정렬
		
		for(int i: rect) {
			if(i != 0) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(num);
		System.out.println(sb);
	}
	
	public static void bfs(int startX, int startY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		rect[num]++;
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 이동한 x, y좌표가 범위를 벗어난 경우 (음수좌표 또는 (N-1, M-1) 좌표 넘어간 경우)
                // 맵의 좌표는 (0,0) ~ (N-1, M-1) 까지이다
				if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
					continue;
				}
				// 방문한 좌표거나 색칠된 영역인 경우(1) 건너뜀 (색칠 안된 영역을 탐색해야 하므로)
				if(visited[nextX][nextY] || map[nextX][nextY] == 1) {
					continue;
				}
				
				queue.add(new Position(nextX, nextY));
				visited[nextX][nextY] = true;
				rect[num]++;
			}
		}
	}

}
