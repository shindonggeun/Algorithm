import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] dp;	// 각 해당하는 수를 1, 2, 3의 합으로 나타내는 방법의 수 (dp배열)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		dp = new int[11];	// [0] ~ [10]
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int i=4; i<=10; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			sb.append(dp[N]).append("\n");
		}
		System.out.print(sb);

	}

}
