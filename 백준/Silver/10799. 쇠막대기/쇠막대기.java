import java.util.*;
import java.io.*;

public class Main {
	
	static Stack<Character> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		stack = new Stack<>();
		int totalPipeCount = 0;
		
		for (int i=0; i<input.length(); i++) {
			char ch = input.charAt(i);
			
			if (ch == '(') {
				stack.push(ch);
			}
			else {
				if (!stack.isEmpty()) {
					stack.pop();
					if (input.charAt(i-1) == '(') {
						totalPipeCount += stack.size();
					}
					else {
						totalPipeCount += 1;
					}
				}
			}
		}
		
		System.out.println(totalPipeCount);

	}

}
