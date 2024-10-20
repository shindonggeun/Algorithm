import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static Stack<Integer> stack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		stack = new Stack<>();
		
		StringBuilder sb = new StringBuilder();
		boolean stackCheck = true;
		int start = 0;
		
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if (num > start) {
				for (int j=start+1; j<=num; j++) {
					stack.push(j);
					sb.append("+").append("\n");
				}
				start = num;
			}
			else if (stack.peek() != num) {
				stackCheck = false;
			}
			
			stack.pop();
			sb.append("-").append("\n");
		}
		
		System.out.println(stackCheck == true ? sb : "NO");

	}

}