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
	
	static int N;	// 세로 길이 (y 좌표)
	static int M;	// 가로 길이 (x 좌표)
	static int[][] map;
	static int[][] resultMap;
	// 4가지 방향 탐색 (상, 하, 좌, 우)
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 (상, 하)
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 (좌, 우)
	static int maxArea = Integer.MIN_VALUE;	// 안전 영역 최대 크기
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 벽 세우는 작업을 실시하기 위해 깊이우선탐색 이용 (백트래킹)
		// 모든칸에 벽을 세워서 경우를 따져야 하므로
		dfs(0);
	
		System.out.println(maxArea);
	}
	
	// 벽을 세우는 작업을 실시하기 위한 깊이우선탐색
	public static void dfs(int wallCount) {
		// 벽 3개 세워지면 너비우선탐색 실시
		if(wallCount == 3) {
			bfs();
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;	// 벽을 세웠다가
					dfs(wallCount+1);
					map[i][j] = 0;	// 다시 벽 허물음 (백트래킹 과정)
				}
			}
		}
		
	}
	
	// 바이러스 퍼트리는 작업을 실시하는 너비우선탐색
	public static void bfs() {
		boolean[][] visited = new boolean[N][M];	// (0, 0) ~ (M-1, N-1)
		Queue<Position> queue = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 바이러스가 있는 좌표는 큐에 집어넣음(너비우선탐색을 위해)
				if(map[i][j] == 2) {
					queue.add(new Position(i, j));
					visited[i][j] = true;
				}
			}
		}
		
		//원본 맵(연구소)를 바꾸지 않기 위한 카피본 사용 (깊은 복사)
        int copyMap[][] = new int[N][M];

        for (int i=0; i<N; i++) {
            copyMap[i] = map[i].clone();
        }
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 (0, 0) ~ (M-1, N-1) 이외의 좌표인 ㄱ경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				
				// 탐색한 좌표가 이미 방문한 좌표거나 또는 벽에 가로막힌 경우
				if(visited[nextX][nextY] || copyMap[nextX][nextY] == 1) {
					continue;
				}
				
				// 탐색한 좌표가 빈칸 인 경우
				if(copyMap[nextX][nextY] == 0) {
					copyMap[nextX][nextY] = 2;	// 해당 좌표 바이러스 퍼짐
					visited[nextX][nextY] = true;
					queue.add(new Position(nextX, nextY));
				}
			}
		}
		
		// 위의 너비우선탐색 다 실시했으면 카피본(복사본 맵)에서 안전영역 크기 확인해주기
		findSafeZone(copyMap);
	}
	// 안전영역 크기 구해주는 메서드
	public static void findSafeZone(int[][] copyMap) {
		int safeZone = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				 if(copyMap[i][j] == 0) {
					 safeZone++;
				 }
			}
		}
		maxArea = Math.max(maxArea, safeZone);
	}

}
