import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<>();	// 낮은 숮자가 우선순위 높은것
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(pq.size() == 0) {
					sb.append(0).append("\n");
				}
				else {
					sb.append(pq.poll()).append("\n");
				}
			}
			else {
				pq.add(num);
			}
		}
		System.out.print(sb);

	}

}
