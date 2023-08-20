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
	
	static int N;	// 행의 개수
	static int M;	// 열의 개수
	static int D;	// 궁수 공격 사정거리
	static int[][] map;	// 원본 맵
	static int[] archerArr;	// 궁수의 열 좌표를 조합으로 뽑아내서 저장한 배열
	static int enemyCount;	// 적의 수
	static int enemyKillMaxCount;	// 적을 죽일 수 있는 최대 수
	
	// 3가지 방향 배열 (좌, 상, 우)
	static int[] dx = {0, -1, 0};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {-1, 0, 1};	// y축이 고정되어 있을 떄 x좌표가 움직이는 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1) -> [0][0] ~ [N-1][M-1]
		archerArr = new int[3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					enemyCount++;
				}
			}
		}
		
		enemyKillMaxCount = Integer.MIN_VALUE;
		setArcher(0, 0);
		System.out.println(enemyKillMaxCount);
	}
	
	// 백트래킹 메서드 (조합 메서드) -> 궁수의 행 위치를 조합으로 뽑아낼 메서드
	public static void setArcher(int depth, int idx) {
		// 해당 깊이가 3이 된 경우 (즉, 궁수의 인원이 3명이 충족 된 경우)
		if(depth == 3) {
			enemyAttack();	// 적 공격하러 가기
			return;	// 메서드 종료
		}
		
		for(int i=idx; i<M; i++) {
			// 궁수의 열 위치 저장 
			// 행의 위치를 저장할 필요가 없는게 격자판의 N번행의 바로 아래(N+1번 행)의 모든칸에 성이 있는데
			// 궁수는 성이 있는 칸에 배치할 수 있으므로 (이미 행의 위치가 정해져있음)
			archerArr[depth] = i;	
						  
			setArcher(depth+1, i+1);	// 다음 궁수의 행 위치 선택을 위해 depth를 1 증가시키면서 재귀 호출
		}
	}
	
	public static void enemyAttack() {
		int[][] playMap = copyMap();
		boolean[][] killVisited;
		
		int killCount = 0;
		
		for(int i=0; i<N; i++) {
			killVisited = new boolean[N][M];
			
			// 궁수들의 y좌표 뽑기
			for(int y: archerArr) {
				// 궁수가 있는 좌표에 적이 있는 경우 (즉, 거리가 1인 위치에 적이 바로 존재하는 경우)
				if(playMap[N-1][y] == 1) {
					killVisited[N-1][y] = true;
				}
				// 궁수가 있는 좌표에 적이 없는 경우 (즉, 거리가 1인 위치가 아닌 경우)
				else {
					// 거리가 1인 위치에서부터 거리 넓혀가면서 너비우선 탐색 실시 (적 탐색하기)
					bfs(N-1, y, killVisited, playMap);	 
				}
			}
		
			killCount += enemyKill(killVisited, playMap);
			
			enemyMove(playMap);
		}
		
		enemyKillMaxCount = Math.max(enemyKillMaxCount, killCount);
	}
	
	public static int[][] copyMap() {
		int[][] copyMap = new int[N][M];
		
		for(int i=0; i<N; i++) {
			copyMap[i] = map[i].clone();
		}
		
		return copyMap;
	}
	
	public static void bfs(int startX, int startY, boolean[][] killVisited, int[][] playMap) {
		Queue<Position> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.add(new Position(startX, startY));
		visited[startX][startY] = true;
		
		for(int i=1; i<D; i++) {
			int size = queue.size();
			
			// 새로 추가된 좌표만 탐색할 수 있게끔 큐의 사이즈만큼 돌리기
			for(int j=0; j<size; j++) {
				Position now = queue.poll();
				int nowX = now.x;
				int nowY = now.y;
				
				// 3가지 방향 탐색 (좌, 상, 우)
				for(int k=0; k<3; k++) {
					int nextX = nowX + dx[k];
					int nextY = nowY + dy[k];
					
					if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
						continue;
					}
					
					if(visited[nextX][nextY]) {
						continue;
					}
					
					// 탐색한 좌표에서 적 발견한 경우
					if(playMap[nextX][nextY] == 1) {
						killVisited[nextX][nextY] = true;
						return;	// 메서드 종료
					}
					
					queue.add(new Position(nextX, nextY));
					visited[nextX][nextY] = true;
				}
			}
		}
 	}
	
	public static int enemyKill(boolean[][] killVisited, int[][] playMap) {
		int killCount = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 해당 좌표에 있는 적 죽였으면
				if(killVisited[i][j]) {
					killCount++;	// 적 죽인 수 증가
					playMap[i][j] = 0;	// 게임맵에 적 없앤 표시(빈칸(0)으로 만들기)
				}
			}
		}
		
		return killCount;
	}
	
	public static void enemyMove(int[][] playMap) {
		// 맨 밑에 행부터 탐색해서 한칸씩 적 내려올 수 있게끔
		for(int i=N-1; i>=0; i--) {
			for(int j=0; j<M; j++) {
				// 게임맵에 적(1)이 있는 경우
				if(playMap[i][j] == 1) {
					playMap[i][j] = 0;	// 해당 좌표 일단 빈칸(0)으로 만들어 준 뒤
					
					// 한칸 내려도 범위 넘지 않으면
					if(i+1 < N) {
						playMap[i+1][j] = 1;	
					}
				}
			}
		}
	}
	
	

}
