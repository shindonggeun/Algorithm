import java.io.*;

public class Main {

	static int K;
	static int N;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc=1; tc<=testCase; tc++) {
			K = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());
			
			dp = new int[K+1][N+1];
			
			for (int i=0; i<=K; i++) {
				dp[i][1] = 1; // i층의 1호는 거주자 1명
			}
			
			for (int i=1; i<=N; i++) {
				dp[0][i] = i; // 0층의 i호는 거주자 i명
			}
			
			for (int i=1; i<=K; i++) {
				for (int j=2; j<=N; j++) {
					dp[i][j] = dp[i][j-1] + dp[i-1][j];
				}
			}
			
			sb.append(dp[K][N]).append("\n");
		}
		
		System.out.print(sb);
	}

}