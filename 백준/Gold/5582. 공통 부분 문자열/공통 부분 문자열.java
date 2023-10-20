import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int N = str1.length();
		int M = str2.length();
		
		int[][] dp = new int[N+1][M+1];
		int maxLength = 0;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
					maxLength = Math.max(maxLength, dp[i][j]);
				}
			}
		}
		
		System.out.println(maxLength);

	}

}
