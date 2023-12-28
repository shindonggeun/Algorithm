import java.io.*;

public class Main {

	static int N;	
	static int[] dp;	// 각 숫자를 1, 2, 3의 합으로 나타내는 방법의 수를 저장한 dp 배열 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		// dp 배열 초기화
		dp = new int[11];	// [0] ~ [10] (문제에서 n은 11보다 작음)
		dp[1] = 1;	// 1을 만드는 방법은 1가지
		dp[2] = 2;	// 2을 만드는 방법은 2가지 (1+1, 2)
		dp[3] = 4;	// 3을 만드는 방법은 4가지 (1+1+1, 1+2, 2+1, 3)
		
		// 4부터 10까지 각 숫자를 만드는 방법의 수 계산
		for(int i=4; i<=10; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];	// 점화식 적용
		}
		
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			sb.append(dp[N]).append("\n");
		}
		System.out.print(sb);

	}

}
