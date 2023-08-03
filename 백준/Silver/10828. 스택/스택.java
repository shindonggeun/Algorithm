import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			String[] inputSplit = input.split(" ");
			
			// "push" 명령어 입력된 경우
			if(inputSplit[0].equals("push")) {
				// 스택에 값 저장
				stack.push(Integer.parseInt(inputSplit[1]));
			}
			// "pop" 명령어 입력된 경우
			else if(inputSplit[0].equals("pop")) {
				// 스택이 비어있으면 -1 출력할 수 있게끔
				if(stack.isEmpty()) {
					sb.append(-1).append("\n");
				}
				// 스택이 비어있지 않으면 스택의 최상단 값 출력할 수 있게끔
				else {
					sb.append(stack.pop()).append("\n");
				}
			}
			// "size" 명령어 입력된 경우
			else if(inputSplit[0].equals("size")) {
				sb.append(stack.size()).append("\n");
			}
			// "empty" 명령어 입력된 경우
			else if(inputSplit[0].equals("empty")) {
				// 스택이 비어있는 경우 1 출력할 수 있게끔
				if(stack.isEmpty()) {
					sb.append(1).append("\n");
				}
				// 스택이 비어있지 않은 경우 0 출력할 수 있게끔
				else {
					sb.append(0).append("\n");
				}
			}
			// "top" 명령어 입력된 경우
			else if(inputSplit[0].equals("top")) {
				// 스택이 비어있는 경우
				if(stack.isEmpty()) {
					sb.append(-1).append("\n");
				}
				// 스택이 비어있지 않은 경우
				else {
					// 스택의 최상단에 있는 정수 출력할 수 있게끔
					sb.append(stack.peek()).append("\n");
				}
			}
		}
		System.out.print(sb);
		

	}

}
