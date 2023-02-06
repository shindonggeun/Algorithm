import java.util.*;
import java.io.*;

public class Main {
	
	static Deque<Integer> deque;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			String arr = st.nextToken();
			arr = arr.substring(1, arr.length() - 1);
			String[] num = arr.split(",");
			
			deque = new ArrayDeque<>();
			
			for(int j=0; j<n; j++) {
				deque.add(Integer.parseInt(num[j]));
			}
			
			AC(command, deque);
		}
		
		System.out.println(sb);

	}
	
	public static void AC(String command, Deque<Integer> deque) {
		boolean isRight = true;
		
		for(int i=0; i<command.length(); i++) {
			if(command.charAt(i) == 'R') {
				isRight = !isRight;
				continue;
			}
			
			// 정방향인 경우
			if(isRight) {
				if(deque.pollFirst() == null) {
					sb.append("error").append("\n");
					return;
				}
			}
			// 반대방향인경우
			else {
				if(deque.pollLast() == null) {
					sb.append("error").append("\n");
					return;
				}
			}
		}
		
		makeArray(isRight);
	}
	
	public static void makeArray(boolean isRight) {
		sb.append("[");
		
		if(deque.size() > 0) {
			// 정방햐인 경우
			if(isRight) {
				sb.append(deque.pollFirst());
				
				// 덱의 원소 없어질때까지 반복
				while(!deque.isEmpty()) {
					sb.append(",").append(deque.pollFirst());
				}
			}
			// 역방향인 경우
			else {
				sb.append(deque.pollLast());
				
				while(!deque.isEmpty()) {
					sb.append(",").append(deque.pollLast());
				}
			}
		}
		
		sb.append("]").append("\n");
	}

}