import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();	// 입력값 저장
		
		// 입력값에서 "1"을 기준으로 토큰을 끊어줌 (연속된 0의 문자열 토큰들을 저장해줌) 
		StringTokenizer zeroToken = new StringTokenizer(input, "1");	
		
		// 입력값에서 "0"을 기준으로 토큰을 끊어줌(연속된 1의 문자열 토큰들을 저장해줌)
		StringTokenizer oneToken = new StringTokenizer(input, "0");		

		// 연속된 0과 1의 토큰들 중 가장 작은 토큰 개수 출력
		System.out.println(Math.min(zeroToken.countTokens(), oneToken.countTokens()));

	}

}