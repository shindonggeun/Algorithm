import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String input = st.nextToken();
		Stack<Character> stack = new Stack<>();
		
		// 입력받은 문자열에서 한글자씩 탐색
		for(int i=0; i<input.length(); i++) {
			char ch = input.charAt(i);
			
			if(ch == 'P') {
				stack.push(ch);
			}
			else {
				// 해당 조건 만족하는 경우 스택에 저장되어 있는 최상단 글자 두개 빼냄
				if((stack.size() >= 2) && (i < input.length() - 1) && (input.charAt(i+1) == 'P')) {
					stack.pop();
					stack.pop();
				}
				else {
					System.out.println("NP");
					return;
				}
			}
		}
		
		if(stack.size() == 1) {
			System.out.println("PPAP");
		}
		else {
			System.out.println("NP");
		}

	}

}
