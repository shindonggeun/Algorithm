import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int K;
	static int[] coinArr;
	static int[] dp;	// 각 금액을 만드는데 필요한 동전 개수를 저장한 dp 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coinArr = new int[N];
		dp = new int[K+1];
		
		// 사용할 수 있는 동전들 입력
		for(int i=0; i<N; i++) {
			coinArr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;	// 0원을 만드는데 필요한 동전 개수는 0개

		// 각 사용할 수 있는 동전들 다 이용하기
		for(int coin: coinArr) {
			// 해당 동전의 값어치부터 K원까지 각 금액을 만드는데 필요한 최소 동전 개수 갱신하는 작업
			for(int j=coin; j<=K; j++) {
				if(dp[j - coin] != Integer.MAX_VALUE) {
					dp[j] = Math.min(dp[j], dp[j - coin] + 1);
				}
			}
		}
		
		if(dp[K] == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(dp[K]);
		}
	}

}
