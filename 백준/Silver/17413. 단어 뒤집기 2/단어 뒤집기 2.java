import java.util.*;
import java.io.*;

public class Main {

	static String S; // 입력 문자열
	static Stack<Character> stack; // 단어 뒤집어서 출력하기 위해 스택 자료구조 이용
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		
		stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<S.length(); i++) {
			char ch = S.charAt(i);
			
			// 해당 문자가 '<'(열린 태그)인 경우
			if (ch == '<') {
				// 열린 태그 전까지 쌓은 문자 뒤집어서 처리
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				
				// 해당 문자가 '>'(닫힌 태그)가 아닌 경우까지 반복
				while (ch != '>') {
					sb.append(ch);
					i++; // 문자열의 다음 문자 탐색하도록 
					ch = S.charAt(i); // 다음 문자 추출
				}
				sb.append(ch); // '>' 닫힌 태그 출력
			}
			// 해당 문자가 공백인 경우
			else if (ch == ' ') {
				// 단어 뒤집기
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(ch); // 공백 추가 
			}
			// 그 이외의 다른 문자인 경우
			else {
				stack.push(ch); // 스택에 해당 문자 저장
			}
		}
		
		// 마지막 단어가 있을 경우를 대비해 스택 비우기 (단어 뒤집기)
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.println(sb);

	}

}