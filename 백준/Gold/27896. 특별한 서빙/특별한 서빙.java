import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());	// 높은값이 우선순위 높음(최대힙)
		int sum = 0;
		int count = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			pq.add(num);
			sum += num;
			
			// 불만도가 M보다 크거나 같아지는 경우 반복문 수행
			while(sum >= M) {
				sum -= pq.poll() * 2;	// 가지 저장한 값들 중 가장 최대값의 2배한 값 빼주기 (위에서 더했으므로)
				count++;
			}
		}
		System.out.println(count);

	}

}
