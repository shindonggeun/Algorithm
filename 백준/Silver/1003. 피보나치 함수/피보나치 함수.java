import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		dp = new int[41][2];	// N<=40, [0] => 0이 출력된 횟수, [1] => 1이 출력된 횟수
		
		dp[0][0] = 1;	// 0을 호출했을 때 0이 출력되는 횟수
		dp[0][1] = 0;	// 0을 호출했을 때 1이 출력되는 횟수
		dp[1][0] = 0;	// 1을 호출했을 때 0이 출력되는 횟수
		dp[1][1] = 1;	// 1을 호출했을 때 1이 출력되는 횟수
		
		// 상향식
		for(int i=2; i<=40; i++) {
			dp[i][0] = dp[i-1][0] + dp[i-2][0];
			dp[i][1] = dp[i-1][1] + dp[i-2][1];
		}
		
		for(int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
		}
		System.out.print(sb);
		
	}
	
	
	

}
