import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 입력값이 null이 아닐때까지 반복
		while((str = br.readLine()) != null) {
			st = new StringTokenizer(str);
			String s1 = st.nextToken();	// 입력된 문자열 t
			String s2 = st.nextToken();	// 입력된 문자열 s
			
			int idx = 0;	// 문자열 t의 인덱스
			
			// 입력된 문자열 s를 한글자씩 탐색
			for (int i = 0; i < s2.length(); i++) {
				// 문자열 t와 문자열 s를 각 한글자씩 추출했을 때 문자가 그 문자가 같은경우
				// 즉 문자열s를 각 한글자씩 쪼갰을때 t가 그 부분집합이 되는 경우
				if (s1.charAt(idx) == s2.charAt(i)) {
					idx++;	// 문자열 t의 인덱스 증가
				}
				// 문자열 t의 인덱스가 t의 길이와 같아지는 경우
				if (idx == s1.length())
					break;	// 반복문 빠져나옴
			}
			
			// 위에서 탐색했을 때 문자열 t의 인덱스가 t의 길이와 같은경우는 문자열 t가 문자열 s의 부분집합인 경우이다
			if (idx == s1.length()) {
				sb.append("Yes").append("\n");
			}
			// 문자열 s 한글자씩 다 탐색했는데도 문자열t가 문자열 s의 부분집합이 아닌 경우
			else {
				sb.append("No").append("\n");
			}
				
		}
		System.out.print(sb);
	}
	
}