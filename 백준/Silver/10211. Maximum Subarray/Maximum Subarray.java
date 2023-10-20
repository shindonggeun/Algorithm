import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N+1];
			int[] dp = new int[N+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());	
			}
			
			int max = arr[1];
			for(int i=1; i<=N; i++) {
				if(dp[i-1] < 0) {
					dp[i-1] = 0;
				}
				dp[i] = dp[i-1] + arr[i];
				max = Math.max(max, dp[i]);
			}
			sb.append(max).append("\n");
		}
		System.out.print(sb);

	}

}
