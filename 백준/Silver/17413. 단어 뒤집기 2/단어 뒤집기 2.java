import java.util.*;
import java.io.*;

public class Main {

	static String S;
	static Stack<Character> stack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		
		stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<S.length(); i++) {
			char ch = S.charAt(i);
			
			if (ch == '<') {
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				
				while (ch != '>') {
					sb.append(ch);
					i++;
					ch = S.charAt(i);
				}
				sb.append(ch);
			}
			else if (ch == ' ') {
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(ch);
			}
			else {
				stack.push(ch);
			}
		}
		
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.print(sb);

	}

}