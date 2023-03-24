import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		
		while(queue.size() != 1) {
			// 1. 맨위에 있는 카드 버림
			queue.remove();	// 큐에서 첫번째 값 제거
		
			// 2. 그다음 제일 위의 있는 카드를 제일 아래에 있는 카드 밑으로 감
			int card = queue.poll();	// 큐에서 첫번째 값 반환
			queue.add(card);	// 다시 큐에 집어넣음
		}
		System.out.println(queue.poll());	// 남게 되는 카드 번호 출력

	}

}