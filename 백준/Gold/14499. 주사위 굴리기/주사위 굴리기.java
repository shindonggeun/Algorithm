import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int X;
	static int Y;
	static int K;
	static int[][] map;
	// 명령에 따른 방향 배열 [1]부터 시작 -> (동, 서, 북, 남)
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	static int[] dice; // 각 주사위 면의 값을 저장하는 배열
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		dice = new int[6+1]; // [1] ~ [6], 주사위 모든 면 0 ([0]은 안씀)
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
			int dir = Integer.parseInt(st.nextToken());
			simulation(dir);
		}
		
		System.out.print(sb);
	}
	
	public static void simulation(int dir) {
		int nextX = X + dx[dir];
		int nextY = Y + dy[dir];
		
		if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
			return;
		}
		
		// 처음 주사위가 지도 위에 윗면이 1을 가리키고, 동쪽을 바라보는 방향이 3인 상태로 놓여 있으므로
		// 아랫면(value)은 6이다
		int value = dice[6];
		
		switch(dir) {
			// 이동 명령이 동(1)
			case 1:
				dice[6] = dice[4];
				dice[4] = dice[1];
				dice[1] = dice[3];
				dice[3] = value;
				break;
			// 서(2)
			case 2:
				dice[6] = dice[3];
				dice[3] = dice[1];
				dice[1] = dice[4];
				dice[4] = value;
				break;
			// 북(3)
			case 3:
				dice[6] = dice[5];
				dice[5] = dice[1];
				dice[1] = dice[2];
				dice[2] = value;
				break;
			// 남(4)
			case 4:
				dice[6] = dice[2];
				dice[2] = dice[1];
				dice[1] = dice[5];
				dice[5] = value;
				break;
		}
		
		// 주사위 윗면 출력
		sb.append(dice[1]).append("\n");
		
		// 이동한 칸의 값이 0인 경우
		if (map[nextX][nextY] == 0) {
			// 주사위의 바닥면(6)에 쓰여 있는 수(dice[6]) 복사
			map[nextX][nextY] = dice[6];
		}
		// 이동한 칸의 값이 0이 아닌 경우
		else {
			// 칸에 쓰여있는 수가 주사위의 바닥면(6)으로 복사
			dice[6] = map[nextX][nextY];
			// 칸에 쓰여있는 수 0으로 초기화
			map[nextX][nextY] = 0;
		}
		
		// 좌표 갱신
		X = nextX;
		Y = nextY;
	}

}