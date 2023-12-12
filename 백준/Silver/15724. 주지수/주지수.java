import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 영토의 세로 크기
	static int M;	// 영토의 가로 크기
	static int K;	// 직사각형 범위의 개수를 저장할 변수
	static int[][] dp;	// 누적합을 저장할 2차원 배열 dp

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][M+1];	// 누적합을 저장할 배열 초기화 [1][1] ~ [N][M]
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				int num = Integer.parseInt(st.nextToken());
				// 각 셀에 대해 왼쪽, 위쪽, 왼쪽 대각선 위쪽 셀의 누적합을 현재 셀값에 더한 후 적용
				// 위 + 왼쪽 - 왼쪽 대각선 위 + 현재 셀 
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + num;
			}
		}
		
		
		K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());	// 시작 x 좌표
			int startY = Integer.parseInt(st.nextToken());	// 시작 y 좌표
			int endX = Integer.parseInt(st.nextToken());	// 끝 x 좌표
			int endY = Integer.parseInt(st.nextToken());	// 끝 y 좌표
			// 범위 내 사람 수 계산
			int result = dp[endX][endY] - dp[startX-1][endY] - dp[endX][startY-1] + dp[startX-1][startY-1];
			
			sb.append(result).append("\n");
			
		}
		System.out.print(sb);
		
		
	}

}
