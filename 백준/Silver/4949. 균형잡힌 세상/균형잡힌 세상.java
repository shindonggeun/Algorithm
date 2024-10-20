import java.util.*;
import java.io.*;

public class Main {
	
	static Stack<Character> stack; // 괄호의 균현을 맞추기 위해 사용할 스택

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		
		StringBuilder sb = new StringBuilder();
		
		// 입력값이 "." (온점)일 때까지 반복
		while (!(input = br.readLine()).equals(".")) {
			char[] inputToCharArr = input.toCharArray(); // 입력값 문자 배열로 변환
			
			stack = new Stack<>(); // 스택 생성
			boolean balance = true; // 문자열의 괄호 균형 여부를 판단하는 변수 (true: 균형 잡힘, false: 균형 잡히지 않음)
			
			// 문자열의 모든 문자 순회
			for (char ch: inputToCharArr) {
				// 해당 문자가 '(' 거나 또는 '['인 경우
				if (ch == '(' || ch == '[') {
					stack.push(ch); // 스택에 해당 문자 저장
				}
				// 해당 문자가 ')'인 경우
				else if (ch == ')') {
					// 스택이 비어있거나 또는 스택의 최상단 값이 '('가 아닌 경우
					if (stack.isEmpty() || stack.peek() != '(') {
						balance = false; // 문자열의 괄호 균형 여부가 맞지 않음
						break; // 더 이상 해당 문자열 검사할 필요 없으므로 반복문 빠져나옴
					}
					stack.pop(); // 위의 경우가 아닌 경우는 짝이 맞으므로 스택에서 pop하여 괄호 제거
				}
				// 해당 문자가 ']'인 경우
				else if (ch == ']') {
					// 스택이 비어있거나 또는 스택의 최상단 값이 '['가 아닌 경우
					if (stack.isEmpty() || stack.peek() != '[') {
						balance = false; // 문자열의 괄호 균형 여부가 맞지 않음
						break; // 더 이상 해당 문자열 검사할 필요 없으므로 반복문 빠져나옴
					} 
					stack.pop(); // 위의 경우가 아닌 경우는 짝이 맞으므로 스택에서 pop하여 괄호 제거
				}
			}
			
			// 문자열의 괄호 균형 여부가 맞지 않은 경우
			if (!balance) {
				sb.append("no");
			}
			// 문자열의 괄호 균형 여부가 맞는 경우
			else {
				// 스택이 비어있지 않은 경우 여전히 처리되지 않은 괄호가 있는 것임
				if (!stack.isEmpty()) {
					sb.append("no");
				}
				// 스택이 비어있는 경우 균형이 맞는 것임
				else {
					sb.append("yes");
				}
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

}