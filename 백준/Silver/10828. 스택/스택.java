import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 명령어 개수
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), "\n");
			String input = st.nextToken();			// 입력값
			String[] command = input.split(" ");	// 입력값 공백단위로 쪼개줌
			
			// 명령어가 push인 경우 -> stack에 담음
			if(command[0].equals("push")) {
				int num = Integer.parseInt(command[1]);
				stack.push(num);
			}
			// 명령어가 top인 경우 
			else if(command[0].equals("top")) {
				// 스택이 비어있지않으면 최상단 값 출력
				if(!stack.empty()) {
					sb.append(stack.peek()).append("\n");
				}
				// 스택에 비어있는 경우 -1 출력
				else {
					sb.append("-1").append("\n");
				}
			}
			// 명령어가 size인 경우 -> 스택의 size 출력
			else if(command[0].equals("size")) {
				sb.append(stack.size()).append("\n");
			}
			// 명령어가 pop인 경우
			else if(command[0].equals("pop")) {
				// 스택이 비어있지 않으면 스택 최상단 값 빼주면서 빼준 값 출력
				if(!stack.empty()) {
					sb.append(stack.pop()).append("\n");
				}
				// 스택 비어있으면 -1 출력
				else {
					sb.append("-1").append("\n");
				}
			}
			// 명령가 empty인 경우
			else if(command[0].equals("empty")) {
				// 스택이 비어있지 않은 경우 0 출력
				if(!stack.empty()) {
					sb.append("0").append("\n");
				}
				// 스택이 비어있는 경우 1 출력
				else {
					sb.append("1").append("\n");
				}
			}
		}
		System.out.print(sb);
		
	}

}