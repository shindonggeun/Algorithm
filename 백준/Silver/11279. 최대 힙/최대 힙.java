import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());	// 우선순위 큐 이용 (높은 숫자순으로 우선순위 결정)
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			int command = Integer.parseInt(br.readLine());
			if(command == 0) {
				if(pq.size() == 0) {
					sb.append(0).append("\n");
				}
				else {
					sb.append(pq.poll()).append("\n");
				}
			}
			else {
				pq.add(command);
			}
		}
		System.out.print(sb);

	}

}
