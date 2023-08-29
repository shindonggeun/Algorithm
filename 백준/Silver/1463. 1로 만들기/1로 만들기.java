import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		int[] dp = new int[X+1];	// dp[num] => 해당 숫자를 1로 만들 때 연산을 사용하는 횟수
		Arrays.fill(dp, -1);	// 메모되지 않은 상태들을 초기값 -1로 세팅
		 
		// 초기화 작업
		dp[0] = 0;	
		dp[1] = 0;
		
		// 다이나믹프로그래밍 상향식 풀이
		// 2부터 해당 숫자까지 탐색 시작
		for(int i=2; i<=X; i++) {
			dp[i] = dp[i-1] + 1;	// 해당 숫자 연산을 사용한 횟수 증가시킴
			// 2로 나누어지는 수이면
			if(i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i/2] + 1);	// 해당 수의 연산 횟수와 해당 수를 2로 나눌때 연산 사용한 횟수 + 1을 비교해서 가장 작은 값 세팅
			}
			
			// 3으로 나누어지는 수이면
			if(i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i/3] + 1);	// 해당 수의 연산 횟수와 해당 수를 3으로 나눌 때 연산 사용한 횟수 + 1을 비교해서 가장 작은 값 세팅
			}
		}
		
		System.out.println(dp[X]);	// 해당 수를 1로 만들 때 연산 사용한 횟수 출력
	}

}
