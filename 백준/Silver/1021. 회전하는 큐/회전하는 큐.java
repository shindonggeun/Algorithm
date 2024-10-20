import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 큐의 크기
	static int M; // 뽑아내려고 하는 수의 개수
	static Deque<Integer> deque;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		deque = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
			deque.add(i);
		}
		
		List<Integer> targetList = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			targetList.add(num);
		}
		
		int totalMoveCount = 0;
		
		for (int target: targetList) {
			int idx = 0;
			
			for (int num: deque) {
				if (num == target) {
					break;
				}
				idx++;
			}
			
			int leftMoveCount = idx;
			int rightMoveCount = deque.size() - idx;
			
			if (leftMoveCount <= rightMoveCount) {
				for (int i=0; i<leftMoveCount; i++) {
					int leftNum = deque.pollFirst();
					deque.addLast(leftNum);
				}
				totalMoveCount += leftMoveCount;
			}
			else {
				for (int i=0; i<rightMoveCount; i++) {
					int rightNum = deque.pollLast();
					deque.addFirst(rightNum);
				}
				totalMoveCount += rightMoveCount;
			}
			
			deque.pollFirst();
		}
		
		System.out.println(totalMoveCount);

	}

}