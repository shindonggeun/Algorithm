import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int D = Integer.parseInt(st.nextToken());	// 할머니가 호랑이를 만나서 무사히 넘어온 D째날 입력
		int K = Integer.parseInt(st.nextToken());	// D째날에 준 떡의 개수
		
		int[] dp = new int[D+1];	// D일차에 각각 준 떡을 저장하는 dp배열
		dp[D] = K;
		
		int result = 1;	// 초기 떡 개수
		while(true) {
			dp[1] = result;	// 1일차에 해당 떡 개수 설정하고 시작
			// 2일차 떡 개수 늘려가는 과정
			for(int i=result; i<K; i++) {
				dp[2] = i;	// 2일차의 떡 개수
				
				for(int j=3; j<D; j++) {
					// 각 날에 준 떡의 개수를 피보나치 수열을 이용하여 계산
					dp[j] = dp[j-1] + dp[j-2];
				}
				// D째날의 하루 전과 이틀전의 떡 준 개수 합이 D째날에 준 떡의 개수와 같아지는 경우
				if(dp[D] == dp[D-1] + dp[D-2]) {
					System.out.println(dp[1]);	
					System.out.println(dp[2]);
					return;	// 메서드 종료
				}
			}
			
			result++;	// 초기 떡 개수 증가
		}
	}

}
