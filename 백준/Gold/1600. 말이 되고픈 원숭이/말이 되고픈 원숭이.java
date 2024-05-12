import java.util.*;
import java.io.*;

public class Main {
	
	// 원숭이의 좌표 정보 및 능력 사용횟수, 움직인 횟수 정보를 담고있는 내부 클래스
	static class Monkey {
		int x;
		int y;
		int use;	// 능력 사용 횟수
		int move;
		
		public Monkey(int x, int y, int use, int move) {
			this.x = x;
			this.y = y;
			this.use = use;
			this.move = move;
		}
	}
	
	static int K;	// 사용 가능한 능력 수
	static int W;	// 가로 길이
	static int H;	// 세로 길이
	static int[][] map;
	static boolean[][][] visited;	// 3차원 방문 배열 이용 (맨 앞 차원은 능력 사용한 횟수에 따른 방문 여부)
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] fourDx = {1, -1, 0, 0};
	static int[] fourDy = {0, 0, -1, 1};
	// 8가지 방향 배열
	static int[] eightDx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] eightDy = {1, 2, 2, 1, -1, -2, -2, 1};
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];	// [0][0] ~ [H-1][W-1]
		visited = new boolean[K+1][H][W];	// [0] ~ [K]
		
		for (int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int resultMove = bfs(0, 0);	// 시작 좌표에서 부터 원숭이 이동시키도록 너비우선탐색 실시
		System.out.println(resultMove);
	}
	
	// 원숭이를 시작지점에서 부터 도착지점까지 이동시키도록 하는 너비우선탐색 메서드
	public static int bfs(int startX, int startY) {
		// 너비우선탐색을 이용하기 위해 큐 선언 및 생성
		Queue<Monkey> queue = new LinkedList<>();
		queue.add(new Monkey(startX, startY, 0, 0));	// 큐에 원숭이 정보 저장
		visited[0][startX][startY] = true;
		
		while (!queue.isEmpty()) {
			// 원숭이의 현재 정보 큐에서 뽑아냄
			Monkey now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowUse = now.use;
			int nowMove = now.move;
			
			// 현재 도착지점에 도달한 경우
			if (nowX == H-1 && nowY == W-1) {
				return nowMove;	// 현재까지 이동한 횟수 반환
			}
			
			// 먼저 4가지 방향 탐색
			for (int i=0; i<4; i++) {
				int nextX = nowX + fourDx[i];
				int nextY = nowY + fourDy[i];
				
				// 탐색한 좌표가 [0][0] ~ [H-1][W-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
					continue;	// 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표가 이미 방문했거나 또는 장애물(1)인 경우
				if (visited[nowUse][nextX][nextY] || map[nextX][nextY] == 1) {
					continue;	// 다음 방향 탐색하도록 넘어감
				}
				
				// 탐색한 좌표 정보 및 원숭이 능력 사용 횟수 및 움직인 횟수 증가 정보 큐에 저장
				queue.add(new Monkey(nextX, nextY, nowUse, nowMove + 1));
				visited[nowUse][nextX][nextY] = true;	// 현재 원숭이가 능력 사용한 횟수의 탐색한 좌표 방문 처리
			}
			
			// 그 다음으로 원숭이가 능력을 사용했는지 확인
			// 원숭이가 능력을 K 미만으로 사용한 경우
			if (nowUse < K) {
				// 8가지 방향 탐색
				for (int i=0; i<8; i++) {
					int nextX = nowX + eightDx[i];
					int nextY = nowY + eightDy[i];
					
					// 탐색한 좌표가 [0][0] ~ [H-1][W-1] 이외의 좌표인 경우
					if (nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
						continue;	// 다음 방향 탐색하도록 넘어감
					}
					
					// 원숭이가 능력을 사용한 상태의 탐색한 좌표가 이미 방문했거나 또는 장애물(1)인 경우
					if (visited[nowUse+1][nextX][nextY] || map[nextX][nextY] == 1) {
						continue;
					}
					
					// 탐색한 좌표 정보 및 원숭이의 능력 사용 횟수 증가 및 움직인 횟수 증가 정보 큐에 저장
					queue.add(new Monkey(nextX, nextY, nowUse + 1, nowMove + 1));	
					visited[nowUse+1][nextX][nextY] = true;	// 원숭이가 능력을 사용한 상태로 탐색한 좌표 방문처리
				}
			}
		}
		
		// 너비우선탐색을 실시했는데도 도착지점에 도달하지 못한 경우 -1 반환
		return -1;
	}

}