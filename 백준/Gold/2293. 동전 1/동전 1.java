import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int K;
	static int[] coinArr;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coinArr = new int[N];
		dp = new int[K+1]; // [0] ~ [K]
		
		for (int i=0; i<N; i++) {
			coinArr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 1;
		for (int i=0; i<N; i++) {
			for (int j=coinArr[i]; j<=K; j++) {
				dp[j] += dp[j - coinArr[i]];
			}
		}
		
		System.out.println(dp[K]);
	}

}