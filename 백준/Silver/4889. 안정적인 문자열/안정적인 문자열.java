import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		StringBuilder sb = new StringBuilder();
		int start = 1;
		
		// 입력값에서 "-"가 포함되는 경우 입력 종료
		while(!(str = br.readLine()).contains("-")) {
			Stack<Character> stack = new Stack<>();		// 괄호를 담을 스택 -> '{'만 담는다 
			int count = 0;	// 변경횟수 
			// 입력값 글자 탐색
			for(int i=0; i<str.length(); i++) {
				// 입력값에서 문자가 '{'인 경우 스택에 담음
				if(str.charAt(i) == '{') {
					stack.push(str.charAt(i));
				}
				// 입력값에서 문자가 '}'인 경우
				else if(str.charAt(i) == '}'){
					// 스택이 비어있으면
					if(stack.empty()) {
						stack.push('{');	// 스택에 '{'로 바꿔서 담음
						count++;			// 변경횟수 증가
					}
					// 스택이 비어있으면 스택에서 최상단 값 빼냄
					else {
						stack.pop();
					}
				}
			}
			
			// 변경횟수에서 stack의 사이즈 / 2 를 더해주는 이유
			// 스택에는 '{'문자만 들어가있다. '}'가 오면, pop하기 때문임
			// 만약 스택에 ['{', '{']가 들어있다면, 2번째 하나만 '}'로 변경하면 되므로 /2를 해주는 것이다
			// 그리고 입력값 항상 짝수개이므로
			count = count + stack.size() / 2;
			sb.append(start + ". " + count).append("\n");
			start++;
		}
		System.out.println(sb);

	}

}
