import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 명령의 개수
	static Stack<Integer> stack;	// 스택 선언
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		stack = new Stack<>();	// 스택 생성
		StringBuilder sb = new StringBuilder();	// 명령의 결과값들을 저장할 StringBuilder 선언 및 생성
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			
			// 명령이 1인 경우 스택에 정수 집어넣음
			if (command == 1) {
				int num = Integer.parseInt(st.nextToken());
				stack.push(num);
			}
			// 명령이 2인 경우 스택의 맨 위의 저장된 정수 빼고 출력, 비어있으면 -1 출력
			else if (command == 2) {
				if (stack.isEmpty()) {
					sb.append(-1).append("\n");
				}
				else {
					int output = stack.pop();
					sb.append(output).append("\n");
				}
			}
			// 명령이 3인 경우 스택에 들어있는 정수의 개수 출력
			else if (command == 3) {
				sb.append(stack.size()).append("\n");
			}
			// 명령이 4인 경우 스택이 비어 있으면 1, 아닌 경우 0 출력
			else if (command == 4) {
				if (stack.isEmpty()) {
					sb.append(1).append("\n");
				}
				else {
					sb.append(0).append("\n");
				}
			}
			// 명령이 5인 경우 스택에 저장된 맨 위의 정수 출력, 비어있는 경우 -1 출력 
			else if (command == 5) {
				if (stack.isEmpty()) {
					sb.append(-1).append("\n");
				}
				else {
					sb.append(stack.peek()).append("\n");
				}
			}
		}
		
		System.out.print(sb);

	}

}