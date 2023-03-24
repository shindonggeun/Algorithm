import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String input = st.nextToken();
		Stack<Character> stack = new Stack<>();
		
		// 문자열(괄호열) 한글자씩 탐색
		for(int i=0; i<input.length(); i++) {
			// 스택이 비어있지 않은 경우
			if(!stack.empty()) {
				// 스택의 최상단 값이 '('이면서 괄호열에서 ')'가 나온경우 (즉 올바른 괄호열인 경우)
				if(stack.peek() == '(' && input.charAt(i) == ')') {
					stack.pop();	// 스택에서 최상단값 빼냄
					continue;		// 다시 반복문으로 올라가기
				}
			}
			
			stack.push(input.charAt(i));	// 스택에 한글자씪 집어넣음
		}
		System.out.println(stack.size());	

	}

}
