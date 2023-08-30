import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[][] dp = new long[30][30];	// [0][0] ~ [29][29]
		
		// 다리를 겹치게 놓을 수 없다 => 다리 왼쪽 사이트의 개수 N, 오른쪽 사이트의 개수 M (N <= M)
		// 다리를 놓는 방법의 개수는 M개중 N개를 순서 상관없이 뽑는 방법의 개수와 같음 => 즉, 조합 공식 이용
		
		// 동적계획법 상향식 풀이
		// 초기화 작업
		// 조합성질 1. => nC0 = nCn = 1
		for(int i=1; i<30; i++) {
			dp[i][0] = 1;
			dp[i][i] = 1;
			dp[i][1] = i;	// M개중 1개만 뽑는 경우의 수 => M개
		}
		
		// 조합성질 2. => nCr = n-1Cr-1 + n-1Cr
		for(int i=2; i<30; i++) {
			for(int j=2; j<30; j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
			}
		}
		
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			sb.append(dp[M][N]).append("\n");
		}
		System.out.print(sb);

	}

}
