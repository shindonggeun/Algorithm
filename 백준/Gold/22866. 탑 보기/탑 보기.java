import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int L;
	static int[] heightArr;
	static int[] countArr;
	static int[] closedArr;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		heightArr = new int[N+1];		
		countArr = new int[N+1];
		closedArr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			heightArr[i] = Integer.parseInt(st.nextToken());
			closedArr[i] = -1;
		}
		
		
		stack = new Stack<>();
		for (int i=1; i<=N; i++) {
			while (!stack.isEmpty() && heightArr[stack.peek()] <= heightArr[i]) {
				stack.pop();
			}
			
			countArr[i] = stack.size();
			
			if (countArr[i] > 0) {
				closedArr[i] = stack.peek();
			}
			
			stack.push(i); // 스택에 현재 건물의 인덱스 저장
		}
		
		
		stack = new Stack<>();
		for (int i=N; i>=1; i--) {
			while (!stack.isEmpty() && heightArr[stack.peek()] <= heightArr[i]) {
				stack.pop();
			}
			
			countArr[i] += stack.size();
			
			if (!stack.isEmpty()) {
				if (closedArr[i] == -1 || (stack.peek() - i < i - closedArr[i])) {
					closedArr[i] = stack.peek();
				}
			}
			
			stack.push(i);
		}
		
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=1; i<=N; i++) {
			sb.append(countArr[i]);
			
			if (countArr[i] > 0) {
				sb.append(" ").append(closedArr[i]);
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

}
