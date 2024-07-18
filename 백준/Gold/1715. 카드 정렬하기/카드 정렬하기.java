import java.util.*;
import java.io.*;

public class Main {

	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i=0; i<N; i++) {
			int cardSize = Integer.parseInt(br.readLine());
			pq.add(cardSize);
		}
		
		int minCount = 0;
		int tempTotal = 0;
		int start = 0;
		
		while (!pq.isEmpty()) {
			tempTotal += pq.poll();
			start++;
			if (start == 2) {
				minCount += tempTotal;
				pq.add(tempTotal);
				tempTotal = 0;
				start = 0;
			}
		}
		
		System.out.println(minCount);

	}

}