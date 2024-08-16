import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] coinArr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int testCase = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			
			coinArr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				coinArr[i] = Integer.parseInt(st.nextToken());
			}
			
			M = Integer.parseInt(br.readLine());
			
			dp = new int[M+1];
			
			dp[0] = 1;
			
			for (int coin: coinArr) {
				for (int i=coin; i<=M; i++) {
					dp[i] += dp[i - coin];
				}
			}
			
			sb.append(dp[M]).append("\n");
		}
		
		System.out.print(sb);
	}

}