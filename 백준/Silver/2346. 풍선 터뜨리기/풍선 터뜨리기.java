import java.util.*;
import java.io.*;

public class Main {
	
	static class Ballon {
		int moveNum;
		int idx;
		
		public Ballon(int moveNum, int idx) {
			this.moveNum = moveNum;
			this.idx = idx;
		}
	}
	
	static int N;
	static Deque<Ballon> deque;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		deque = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			int num = Integer.parseInt(st.nextToken());
			deque.add(new Ballon(num, i));
		}
		
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			Ballon now = deque.pollFirst();
			sb.append(now.idx).append(" ");
			
			if (deque.isEmpty()) {
				break;
			}
			
			int move = now.moveNum;
			
			if (move > 0) {
				move--;
				
				for (int i=0; i<move; i++) {
					deque.addLast(deque.pollFirst());
				}
			}
			else {
				for (int i=0; i<Math.abs(move); i++) {
					deque.addFirst(deque.pollLast());
				}
			}
		}
		
		System.out.println(sb);
		
	}

}