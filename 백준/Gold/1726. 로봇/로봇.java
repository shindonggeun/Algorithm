import java.util.*;
import java.io.*;

public class Main {
	
	// 로봇의 정보를 담고있는 내부 클래스
	static class Robot {
		int x;
		int y;
		int dir;
		int count;	// 명령 횟수
		
		public Robot(int x, int y, int dir, int count) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.count = count;
		}
	}
	
	static int M;	// 세로 길이
	static int N;	// 가로 길이
	static int[][] map;
	static boolean[][][] visited;	// 방향을 포함한 해당 좌표 방문 여부를 판단하는 배열
	// 4가지 방향 배열 (index 1부터 시작) -> 동(1), 서(2), 남(3), 북(4)
	static int[] dx = {0, 0, 0, 1, -1};
	static int[] dy = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];	// [0][0] ~ [M-1][N-1]
		visited = new boolean[5][M][N];	// [1] ~ [4] (동, 서, 남, 북)
		
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 로봇 출발 지점 및 출발 방향 입력
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken()) - 1;
		int startY = Integer.parseInt(st.nextToken()) - 1;
		int startDir = Integer.parseInt(st.nextToken());
		
		// 로봇 도착 지점 및 도착 방향 입력
		st = new StringTokenizer(br.readLine());
		int endX = Integer.parseInt(st.nextToken()) - 1;
		int endY = Integer.parseInt(st.nextToken()) - 1;
		int endDir = Integer.parseInt(st.nextToken());
		
		// 너비우선탐색을 실시하여 로봇 최소 명령 횟수 계산
		int minCount = bfs(startX, startY, startDir, endX, endY, endDir);
		System.out.println(minCount);
	}
	
	// 로봇의 최소 명령을 계산하기 위한 너비우선탐색 메서드
	public static int bfs(int startX, int startY, int startDir, int endX, int endY, int endDir) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Robot> queue = new LinkedList<>();
		queue.add(new Robot(startX, startY, startDir, 0));	// 로봇의 출발 지점 및 초기 방향, 명령 횟수 큐에 저장
		visited[startDir][startX][startY] = true;	// 초기 방향 및 출발지점 방문 처리
		
		// 너비우선탐색 실시
		while (!queue.isEmpty()) {
			// 로봇의 현재 정보 큐에서 뽑아냄
			Robot now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			int nowDir = now.dir;
			int nowCount = now.count;
			
			// 목표 위치 및 방향에 도달한 경우
			if (nowX == endX && nowY == endY && nowDir == endDir) {
				return nowCount;	// 로봇의 최소 명령 횟수 반환
			}
			
			// 명령 Go K -> 1 ~ 3칸까지 이동 가능
			for (int k=1; k<=3; k++) {
				int nextX = nowX + dx[nowDir] * k;
				int nextY = nowY + dy[nowDir] * k;
				
				// 이동한 좌표가 [0][0] ~ [M-1][N-1] 이외의 좌표인 경우
				if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
					break;	// 이동 종료
				}
				
				// 이동한 좌표가 궤도가 없는 좌표(1)인 경우
				if (map[nextX][nextY] == 1) {
					break;	// 이동 종료
				}
				
				// 로봇의 현재 방향 및 이동한 좌표가 방문처리가 안된 경우 
				if (!visited[nowDir][nextX][nextY]) {
					visited[nowDir][nextX][nextY] = true;	// 해당 로봇의 현재 방향 상태 및 이동한 좌표 방문처리
					queue.add(new Robot(nextX, nextY, nowDir, nowCount + 1));	// 큐에 로봇의 현재 상태 저장
				}
			}
			
			// 명령 Turn Dir
			int left = 0;	// 왼쪽으로 90도 회전했을 때 가리키는 방향
			int right = 0;	// 오른쪽으로 90도 회전했을 때 가리키는 방향
			
			// 왼쪽으로 90도 회전하는 경우 -> 동(1) -> 북(4) -> 서(2) -> 남(3)
			// 오른쪽으로 90도 회전하는 경우 -> 동(1) -> 남(3) -> 서(2) -> 북(4)
			switch (nowDir) {
            	case 1: // 현재 방향이 동쪽인 경우
                	left = 4; // 동쪽(1) -> 북쪽(4)
                	right = 3; // 동쪽(1) -> 남쪽(3)
                	break;
            	case 2: // 현재 방향이 서쪽인 경우
                	left = 3; // 서쪽(2) -> 남쪽(3)
                	right = 4; // 서쪽(2) -> 북쪽(4)
                	break;
            	case 3: // 현재 방향이 남쪽인 경우
                	left = 1; // 남쪽(3) -> 동쪽(1)
                	right = 2; // 남쪽(3) -> 서쪽(2)
                	break;
            	case 4: // 현재 방향이 북쪽인 경우
            		left = 2; // 북쪽(4) -> 서쪽(2)
            		right = 1; // 북쪽(4) -> 동쪽(1)
            		break;
			}
			
			// 로봇이 왼쪽으로 회전한 상태에서 현재 좌표가 방문처리 안된 경우
			if (!visited[left][nowX][nowY]) {
				visited[left][nowX][nowY] = true;	// 로봇이 왼쪽으로 회전한 상태 및 현재 좌표 방문 처리
				queue.add(new Robot(nowX, nowY, left, nowCount + 1));	// 큐에 로봇의 현재 상태 저장
			}
			
			// 로봇이 오른쪽으로 회전한 상태에서 현재 좌표가 방문처리 안된 경우
			if (!visited[right][nowX][nowY]) {
				visited[right][nowX][nowY] = true;	// 로봇이 오른쪽으로 회전한 상태 및 현재 좌표 방문 처리
				queue.add(new Robot(nowX, nowY, right, nowCount + 1));	// 큐에 로봇의 현재 상태 저장
			}
		}
		
		// 로봇이 도착한 상태의 방향 및 도착지점에 도달할 수 없는 경우
		return -1;
	}

}