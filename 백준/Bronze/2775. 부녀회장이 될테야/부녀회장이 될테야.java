import java.io.*;

public class Main {

	static int K; // 층 수
	static int N; // 호 수
	static int[][] dp; // 해당 층수 및 호수에 거주하고 있는 사람의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc=1; tc<=testCase; tc++) {
			K = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());
			
			// [K][N] = K층 N호수에 거주하고 있는 사람의 수
			dp = new int[K+1][N+1]; // [0][1] ~ [K][N]
			
			for (int i=0; i<=K; i++) {
				dp[i][1] = 1; // i층의 1호는 거주자 1명
			}
			
			for (int i=1; i<=N; i++) {
				dp[0][i] = i; // 0층의 i호는 거주자 i명
			}
			
			// 1층부터 K층까지 순회
			for (int i=1; i<=K; i++) {
				// 2호부터 N호까지 순회
				for (int j=2; j<=N; j++) {
					// i층 j호에 거주하고 있는 사람의 수 = i층 j-1호에 거주하고 있는 사람의 수 + i-1층 j호에 거주하고 있는 사람의 수 저장 
					dp[i][j] = dp[i][j-1] + dp[i-1][j];
				}
			}
			
			// K층 N호에 거주하고 있는 사람의 수 출력
			sb.append(dp[K][N]).append("\n");
		}
		
		System.out.print(sb);
	}

}
