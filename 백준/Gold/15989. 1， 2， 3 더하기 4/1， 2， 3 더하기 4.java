import java.io.*;

public class Main {

	static int T; // 테스트 케이스 개수
	static int[] nums; // 각 테스트 케이스에서 주어진 정수 n을 저장하는 배열
	static int maxN; // 입력된 N(정수)값들 중 최대값 (DP 배열을 최적화 하기 위해 사용)
	static int[][] dp; // 2차원 dp 배열 (dp[i][j] = i를 1 ~ j(최대 3)까지 숫자로 만드는 경우의 수)
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		nums = new int[T]; // [0] ~ [T-1]
		
		for (int i=0; i<T; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			maxN = Math.max(nums[i], maxN);
		}
		
		dp = new int[maxN+1][4]; // dp 배열 초기화
		
		// 1. 초기값 설정하는 과정
		dp[1][1] = 1; // 1을 1만 사용해서 만드는 경우의 수 (1)
		dp[2][1] = 1; // 2를 1만 사용해서 만드는 경우의 수 (1+1)
		
		// 2를 1과 2를 사용해서 만드는 경우의 수 (단, 1+1은 이미 dp[2][1]에 포함되므로, 2만 사용한 경우만 포함하여 1)
		dp[2][2] = 1;
		
		dp[3][1] = 1; // 3을 1만 사용해서 만드는 경우의 수 (1+1+1)
		dp[3][2] = 1; // 3을 1과 2만 사용해서 만드는 경우의 수 (1+2)
		
		// 3을 1과 2, 3을 사용해서 만드는 경우의 수 (단, 1+1+1, 1+2 경우는 dp[3][1], dp[3][2]에 포함되므로, 3만 사용한 경우만 포함하여 1)
		dp[3][3] = 1; 
		
		// 2. dp 배열 채우는 과정
		for (int i=4; i<=maxN; i++) {
			// 1만 사용해서 i를 만드는 경우 (전 단계의 경우 그대로 가져옴)
			dp[i][1] = dp[i-1][1];
			
			// 1과 2를 사용해서 i를 만드는 경우
			// i에서 2를 사용하려면, i-2까지 1로만 만드는 경우와 2까지 허용한 경우를 더함
			dp[i][2] = dp[i-2][1] + dp[i-2][2];
			
			// 1, 2, 3을 사용해서 i를 만드는 경우
			// i에서 3을 사용하려면, i-3까지 1과 2만 사용한 경우와 3까지 허용한 경우를 더함
			dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
		}
		
		// 3. 결과 출력하는 과정 (각 테스트 케이스에 대해 dp[n][1] + dp[n][2] + dp[n][3] 계산 -> 1, 2, 3의 합으로 나타내는 방법의 수)
		sb = new StringBuilder();
		for (int i=0; i<T; i++) {
			int n = nums[i];
			sb.append(dp[n][1] + dp[n][2] + dp[n][3]).append("\n");
		}
		
		System.out.print(sb);
	}

}