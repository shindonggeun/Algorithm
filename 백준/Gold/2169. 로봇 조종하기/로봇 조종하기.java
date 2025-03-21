import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 행의 수
	static int M; // 열의 수
	static int[][] map; // 지형의 가치 정보
	static int[][] dp; // dp 배열 (dp[i][j] = (i, j)까지 탐사했을 때의 최대 가치 합

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // [0][0] ~ [N-1][M-1]
		dp = new int[N][M]; // [0][0] ~ [N-1][M-1]
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 첫 번째 행(i=0)은 왼쪽에서 오른쪽으로 이동하는 경우만 존재 (즉, 왼쪽에서 오른쪽으로 이동하는 경우만 고려하면 됨)
		dp[0][0] = map[0][0]; // 초기값 세팅
		for (int j=1; j<M; j++) {
			dp[0][j] = dp[0][j-1] + map[0][j];
		}
		
		// 2. 두 번째 행부터는 왼 -> 오, 오 -> 왼 이렇게 두 방향 스캔이 필요함
		for (int i=1; i<N; i++) {
			int[] left = new int[M]; // 왼쪽에서 오른쪽으로 이동하는 값 저장 할 배열
			int[] right = new int[M]; // 오른쪽에서 왼쪽으로 이동하는 값 저장 할 배열
			
			// (2-1) 왼쪽 -> 오른쪽 스캔
			// 왼쪽에서 오거나, 위쪽에서 오는 경우 중 최대값 선택
			left[0] = dp[i-1][0] + map[i][0];
			for (int j=1; j<M; j++) {
				left[j] = Math.max(left[j-1], dp[i-1][j]) + map[i][j];
			}
			
			// (2-2) 오른쪽 -> 왼쪽 스캔
			// 오른쪽에서 오거나, 위쪽에서 오는 경우 중 최대값 선택
			right[M-1] = dp[i-1][M-1] + map[i][M-1];
			for (int j=M-2; j>=0; j--) {
				right[j] = Math.max(right[j+1], dp[i-1][j]) + map[i][j];
			}
			
			// (2-3) 왼쪽/오른쪽 스캔 결과 중 더 큰 값 선택해서 dp 배열 채움
			for (int j=0; j<M; j++) {
				dp[i][j] = Math.max(left[j], right[j]);
			}
		}
		
		System.out.println(dp[N-1][M-1]); // 오른쪽 아래 칸 [N-1][M-1]까지의 최대 가치 합 출력
		

	}

}