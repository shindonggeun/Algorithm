import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int[] dp = new int[21];	// dp배열 이용해서 피보나치 수 저장하기
		dp[0] = 0;
		dp[1] = 1;
		// 0 또는 1이 입력된 경우
		if(num == 0 || num == 1) {
			System.out.println(num);
		}
		else {
			// 피보나치수 저장
			for(int i=2; i<=20; i++) {
				dp[i] = dp[i-1] + dp[i-2];
			}
			System.out.println(dp[num]);
		}

	}
	

}
