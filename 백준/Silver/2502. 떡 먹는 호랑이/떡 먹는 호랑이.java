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
			for(int i=result; i<K; i++) {
				dp[2] = i;
				for(int j=3; j<D; j++) {
					dp[j] = dp[j-1] + dp[j-2];
				}
				
				if(dp[D] == dp[D-1] + dp[D-2]) {
					System.out.println(dp[1]);
					System.out.println(dp[2]);
					return;
				}
			}
			
			result++;	// 초기 떡 개수 증가
		}
	}

}
