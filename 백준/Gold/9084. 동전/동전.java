import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 동전의 가지 수
	static int M; // 동전들을 이용해서 만들 금액
	static int[] coinArr; // 각각의 동전들을 저장할 배열
	static int[] dp; // dp[i] = 해당 금액을 만들 수 있는 경우의 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int testCase = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력
		
		StringBuilder sb = new StringBuilder();
		for (int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			
			coinArr = new int[N]; // [0] ~ [N-1]
			
			st = new StringTokenizer(br.readLine());
			// 각 동전들의 금액 저장
			for (int i=0; i<N; i++) {
				coinArr[i] = Integer.parseInt(st.nextToken());
			}
			
			M = Integer.parseInt(br.readLine());
			
			dp = new int[M+1]; // [0] ~ [M]
			
			dp[0] = 1; // 0원을 만드는 경우의 수를 1로 설정 (공집합인 경우 -> 즉, 아무 동전도 사용하지 않은 경우)
			
			// 각 동전들에 대해 경우의 수 계산
			for (int coin: coinArr) {
				// 현재 동전의 금액부터 목표 금액(M)까지 순회 (동전 금액 1씩 증가시키면서)
				for (int i=coin; i<=M; i++) {
					// 현재 동전의 금액으로 만들 수 있는 경우의 수에 가지고 있는 현재 동전의 금액을 사용하기 전의 총 금액을 만들 수 있는 경우의 수를 누적함
					// dp[i - coin]은 현재 동전을 사용하기 전의 경우의 수를 의미
					dp[i] += dp[i - coin];
				}
			}
			
			// 해당 목표 금액을 만들기 위한 경우의 수 출력하기 위해 StringBuilder에 저장
			sb.append(dp[M]).append("\n");
		}
		
		System.out.print(sb);
	}

}