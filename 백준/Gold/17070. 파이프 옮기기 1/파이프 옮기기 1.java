import java.util.*;
import java.io.*;

public class Main {

	static int[][] map;
	static int count;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		count = 0;
		dfs(1, 2, 0);	// x, y, direction(방향) -> 초기에 방향 가로로 시작함
		
		System.out.println(count);	// (N,N)으로 이동시킬 수 있는 방법의 수 출력

	}
	
	// 방향이 0이면 가로, 1이면 세로, 2이면 오른쪽 대각선
	public static void dfs(int x, int y, int direction) {
		// (N, N)에 도달했으면 종료
		if(x == N && y == N) {
			count++;	// (N, N)까지 도달할 수 있는 방법의 수 증가
			return;
		}
		
		// 파이프 방향이 가로인 경우 갈 수 있는 경우는 동쪽과 대각선 오른쪽 대각선 방향임 (2가지)
		if(direction == 0) {
			// 동쪽으로 갈 수 있는 경우
			if(y + 1 <= N && map[x][y+1] == 0) {
				dfs(x, y+1, 0);	// 파이프 방향 가로로
			}
		}
		// 파이프 방향이 세로인 경우 갈 수 있는 경우는 남쪽과 오른쪽 대각선 방향임 (2가지)
		else if(direction == 1) {
			// 남쪽으로 갈 수 있는 경우
			if(x + 1 <= N && map[x+1][y] == 0) {
				dfs(x+1, y, 1);	// 파이프 방향 세로로
			}
		}
		// 파이프 방향이 오른쪽 대각선인 경우 갈 수 있는 경우는 동쪽과 남쪽, 오른쪽 대각선 방향임 (3가지)
		else if(direction == 2) {
			// 동쪽으로 갈 수 있는 경우
			if(y + 1 <= N && map[x][y+1] == 0) {
				dfs(x, y+1, 0);	// 파이프 방향 가로로
			}
			// 남쪽으로 갈 수 있는 경우
			if(x + 1 <= N && map[x+1][y] == 0) {
				dfs(x+1, y, 1);		// 파이프 방향 세로로
			}
		}
		
		// 오른쪽 대각선 방향은 파이프 방향이 어느 방향이던 공통적으로 체크해줘야므로 밑으로 빼줌
		// 대각선 방향으로 진행할 떄 세 곳은 빈칸이여야함 (문제에서 색칠된 곳)
		// 즉 가로 map[x][y+1] == 0, 세로 map[x+1][y] == 0, 대각선 map[x+1][y+1] == 0
		if(x + 1 <= N && y + 1 <= N && map[x+1][y] == 0 && map[x][y+1] == 0 && map[x+1][y+1] == 0) {
			dfs(x+1, y+1, 2);	// 파이프 방향 대각선으로
		}
	}

}