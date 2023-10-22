import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();	// 첫번째 문자열 입력
		String str2 = br.readLine();	// 두번째 문자열 입력
		
		int N = str1.length();	// 첫번째 문자열의 길이
		int M = str2.length();	// 두번째 문자열의 길이
		
		int[][] dp = new int[N+1][M+1];	// 공통 부분 문자열 길이를 저장할 dp 배열
		int maxLength = 0;	// 최대 공통 부분 문자열 길이 초기값 세팅
		
		// 두 문자열을 비교하며 최대 공통 부분 문자열 길이를 구하는 과정
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				// 현재 문자가 같은 경우
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;	// 공통 부분 문자열 길이를 1 증가
					maxLength = Math.max(maxLength, dp[i][j]);	// 최대 길이 갱신
				}
			}
		}
		
		System.out.println(maxLength);

	}

}
