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
	
	static int N;	// 세로 길이 (x 좌표)
	static int M;	// 가로 길이 (y 좌표)
	static int[][] map;
	static int maxSize = Integer.MIN_VALUE;	// 안전영역크기
	// 4가지 방향 배열(상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열(상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열(좌, 우)
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
		
		for(int i=0; i<N; i++) {
			st =  new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 깊이우선탐색 실시 (백트래킹)
		// 벽 세운거 0개부터 실시
		dfs(0);
		
		System.out.println(maxSize);	// 결과 표시

	}
	
	// 벽 세우는 과정 메서드 (깊이우선탐색)
	public static void dfs(int wallCount) {
		// 벽 3개 세워지면 너비우선 탐색 실시
		if(wallCount == 3) {
			bfs();
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 맵에서 해당 칸이 빈칸(0)인 경우
				if(map[i][j] == 0) {
					map[i][j] = 1;	// 벽(1)을 세웠다가
					dfs(wallCount+1);	// 벽 개수 늘려준 뒤
					map[i][j] = 0;	// 다시 벽 허물음 (백트래킹 과정)
				}
			}
		}
		
	}
	
	// 바이러스 퍼트리는 과정 메서드 (너비우선탐색)
	public static void bfs() {
		Queue<Position> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];	// (0, 0) ~ (M-1, N-1)
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 바이러스가 있는 좌표인 경우 큐에 저장
				if(map[i][j] == 2) {
					queue.add(new Position(i, j));
					visited[i][j] = true;	// 해당 좌표 방문 처리
				}
			}
		}
		
		int[][] copyMap = new int[N][M];	// 맵의 원본을 사용하지 않고 복사해서 사용하기 위해 카피맵 선언
		
		// 2차원배열 깊은복사 실시
		for(int i=0; i<N; i++) {
			copyMap[i] = map[i].clone();
		}
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (상, 하, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// (0, 0) ~ (M-1, N-1) 이외의 좌표를 탐색한 경우 넘어감
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 복사한 맵에서 벽(1)이 세워져있는 경우거나 또는 이미 방문한 좌표인 경우 넘어감
				if(copyMap[nextX][nextY] == 1 || visited[nextX][nextY]) {
					continue;
				}
				
				// 복사한 맵에서 빈칸(0)인 경우
				if(copyMap[nextX][nextY] == 0) {
					copyMap[nextX][nextY] = 2;	// 바이러스(2)를 퍼트림
					visited[nextX][nextY] = true;	// 방문처리
					queue.add(new Position(nextX, nextY));	// 큐에 집어넣어줌
				}
			}
		}
		
		// 너비우선탐색이 다 끝나면 안전영역크기 구해준다
		findArea(copyMap);
	}
	
	// 안전영역크기를 구해주는 메서드
	public static void findArea(int[][] copyMap) {
		int tempSize = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 복사한 맵에서 빈칸(0)인 경우
				if(copyMap[i][j] == 0) {
					tempSize++;	// 안전영역 크기 증가
				}
			}
		}
		
		maxSize = Math.max(maxSize, tempSize);
	}
	
	

}
