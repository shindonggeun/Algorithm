import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	// 입력값 토큰에 저장 (공백단위로 끊어서)
		String strX = st.nextToken();	// 문자열 X 저장
		String strY = st.nextToken();	// 문자열 Y 저장
		
		int result = strX.length();		// 두 문자열의 차이를 저장해주는 값
		
		// 문제에서 문자열 X의 길이는 Y보다 작거나 같음
		// 
		for(int i=0; i<=strY.length() - strX.length(); i++) {
			int count = 0;
			// 브루트포스 알고리즘 이용 (모든 경우 탐색)
			for(int j=0; j<strX.length(); j++) {
				// 두 문자열을 한글자씩 비교하였을때 차이가 있는 경우 count 증가
				if(strX.charAt(j) != strY.charAt(i+j)) {
					count++;
				}
			}
			result = Math.min(result, count);	// 문자열 계속 비교해나가면서 두 문자열의 차이 최소값 저장하게끔
		}
		System.out.println(result);

	}

}