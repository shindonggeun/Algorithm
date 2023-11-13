import java.util.*;
import java.io.*;

public class Main {
	
	// 바이러스가 있는 좌표를 저장해주는 내부 클래스
	static class Virus {
		int x;
		int y;
		
		public Virus(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;	// 세로 크기 
	static int M;	// 가로 크기 
	static int[][] map;
	// 4가지 방향 배열 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열 
	static int[] dy = {0, 0, -1, 1};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열 
	static int safeArea = Integer.MIN_VALUE;	// 안전영역 크기 최대값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// [0][0] ~ [N-1][M-1]
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 깊이우선탐색 실시
		// 벽 세운거 0개부터 실시
		dfs(0);
		
		System.out.println(safeArea);
	}
	
	// 벽 세우는 과정을 나타내는 메서드 (깊이우선탐색)
	public static void dfs(int wallCount) {
		// 벽 3개 세워지면 너비우선탐색 실시 (종료조건)
		if(wallCount == 3) {
			bfs();	// 바이러스 퍼트리기
			return;	// 메서드 종료
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 맵에서 해당 칸이 빈칸(0)인 경우 -> 백트래킹 
				if(map[i][j] == 0) {
					map[i][j] = 1;	// 벽(1)을 세웠다가
					dfs(wallCount+1);	// 벽 개수 늘려준 뒤
					map[i][j] = 0;	// 다시 벽 허물음(0)
				}
			}
		}
	}
	
	// 바이러스 퍼트리는 과정을 나타내는 메서드 (너비우선탐색)
	public static void bfs() {
		Queue<Virus> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];	// [0][0] ~ [N-1][M-1]
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 바이러스가 있는 좌표인 경우 큐에 저장
				if(map[i][j] == 2) {
					queue.add(new Virus(i, j));
					visited[i][j] = true;	// 해당 좌표 방문 처리
				}
			}
		}
		
		int[][] copyMap = new int[N][M];	// 맵의 원본을 복사해서 사용할 수 있도록
		
		// 2차원 배열 깊은복사 실시
		for(int i=0; i<N; i++) {
			copyMap[i] = map[i].clone();
		}
		
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			Virus now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 이미 방문한 좌표거나 또는 복사한 맵에서 탐색한 해당 좌표가 벽(1)인 경우
				if(visited[nextX][nextY] || copyMap[nextX][nextY] == 1) {
					continue;	// 넘어감
				}
				
				// 탐색한 좌표가 빈칸(0)인 경우
				if(copyMap[nextX][nextY] == 0) {
					copyMap[nextX][nextY] = 2;	// 해당 좌표에 바이러스 퍼트림
					visited[nextX][nextY] = true;	// 해당 좌표 방문 처리
					queue.add(new Virus(nextX, nextY));	// 큐에 해당 바이러스 정보 집어넣음
				}
			}
		}
		
		// 너비우선탐색이 다 끝난 경우 안전영역 크기 구해주기
		findSafeArea(copyMap);
	}
	
	// 안전영역 크기를 구해주는 메서드
	public static void findSafeArea(int[][] copyMap) {
		int tempArea = 0;	// 임시 안전영역 크기
		
		// 완전탐색 실시
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 복사한 맵에서 해당 좌표가 빈칸(0)인 경우
				if(copyMap[i][j] == 0) {
					tempArea++;	// 임시 안전영역 크기 증가
				}
			}
		}
		
		safeArea = Math.max(safeArea, tempArea);	// 안전영역크기 최대 갱신
	}

}
