import java.util.*;
import java.io.*;

public class Main {

	static long N;
	static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Long.parseLong(br.readLine());
		K = Integer.parseInt(br.readLine());

		long start = 1;
		long end = N * N;
		long result = 0;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			long count = 0;
			
			for (int i=1; i<=N; i++) {
				count += Math.min(mid / i, N);
			}
			
			if (count < K) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
				result = mid;
			}
		}
		
		System.out.println(result);
	}

}