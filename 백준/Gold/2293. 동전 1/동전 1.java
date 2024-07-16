import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 동전의 종류
	static int K; // 동전들 가치의 합
	static int[] coinArr; // 각각의 동전들의 가치를 저장할 배열
	static int[] dp; // [i] = 해당 가치를 만들 수 있는 경우의 수
	
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
		
		dp[0] = 1; // 0원을 만드는 경우의 수를 1로 설정 (공집합인 경우 -> 즉, 아무 동전도 사용하지 않은 경우)
		
		// 각 동전에 대해 경우의 수를 계산
		for (int i=0; i<N; i++) {
			// 현재 동전의 가치부터 목표 가치 K원까지 순회
			for (int j=coinArr[i]; j<=K; j++) {
				// 현재 동전의 가치로 만들 수 있는 경우의 수에 가지고 있는 현재 동전의 가치를 사용하기 전의 총 가치를 만들 수 있는 경우의 수를 누적함
				// dp[j - coinArr[i]]는 현재 동전을 사용하기 전의 경우의 수를 의미함
				dp[j] += dp[j - coinArr[i]];
			}
		}
		
		// 목표 가치 K원을 만드는 경우의 수 출력
		System.out.println(dp[K]);
	}

}