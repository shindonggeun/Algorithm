import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		String[] numberWord = {"zero", "one", "two", "three", "four", "five", "six",
				"seven", "eight", "nine"};
		List<String> list = new ArrayList<>();
		
		for(int i=M; i<=N; i++) {
			String num = Integer.toString(i);
			String result = "";
			for(int j=0; j<num.length(); j++) {
				int idx = num.charAt(j) - '0';
				result+=numberWord[idx];
				result+=" ";
			}
			result = result.trim();
			list.add(result);
		}
		
		Collections.sort(list);	// 숫자 한자리씩 끊어서 영단어로 읽은것들 저장한 list 사전순으로 정렬
		StringBuilder sb = new StringBuilder();
		int count = 0;
		
		for(String s: list) {
			String answer = "";
			 
			String[] s_split = s.split(" ");
			for(String re: s_split) {
				// String배열에서 찾으려는 문자열에 해당하는 인덱스를 찾을 때 사용한다
				// 숫자랑 영단어 짝 지어준거 찾아서 문자열로 저장
				// ex) "zero" -> "0", "one" -> "1"
				answer += Arrays.asList(numberWord).indexOf(re);	  
			}
			count++;
			sb.append(answer).append(" ");
			// 한줄에 10개씩 출력하게끔 줄바꿈 추가해주기
			if(count%10 == 0) {
				sb.append("\n");
			}
		}
		System.out.print(sb);
		
	}

}