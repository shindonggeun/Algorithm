import java.util.*;
import java.io.*;

public class Main {
	
	static Stack<Character> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		
		StringBuilder sb = new StringBuilder();
		
		while (!(input = br.readLine()).equals(".")) {
			char[] inputToCharArr = input.toCharArray();
			
			stack = new Stack<>();
			boolean balance = true;
			
			for (char ch: inputToCharArr) {
				if (ch == '(' || ch == '[') {
					stack.push(ch);
				}
				else if (ch == ')') {
					if (stack.isEmpty() || stack.peek() != '(') {
						balance = false;
						break;
					}
					stack.pop();
				}
				else if (ch == ']') {
					if (stack.isEmpty() || stack.peek() != '[') {
						balance = false;
						break;
					}
					stack.pop();
				}
			}
			
			if (!balance ) {
				sb.append("no");
			}
			else {
				if (!stack.isEmpty()) {
					sb.append("no");
				}
				else {
					sb.append("yes");
				}
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

}