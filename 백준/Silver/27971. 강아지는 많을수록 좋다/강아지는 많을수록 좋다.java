import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int A;
	static int B;
	static int[] left;
	static int[] right;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		left = new int[M];
		right = new int[M];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			left[i] = Integer.parseInt(st.nextToken());
			right[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		Arrays.fill(dp, 100000);
		dp[0] = 0;
		
		for(int k=1; k<=N; k++) {
			boolean valid = true;
			for(int i=0; i<M; i++) {
				if(k >= left[i] && k <= right[i]) {
					valid = false;
		            break;
				}
			}
			
			if(valid) {
				if(k >= A) {
					dp[k] = Math.min(dp[k], dp[k-A] + 1);
				}
				if (k >= B) {
	                dp[k] = Math.min(dp[k], dp[k - B] + 1);
	            }
			}
			
		}
		
		if(dp[N] == 100000) {
			dp[N] = -1;
		}
		
		System.out.println(dp[N]);
	}
	
	

}
