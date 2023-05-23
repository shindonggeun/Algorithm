import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	// 카드 개수
		int m = Integer.parseInt(st.nextToken());	// 카드 합체 개수
		
		PriorityQueue<Long> pq = new PriorityQueue<>();	// 값이 낮은 것이 우선순위 높음
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			pq.add(Long.parseLong(st.nextToken()));
		}
		
		for(int i=0; i<m; i++) {
			// 가장 작은 점수를 계산해야 하므로 값이 낮은 값 2개를 우선순위 큐에서 뽑는다
			// n(2 <= n <= 1,000), m(0 <= m <= 15×n) 이면서 카드범위가 자연수 (1 <= ai <= 1,000,000) 이므로 
			// int형으로 선언하면 오버플로우 날 수 있다
			long num1 = pq.poll();
			long num2 = pq.poll();
			
			// 뽑은 값들을 더한 뒤 다시 갱신해준다
			long sum = num1 + num2;	
			pq.add(sum);
			pq.add(sum);
		}
		
		long result = 0;
		while(!pq.isEmpty()) {
			result += pq.poll();
		}
		System.out.println(result);
	}

}
