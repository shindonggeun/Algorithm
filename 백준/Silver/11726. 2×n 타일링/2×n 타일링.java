import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// 각 크기가 설정될 때 (1 ~ 1000) 1X2, 2X1 타일로 직사각형을 채우는 방법의 수를 저장한 dp 배열
		int[] dp = new int[1000 + 1];

		// 초기값 세팅
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= N; i++) {
			// 해당 직사각형 크기가 i일 때, 타일링의 경우의 수는 i-1 경우의 수와 i-2 경우의 수를 합한 경우이다
			// i-1 경우의 수에서 세로 타일 붙임
			// i-2 경우의 수에서 가로 타일을 붙임
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
		}
		
		System.out.println(dp[N]);

	}

}