import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		String str = "";
		
		// 입력값이 온점 하나 -> "." 가 나오면 입력 종료 
		while(!(str = br.readLine()).equals(".")) {
			String input = str;	// 입력값 저장
			boolean check = false;	// 체크변수
			
			// 입려값 글자 하나씩 탐색
			for(int i=0; i<input.length(); i++) {
				char ch = input.charAt(i);
				// '[' 글자가 나온 경우
				if(ch == '[') {
					stack.push(ch);	// 스택에 저장
				}
				// ']' 글자가 나온 경우
				else if(ch == ']') {
					// 스택이 비어있지 않으면
					if(!stack.empty()) {
						char equals = stack.pop();	// 스택의 최상단 값 뽑아서 저장
						// 만약 스택에서 뽑은 값이 '['가 아닌경우 (괄호 짝이 안맞음)
						if(equals != '[') {
							check = true;	// 괄호짝이 안맞은거 찾았음
							break;			// 글자탐색 종료
						}
					}
					// 스택이 비어있으면
					else {
						check = true;		// 괄호짝 안맞음
						break;				// 글자탐색 종료
					}
				}
				// '(' 글자가 나온 경우
				else if(ch == '(') {
					stack.push(ch);	// 스택에 저장
				}
				// ')' 글자가 나온 경우
				else if(ch == ')') {
					// 스택이 비어있지 않으면
					if(!stack.empty()) {
						char equals = stack.pop();	// 스택의 최상단 값 뽑아서 저장
						// 만약 스택에서 뽑은 값이 '('가 아닌경우 (괄호 짝이 안맞음)
						if(equals != '(') {
							check = true;	// 괄호짝이 안맞은거 찾았음
							break;			// 글자탐색 종료
						}
					}
					// 스택이 비어있으면
					else {
						check = true;		// 괄호짝 안맞음
						break;				// 글자탐색 종료
					}
				}
			}
			
			// 입력값에서 괄호짝이 안맞는 경우
			if(check) {
				sb.append("no").append("\n");
			}
			else {
				if(!stack.empty()) {
					sb.append("no").append("\n");
				}
				// 입력값에서 괄호짝이 맞는 경우
				else {
					sb.append("yes").append("\n");
				}
			}
			stack.clear();	// 스택 초기화해줌(클리어)
		}
		System.out.print(sb);

	}

}