import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> queue = new LinkedList<>();	// Queue LinkedList객체로 선언
		// 1부터 N까지의 수 큐에 저장
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		
		while(queue.size() != 1) {
			// 1. 맨 앞에 있는 카드를 버림
			queue.poll();
			// 2. 그다음 수를 맨 뒤로 옮김
			int num = queue.poll();
			queue.add(num);
		}
		// 마지막에 저장된 숫자 출력
		System.out.println(queue.poll());
		

	}

}
