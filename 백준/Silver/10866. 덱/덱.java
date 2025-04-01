import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static Deque<Integer> deque;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		deque = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<N; i++) {
			String[] commands = br.readLine().split(" ");
			
			if (commands[0].equals("push_front")) {
				deque.addFirst(Integer.parseInt(commands[1]));
			} 
			else if (commands[0].equals("push_back")) {
				deque.addLast(Integer.parseInt(commands[1]));
			} 
			else if (commands[0].equals("pop_front")) {
				sb.append(deque.isEmpty() ? -1 : deque.pollFirst()).append("\n");
			}
			else if (commands[0].equals("pop_back")) {
				sb.append(deque.isEmpty() ? -1 : deque.pollLast()).append("\n");
			}
			else if (commands[0].equals("size")) {
				sb.append(deque.size()).append("\n");
			}
			else if (commands[0].equals("empty")) {
				sb.append(deque.isEmpty() ? 1 : 0).append("\n");
			}
			else if (commands[0].equals("front")) {
				sb.append(deque.isEmpty() ? -1 : deque.peekFirst()).append("\n");
			}
			else if (commands[0].equals("back")) {
				sb.append(deque.isEmpty() ? -1 : deque.peekLast()).append("\n");
			}
		}
		
		System.out.print(sb);

	}
	

}