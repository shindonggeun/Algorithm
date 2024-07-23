import java.util.*;
import java.io.*;

public class Main {
	
	static Stack<Character> stack; // 괄호들을 저장할 스택

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		stack = new Stack<>(); // 스택 초기화
		int totalPipeCount = 0; // 잘려진 쇠막대기 조각의 총 개수
		
		// 입력값 한 문자씩 탐색
		for (int i=0; i<input.length(); i++) {
			char ch = input.charAt(i); // 입력값의 해당 괄호 추출
			
			// 해당 괄호가 여는 괄호인 경우
			if (ch == '(') {
				stack.push(ch); // 스택에 집어넣음
			}
			// 해당 괄호가 닫는 괄호인 경우
			else {
				// 스택이 비어있지 않은 경우
				if (!stack.isEmpty()) {
					stack.pop(); // 스택의 최상단에 저장된 값 뽑아냄
					// 입력값에서 전 글자(문자)가 여는 괄호인 경우 (즉, 레이저인 경우 -> "()")
					if (input.charAt(i-1) == '(') {
						totalPipeCount += stack.size(); // 스택의 사이즈를 잘려진 쇠막대기 조각의 총 개수에 누적
					}
					// 입력값에서 전 글자(문자)가 여는 괄호가 아닌 경우 (즉, 레이저가 아닌 경우)
					else {
						totalPipeCount += 1; // 쇠막대기 조각의 총 개수 증가 (단순히 쇠막대기가 하나 더 들어온거임)
					}
				}
			}
		}
		
		System.out.println(totalPipeCount);

	}

}