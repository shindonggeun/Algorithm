import java.util.*;
import java.io.*;

public class Main {

	static int N; // 연산의 개수
	static PriorityQueue<Integer> pq; // 우선순위 큐 선언
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		pq = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위 큐 생성 (최대힙 -> 높은 값이 우선순위 높음)
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 입력한 숫자가 0인 경우
			if (num == 0) {
				// 우선순위 큐가 비어있는 경우
				if (pq.isEmpty()) {
					sb.append(0).append("\n"); // 0을 출력하도록
				}
				// 우선순위 큐가 비어있지 않은 경우
				else {
					sb.append(pq.poll()).append("\n"); // 우선순위 큐에 저장된 가장 높은 값 뽑아서 출력하도록
				}
			}
			// 입력한 숫자가 0이 아닌 경우
			else {
				pq.add(num); // 우선순위 큐에 해당 값 저장
			}
		}
		
		System.out.print(sb);

	}

}