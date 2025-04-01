import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 연산의 개수
	static PriorityQueue<Integer> pq; // 우선순위 큐 선언

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		pq = new PriorityQueue<>(); // 우선순위 큐 생성 (최소힙 -> 작은 값이 우선순위 높음)
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if (num == 0) {
				// 입력에서 0인 경우 우선순위 큐가 비어있으면 0 아니면 가장 작은 값 출력
				sb.append(pq.isEmpty() ? 0 : pq.poll()).append("\n");
			}
			else {
				pq.add(num);
			}
		}
		
		System.out.print(sb);
		
		
	}

}