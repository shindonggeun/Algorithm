import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		String pattern = st.nextToken();
		
		// 정규표현식 만들기 
		// replaceAll에서 정규표현식 \\* -> 문자 '*'을 의미
		// ex) ^a -> 문자열에서 a로 시작, . -> 임의의 한문자의 자리수를 뜻하며 문자열이 .뒤에 나오는것으로 끝남
		// * -> 반복여부를 뜻하며 * 앞에 문자가 0번 또는 그이상 반복을 뜻함, a$ -> 문자열에서 a로 끝남
		pattern = "^" + pattern.replaceAll("\\*", ".*") + "$";
		
		//System.out.println(pattern);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			
			// 입력값이 정규표현식으로 표현한 pattern과 일치하는 경우
			if(input.matches(pattern)) {
				sb.append("DA").append("\n");
			}
			// 일치하지 않는 경우
			else {
				sb.append("NE").append("\n");
			}
		}
		System.out.print(sb);

	}

}