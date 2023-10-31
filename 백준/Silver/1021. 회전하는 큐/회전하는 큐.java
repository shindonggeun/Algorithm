import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int resultCount = 0;
		
		Deque<Integer> deque = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) {
			deque.add(i);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int targetNum = Integer.parseInt(st.nextToken());
			
			while(true) {
				int idx = 0;
				for(int value: deque) {
					if(value == targetNum) {
						break;
					}
					idx++;
				}
				
				// 앞쪽으로의 이동이 뒷쪽으로의 이동보다 적거나 같은경우
				if(idx <= deque.size() / 2) {
					for(int j=0; j<idx; j++) {
						deque.addLast(deque.pollFirst());
						resultCount++;
					}
				}
				else {
					for(int j=0; j<deque.size() - idx; j++) {
						deque.addFirst(deque.pollLast());
						resultCount++;
					}
				}
				
				if(deque.peekFirst() == targetNum) {
					deque.pollFirst();
					break;
				}
			}
		
		}
		System.out.println(resultCount);

	}

}
