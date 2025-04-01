import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 명령의 수
	static Deque<Integer> deque; // 자료구조 덱 선언

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		deque = new ArrayDeque<>(); // ArrayDeque 생성
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<N; i++) {
			String[] commands = br.readLine().split(" "); // 명령어 입력 받아서 공백 단위로 쪼개기
			
			if (commands[0].equals("push_front")) {
				// 맨 앞에 해당 정수 집어넣기
				deque.addFirst(Integer.parseInt(commands[1]));
			} 
			else if (commands[0].equals("push_back")) {
				// 맨 마지막에 해당 정수 집어넣기
				deque.addLast(Integer.parseInt(commands[1]));
			} 
			else if (commands[0].equals("pop_front")) {
				// 덱이 비어있는 경우 -1 아닌 경우 가장 앞에 있는 정수 뺀 뒤 출력
				sb.append(deque.isEmpty() ? -1 : deque.pollFirst()).append("\n");
			}
			else if (commands[0].equals("pop_back")) {
				// 덱이 비어있는 경우 -1 아닌 경우 가장 맨 뒤에 있는 정수 뺀 뒤 출력
				sb.append(deque.isEmpty() ? -1 : deque.pollLast()).append("\n");
			}
			else if (commands[0].equals("size")) {
				// 덱의 들어있는 정수 개수 반환
				sb.append(deque.size()).append("\n");
			}
			else if (commands[0].equals("empty")) {
				// 덱이 비어있는 경우 1 아닌 경우 0
				sb.append(deque.isEmpty() ? 1 : 0).append("\n");
			}
			else if (commands[0].equals("front")) {
				// 덱이 비어있는 경우 -1 아닌 경우 가장 앞에 있는 정수 출력
				sb.append(deque.isEmpty() ? -1 : deque.peekFirst()).append("\n");
			}
			else if (commands[0].equals("back")) {
				// 덱이 비어있는 경우 -1 아닌 경우 가장 맨 뒤에 있는 정수 출력
				sb.append(deque.isEmpty() ? -1 : deque.peekLast()).append("\n");
			}
		}
		
		System.out.print(sb);

	}
	

}