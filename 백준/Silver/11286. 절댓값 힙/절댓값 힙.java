import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> {
			int absX = Math.abs(x);
			int absY = Math.abs(y);
			
			if(absX == absY) {
				// y가 x보다 크거나 같은 경우 
				if(x <= y) {
					return -1;	// x가 높은 우선순위를 가짐
				}
				// x가 y보다 더 큰 경우 
				else {
					return 1;		// y가 높은 우선순위를 가짐
				}
			}
			// x가 y보다 작은 절대값 수인 경우
			else if(absX < absY) {
				return -1;	// x가 높은 우선순위를 가짐
			}
			// x가 y보다 큰 절대값 수인 경우
			else {
				return 1;	// y가 높은 우선순위를 가짐
			}
		});
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
