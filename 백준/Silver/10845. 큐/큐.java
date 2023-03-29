import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Deque<String> deque = new LinkedList<>();	// 양방향 (스택과 큐 특성 둘다 가지고 있음)
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), "\n");
			String input = st.nextToken();
			String[] command = input.split(" ");
			
			// push 명령어 -> 정수 X를 큐에 넣음
			if(command[0].equals("push")) {
				deque.add(command[1]);	// addLas()도 가능
			}
			// pop 명령어 -> 큐에서 가장 앞에있는 정수를 뺴고, 그 수를 출력, 큐에 들어있는 정수 없는 경우 -1 출력 
			else if(command[0].equals("pop")) {
				if(!deque.isEmpty()) {
					sb.append(deque.removeFirst()).append("\n");
				}
				else {
					sb.append("-1").append("\n");
				}
			}
			// size 명령어 -> 큐에 들어있는 정수의 개수 반환
			else if(command[0].equals("size")) {
				sb.append(deque.size()).append("\n");
			}
			// empty 명령어 -> 큐가 비어있으면 1, 아니면 0
			else if(command[0].equals("empty")) {
				if(deque.isEmpty()) {
					sb.append("1").append("\n");
				}
				else {
					sb.append("0").append("\n");
				}
			}
			// front 명령어 -> 큐의 가장 앞에 있는 정수 출력, 큐에 들어있는 정수 없는 경우 -1 출력
			else if(command[0].equals("front")) {
				if(!deque.isEmpty()) {
					sb.append(deque.peekFirst()).append("\n");
				}
				else {
					sb.append("-1").append("\n");
				}
			}
			// back 명령어 -> 큐에 가장 뒤에 있는 정수 출력, 큐에 들어있는 정수 없는 경우 -1 출력
			else if(command[0].equals("back")) {
				if(!deque.isEmpty()) {
					sb.append(deque.peekLast()).append("\n");
				}
				else {
					sb.append("-1").append("\n");
				}
			}
		}
		System.out.print(sb);

	}

}