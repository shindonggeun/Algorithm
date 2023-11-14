import java.util.*;
import java.io.*;

public class Main {
	
	// 원숭이가 움직인 좌표 정보 및 움직임수, 능력수를 저장하는 내부 클래스
	static class Monkey {
		int x;
		int y;
		int moveCount;	// 움직임 수
		int abilityCount;	// 말처럼 움직이기 위해 사용한 능력 수
		
		public Monkey(int x, int y, int moveCount, int abilityCount) {
			this.x = x;
			this.y = y;
			this.moveCount = moveCount;
			this.abilityCount = abilityCount;
		}
	}
	
	static int K;	// 사용 가능한 능력 수
	static int W;	// 가로 길이
	static int H;	// 세로 길이
	static int[][] map;	// 격자판
	static boolean[][][] visited;	// 3차원 방문 배열 (맨 앞 차원은 능력 사용한 횟수에 따른 여부)
	// 4가지 방향 배열 (하, 상, 좌, 우)
	static int[] fourDx = {1, -1, 0, 0};
	static int[] fourDy = {0, 0, -1, 1};
	// 8가지 방향 배열
	static int[] eightDx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] eightDy = {1, 2, 2, 1, -1, -2, -2, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];	// [0][0] ~ [H-1][W-1]
		visited = new boolean[K+1][H][W];	// [0] ~ [K]
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 원숭이가 시작지점에서 시작하여 도착지까지 이동 시킨 뒤 원숭이의 움직임 수를 저장
		int minMoveCount = monkeyMove(0, 0);	
		System.out.println(minMoveCount);

	}
	
	// 원숭이가 시작지점에서 도착지까지 이동하는 메서드 (너비우선탐색 알고리즘 이용)
	public static int monkeyMove(int startX, int startY) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Monkey> queue = new LinkedList<>();
		queue.add(new Monkey(startX, startY, 0, 0));	// 시작좌표 정보 및 원숭이 움직임 수, 사용한 능력 수 큐에 저장
		visited[0][startX][startY] = true;	// 원숭이가 사용한 능력 수에 따른 시작 좌표 방문 처리
		
		// 너비우선탐색 알고리즘 이용
		while(!queue.isEmpty()) {
			// 현재 좌표 정보 및 원숭이의 정보 (움직임 수, 사용한 능력 수) 큐에서 뽑아냄
			Monkey now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowMove = now.moveCount;
			int nowAbility = now.abilityCount;
			
			// 현재 좌표 정보가 도착지점에 도달한 경우
			if(nowX == H-1 && nowY == W-1) {
				return nowMove;	// 현재 원숭이가 움직인 수 반환
			}
			
			// 먼저 4가지 방향 탐색 (하, 상, 좌, 우)
			for(int i=0; i<4; i++) {
				int nextX = nowX + fourDx[i];
				int nextY = nowY + fourDy[i];
				
				// 탐색한 좌표가 [0][0] ~ [H-1][W-1] 이외의 좌표인 경우
				if(nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
					continue;	// 넘어감	(다음 방향 탐색하게끔)
				}
				
				// 현재 사용한 능력수에 따른 탐색한 좌표가 이미 방문처리 됐거나 또는 장애물(1)인 경우
				if(visited[nowAbility][nextX][nextY] || map[nextX][nextY] == 1) {
					continue;	// 넘어감	(다음 방향 탐색하게끔)
				}
				
				// 탐색한 좌표 정보 및 원숭이의 움직임 수 및 사용한 능력 수 큐에 저장
				queue.add(new Monkey(nextX, nextY, nowMove + 1, nowAbility));
				visited[nowAbility][nextX][nextY] = true;	// 사용한 능력 수에 따른 탐색한 좌표 정보 방문처리
			}
			
			// 현재 사용한 능력 수가 K 미만인 경우 (말의 능력 사용 가능)
			if(nowAbility < K) {
				// 말의 능력을 사용하므로 8가지 방향 탐색
				for(int i=0; i<8; i++) {
					int nextX = nowX + eightDx[i];
					int nextY = nowY + eightDy[i];
					
					// 탐색한 좌표가 [0][0] ~ [H-1][W-1] 이외의 좌표인 경우
					if(nextX < 0 || nextY < 0 || nextX >= H || nextY >= W) {
						continue;	// 넘어감 (다음 방향 탐색하게끔)
					}
					
					// 사용한 능력수에 따른 탐색한 좌표가 이미 방문처리 됐거나 또는 장애물(1)인 경우
					// 말의 능력 사용 했으므로 nowAbility + 1 해줘야 한다
					if(visited[nowAbility+1][nextX][nextY] || map[nextX][nextY] == 1) {
						continue;	// 넘어감	(다음 방향 탐색하게끔)
					}
					
					// 탐색한 좌표 정보 및 원숭이의 이동 수 및 사용한 능력 수(nowAbility+1) 큐에 저장
					queue.add(new Monkey(nextX, nextY, nowMove + 1, nowAbility + 1));
					visited[nowAbility+1][nextX][nextY] = true;	// 사용한 능력 수에 따른 탐색한 좌표 방문처리
				}
			}
		}
		
		// 위의 너비우선탐색을 다 진행했는데도 도착지점에 도달하지 못한 경우 -1 반환
		return -1;
	}

}
