import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<T; tc++) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue<>();	// 낮은값이 우선순위 높음
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; i++) {
				// 파일크기 입력받아서 우선순위큐에 집어넣음
				long fileSize = Long.parseLong(st.nextToken());	
				pq.add(fileSize);
			}
			
			long sum = 0;
			
			while(pq.size() > 1) {
				long a = pq.poll();
				long b = pq.poll();
				
				sum += (a+b);
				pq.add(a+b);
			}
			sb.append(sum).append("\n");
		}
		System.out.print(sb);

	}

}
