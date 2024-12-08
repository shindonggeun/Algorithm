import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] times;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		times = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			times[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(times);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int resultTime = 0;
		for (int i=times.length-1; i>=0; i--) {
			
			if (pq.size() >= M) {
				resultTime = pq.poll();
			}
			
			pq.add(resultTime + times[i]);
		}
		
		while (!pq.isEmpty()) {
			resultTime = pq.poll();
		}
		
		System.out.println(resultTime);
	}

}