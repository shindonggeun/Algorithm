import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 각 크기가 설정될 때 (1 ~ N) 1x2, 2x1, 2x2 타일로 직사각형을 채우는 방법의 수를 저장한 dp 배열
		int[] dp = new int[N + 1];

		// 초기값 세팅 과정
        dp[1] = 1;
        
        if (N > 1) {
            dp[2] = 3;
        }
        
        for (int i=3; i<=N; i++) {
        	// 해당 직사각형의 크기가 i일 때, 타일링의 경우의 수는 i-1 경우의 수와 i-2 경우의 수를 합한 경우이다
        	// i-1 경우의 수에서 1x2 타일을 추가하여 2xn 만들 수 있음
        	// i-2 경우의 수에서 2x1 타일 2개를 추가하여 2xn 만들 수 있음
        	// i-2 경우의 수에서 2x2 타일을 추가하여 2xn 만들 수 있음
        	dp[i] = (dp[i-1] + 2 * dp[i-2]) % 10007;
        }

        System.out.println(dp[N]);

	}

}