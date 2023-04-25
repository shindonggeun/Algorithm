import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String input = st.nextToken();
		String explosion = br.readLine();
		
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<input.length(); i++) {
			char ch = input.charAt(i);
			stack.push(ch);
			
			// 스택에 담겨진 문자들이 폭발 문자열과 길이가 같아지거나 큰 경우 검사해주기
			if(stack.size() >= explosion.length()) {
				boolean isSame = true;	// 폭발 문자열인지 판단해주는 변수
				// 폭발 문자열 검사
				for(int idx=0; idx<explosion.length(); idx++) {
					char ch1 = stack.get(stack.size() - explosion.length() + idx);
					char ch2 = explosion.charAt(idx);
					// 문자열 하나씩 검사해서 다르면
					if(ch1 != ch2) {
						isSame = false;	// 폭발문자열 아님
						break;			// 폭발문자열 검사하는 반복문 빠져나옴
					}
				}
				// 폭발 문자열인게 확인 됐으면
				if(isSame) {
					// 폭발문자열의 글자수만큼 스택에서 빼내주기
					for(int cnt=0; cnt<explosion.length(); cnt++) {
						stack.pop();
					}
				}
			}
		}
		
		// 문자열 다 폭발시켜서 남는 글자가 없는 경우
		if(stack.size() == 0) {
			System.out.println("FRULA");
		}
		else {
			for(char ch: stack) {
				sb.append(ch);
			}
			System.out.println(sb);
		}
		

	}

}