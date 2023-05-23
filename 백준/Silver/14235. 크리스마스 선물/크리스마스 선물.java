import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());	// 값이 높은것이 우선순위 높음
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			if(num == 0) {
				// 아이들에게 나눠줄 선물이 없는 경우 -1 출력
				if(pq.size() == 0) {
					sb.append(-1).append("\n");
				}
				// 아이들에게 나눠줄 선물이 있는 경우 가장 가치가 큰 선물(우선순위 높은)을 준다
				else {
					sb.append(pq.poll()).append("\n");
				}
			}
			else {
				for(int j=0; j<num; j++) {
					pq.add(Integer.parseInt(st.nextToken()));
				}
			}
		}
		System.out.print(sb);
	}

}
