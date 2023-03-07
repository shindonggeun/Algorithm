import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "\n");	// 입력값 한줄입력 다 받게끔
		String input = st.nextToken();	// 입력값 저장
		Stack<Character> stack = new Stack<>();	// 스택이용
		StringBuilder sb = new StringBuilder();
		boolean find = false;	// '<' 만나는지 여부 판단해주는 변수
		
		// 입력값 한글자씩 탐색
		for(int i=0; i<input.length(); i++) {
			char ch = input.charAt(i);
			// '<' 글자인 경우
			if(ch == '<') {
				// 스택에 아무것도 없을때까지 스택에 담긴 글자 뽑아냄 (문자열 거꾸로)
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				find = true;	// '<' 글자 찾았으므로 true
			}
			// '>' 글자 찾은 경우
			else if(ch == '>') {
				find = false;	// 다시 false로
				sb.append(ch);	// 출력해주는 문자열에 > 저장해줌
				continue;		// 반복문으로 다시 
			}
			
			// < 글자가 나왔던 경우
			if(find == true) {
				sb.append(ch);	// 문자열 그대로 출력하게끔 저장해줌
			}
			// < 글자 나오지 않은경우
			else {
				// 공백인 경우
				if(ch == ' ') {
					// 스택에 저장된 글자들 싹다 뽑아냄(문자열 거꾸로)
					while(!stack.isEmpty()) {
						sb.append(stack.pop());
					}
					sb.append(' ');	// 공백도 저장해줌
					continue;		// 반복문으로 다시
				}
				// 일반 글자인 경우
				else {
					stack.push(ch);	// 스택에 저장
				}
			}
			
			// 반복문 변수가 문자열의 인덱스의 맨 마지막인 경우
			if(i == input.length() - 1) {
				// 스택에 저장된 글자들 싹다 뽑아냄(문자열 거꾸로)
				while(!stack.isEmpty()) {
					sb.append(stack.pop());	
				}
			}
		}
		System.out.println(sb);
		
	}

}
