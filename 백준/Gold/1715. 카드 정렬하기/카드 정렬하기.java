import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Long> pq = new PriorityQueue<>();	// 우선순위 큐 이용 (낮은 값이 우선순위 높은 것)
		
		for(int i=0; i<N; i++) {
			long num = Long.parseLong(br.readLine());
			pq.add(num);
		}
		
		long sum = 0;
		// 우선순위 큐에 2개이상 들어있어야 비교 가능하므로 2개이상 일떄만 반복문 돌리기
		while(pq.size() > 1) {
			long temp1 = pq.poll();
			long temp2 = pq.poll();
			long num = temp1 + temp2;
			
			sum += num;
			pq.add(num);
		}
		System.out.println(sum);
	}

}
