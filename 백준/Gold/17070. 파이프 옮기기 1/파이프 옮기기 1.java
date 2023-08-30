import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[][] map;
	static int moveCount;	// [1][1]에서 시작해서 [N][N]로 이동시킬 수 있는 방법의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];	// [1][1] ~ [N][N]
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		moveCount = 0;
		dfs(1, 2, 0);	// [1][2] 좌표지점에서 시작, 방향은 가로 방향(0)
		System.out.println(moveCount);
	}
	
	public static void dfs(int nowX, int nowY, int direction) {
		// 해당 좌표가 도착지점에 도달한 경우 (종료조건)
		if(nowX == N && nowY == N) {
			moveCount++;	// 도착지점에 도달한 경우의 수 증가
			return;	// 메서드 종료
		}
		
		// 파이트 방향이 가로 방향(동쪽)으로 놓여진 경우
		// 파이프 방향이 가로 방향(동쪽)으로 놓여진 경우에는 가로(동쪽), 오른쪽 대각선 방향으로 놓을 수 있다
		if(direction == 0) {
			// 가로 방향으로 이동 가능하면서 동시에 이동했을 때 해당 좌표가 빈칸(0)인 경우 (즉, 동쪽 방향으로 파이프 놓을 수 있는 경우)
			if(nowY+1 <= N && map[nowX][nowY+1] == 0) {
				dfs(nowX, nowY+1, 0);	// 파이프 가로로 놓기 (direction = 0)
			}
		}
		// 파이프 방향이 세로 방향(아래)으로 놓여진 경우
		// 파이프 방향이 세로 방향(아래)으로 놓여진 경우에는 세로(아래), 오른쪽 대각선 방향으로 놓을 수 있다
		else if(direction == 1) {
			// 세로 방향으로 이동 가능하면서 동시에 이동했을 때 해당 좌표가 빈칸(0)인 경우 (즉, 아래 방향으로 파이프 놓을 수 있는 경우)			
			if(nowX+1 <= N && map[nowX+1][nowY] == 0) {	
				dfs(nowX+1, nowY, 1);	// 파이프 세로로 놓기 (direction = 1)
			}
		}
		// 파이프 방향이 오른쪽 대각선 방향으로 놓여진 경우
		// 파이프 방향이 오른쪽 대각선 방향으로 놓여진 경우에는 가로(동쪽), 세로(아래), 오른쪽 대각선 방향으로 놓을 수 있다
		else if(direction == 2) {
			// 가로 방향으로 이동 가능하면서 동시에 이동했을 때 해당 좌표가 빈칸(0)인 경우 (즉, 동쪽 방향으로 파이프 놓을 수 있는 경우)
			if(nowY+1 <= N && map[nowX][nowY+1] == 0) {
				dfs(nowX, nowY+1, 0);	// 파이프 가로로 놓기 (direction = 0)
			}
			
			// 세로 방향으로 이동 가능하면서 동시에 이동했을 때 해당 좌표가 빈칸(0)인 경우 (즉, 아래 방향으로 파이프 놓을 수 있는 경우)			
			if(nowX+1 <= N && map[nowX+1][nowY] == 0) {	
				dfs(nowX+1, nowY, 1);	// 파이프 세로로 놓기 (direction = 1)
			}
		}
		
		// 오른쪽 대각선 방향은 파이프 방향이 어느 방향이던 공통적으로 체크해줘야 하므로 밑으로 빼줌
		// 대각선 방향으로 진행할 때 세 곳은 빈칸이여야 한다 (문제에서 색칠 된 곳 => 오른쪽, 대각선, 아래)			
		if(nowX+1 <= N && nowY+1 <= N && map[nowX][nowY+1] == 0 && map[nowX+1][nowY+1] == 0 && map[nowX+1][nowY] == 0) {
			dfs(nowX+1, nowY+1, 2);	// 파이프 오른쪽 대각선 방향으로 놓기 (direction = 2)
		}
	}

}
