import java.io.*;

public class Main {
	
	static int N;	// 계단의 개수
	static int[] scoreArr;	// 각 계단을 오를때마다 획득할 수 있는 점수들을 저장한 배열
	static int[] dp;	// 각 계단에 도달했을 때 얻을 수 있는 최대 점수을 저장한 dp 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		scoreArr = new int[N+1];
		dp = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			scoreArr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 0;
		dp[1] = scoreArr[1];
		
		// 2번째 계단까지 도달할 때 얻을 수 있는 최대 점수는 1층 계단의 점수 + 2층 계단의 점수
		// 즉, 계단 한개씩 이어 오르기
		if(N >= 2) {
			dp[2] = scoreArr[1] + scoreArr[2];
		}
		
		for(int i=3; i<=N; i++) {
			// 1. i-1번째 계단을 밟고 i번째 계단으로 오르는 경우
			// 이 경우, i-2번째 계단은 밟을 수 없음 (연속 3개 계단 규칙 위반) 
			// 따라서, dp[i-3] + scoreArr[i-1] + scoreArr[i]
			// 2. i-2번째 계단에서 바로 i번째 계단으로 오르는 경우
			// dp[i-2] + scoreArr[i]
			dp[i] = Math.max(dp[i-2], dp[i-3] + scoreArr[i-1]) + scoreArr[i];
		}
		
		// 마지막 도착 계단까지 밟았을 때 얻을 수 있는 총 점수 출력
		System.out.println(dp[N]);
	}

}
