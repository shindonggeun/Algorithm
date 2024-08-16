import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 물건의 개수
	static int K;	// 배낭의 최대 무게
	static int W;	// 물건의 무게
	static int V;	// 물건의 가치
	static int[][] item;	// 각 물건의 무게와 가치를 저장할 2차원 배열
	static int[] dp;	// 각 무게마다 최대 가치를 가지는 dp 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		item = new int[N+1][2];	// [1][0] ~ [N][1]
		dp = new int[K+1];	// [0] ~ [K], 
		
		// 각 물건의 무게와 가치 정보를 입력
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			
			item[i][0] = W;	// 물건의 무게
			item[i][1] = V;	// 물건의 가치
		}
		
		dp[0] = 0;	// 0의 무게의 배낭의 최대 가치는 0
		
		// 모든 물건에 대해 반복
		for(int i=1; i<=N; i++) {
			// 현재 물건의 무게부터 최대 무게 K까지 역순으로 반복
			// 역순으로 하는 이유는 이전에 계산된 값에 영향을 주기 않기 위해서
			// 즉, 무게가 K인 경우부터 무게가 item[i][0]인 경우까지 모두 고려해준다
			for(int j=K; j>=item[i][0]; j--) {
				// dp[j]는 현재 무게 j에서의 최대 가치
				// dp[j - item[i][0]] + item[i][1]는 현재 물건을 배낭에 추가했을 때의 총 가치
				// 두 값을 비교하여 더 큰 값으로 dp[j]를 갱신
				// 즉, 해당 위치에 물건을 넣을 수 있는 경우
				// i - 1번째까지 고려했을 때 무게 j에서의 최대 가치 (최적해)와
				// i - 1번째 물건까지 고려했을 때 
				// 무게 j - item[i][0](현재무게)의 최대 가치 (최적해) + item[i][1] (현재 가치) 중 더 큰 값 선택
				dp[j] = Math.max(dp[j], dp[j - item[i][0]] + item[i][1]);
			}
		}
		
		System.out.println(dp[K]);

	}

}
