import java.util.*;
import java.io.*;

public class Main {

	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[][] dp = new long[31][31];	// [1][1] ~ [29][29]
		
		for(int i=1; i<=30; i++) {
			dp[i][1] = i;	// M개중 1개만 뽑는 경우의 수 => M개
			
		}
		
		// 조합성질 1. => nC0 = nCn = 1
		// 조합성질 2. => n+1Cr+1 = nCr + nCr-1
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
