import java.util.*;
import java.io.*;

public class Main {

	static int T;
	static int[] nums;
	static int maxN;
	static int[][] dp;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		nums = new int[T];
		
		for (int i=0; i<T; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			maxN = Math.max(nums[i], maxN);
		}
		
		dp = new int[maxN+1][4];
		
		dp[1][1] = 1; // 1
		dp[2][1] = 1; // 1+1
		dp[2][2] = 1; // 2
		dp[3][1] = 1; // 1+1+1
		dp[3][2] = 1; // 1+2
		dp[3][3] = 1; // 3
		
		for (int i=4; i<=maxN; i++) {
			dp[i][1] = dp[i-1][1];
			dp[i][2] = dp[i-2][1] + dp[i-2][2];
			dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
		}
		
		sb = new StringBuilder();
		for (int i=0; i<T; i++) {
			int n = nums[i];
			sb.append(dp[n][1] + dp[n][2] + dp[n][3]).append("\n");
		}
		
		System.out.print(sb);
	}

}