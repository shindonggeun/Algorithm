import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 세로 길이
	static int M;	// 가로 길이
	static int[][] map;
	// 4가지 방향 배열(우, 상, 좌, 하) -> 배열에서는 우, 하, 좌, 상
	static int[] dx = {0, 1, 0, -1};	// x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
	static int[] dy = {1, 0, -1, 0};	// y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// (0, 0) ~ (M-1, N-1)
		
		// 2차원배열에 각 원소에 들어가는 값들 입력받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int rotationGroupCount = Math.min(N, M) / 2;	// 회전을 돌려야하는 그룹의 개수 구해주기
		
		// 회전 횟수만큼 배열 회전시켜주기
		for(int i=0; i<R; i++) {
			// 반시계방향으로 회전하게끔 메서드 호출
			rotation(rotationGroupCount);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
		
	}
	
	// 반시계방향으로 배열 회전시키는 메서드
	public static void rotation(int count) {
		// 회전시킬 그룹 개수만큼 돌려주기
		for(int i=0; i<count; i++) {
			int nowX = i;
			int nowY = i;
			
			int temp = map[nowX][nowY];	// 마지막에 넣을 임시변수
			int start = 0;	// 우, 상, 좌, 하 방향으로 배열 회전시키게끔 해주는 시작변수 설정 -> 배열에서는 우, 하, 좌, 상
			
			// 배열 돌리기 과정
			while(start < 4) {
				int nextX = nowX + dx[start];
				int nextY = nowY + dy[start];
				
				// 범위내에 있는 경우 배열 돌려줄 수 있게끔
				if(nextX < N-i && nextY < M-i && nextX >= i && nextY >= i) {
					map[nowX][nowY] = map[nextX][nextY];
					nowX = nextX;
					nowY = nextY;
				}
				// 범위를 벗어난 경우 방향 전환해주기
				else {
					start++;
				}
			}
			map[i+1][i] = temp;	// 뺴놓은 값 넣어준다
			
		}
	}

}
