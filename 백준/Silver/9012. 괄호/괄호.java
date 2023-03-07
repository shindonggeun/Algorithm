import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());	// 테스트 케이스 수 입력
		Stack<Character> stack = new Stack<>();		// 괄호 담을 스택
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine(), "\n");	// 한줄 입력받기
			String input = st.nextToken();					// 입력값
			boolean check = false;							// 체크 변수
			
			// 괄호 입력값 한글자씩 탐색
			for(int j=0; j<input.length(); j++) {
				char ch = input.charAt(j);
				
				// '(' 입력값이 나온 경우
				if(ch == '(') {
					stack.push(ch);	// 스택에 담음
				}
				// ')' 입력값이 나온 경우
				else if(ch == ')') {
					// 스택이 비어있지 않으면
					if(!stack.empty()) {
						stack.pop();	// 스택에 담긴 최상단값 빼줌 -> '(' 괄호랑 짝 맞춰주게끔
					}
					// 스택이 비어있으면
					else {
						check = true;	// 입력값이 잘못된 괄호로 묶인거임 (체크)
						break;			// 입력값 탐색 종료하게끔 반복문 빠져나옴
					}
				}
				
			}
			
			// check 변수가 true인 경우 -> 잘못된 괄호 형식이면 "NO"
			if(check) {
				sb.append("NO").append("\n");
			}
			// check 변수가 false인 경우
			else if(!check) {
				// 스택이 비어있지 않으면 -> 잘못된 괄호 형식으로 묶여있는거임
				if(!stack.empty()) {
					sb.append("NO").append("\n");
				}
				// 스택이 비어있으면 -> 제대로 된 괄호형식으로 묶인거임
				else {
					sb.append("YES").append("\n");
				}
			}
			stack.clear();	// 스택 담긴거 초기화해줌(비워줌)
		}
		System.out.print(sb);

	}

}
