import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static long[] timeArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		timeArr = new long[N];
		long maxTime = 0;

		for (int i=0; i<N; i++) {
			timeArr[i] = Long.parseLong(br.readLine());
			maxTime = Math.max(maxTime, timeArr[i]);
		}
		
		
		long start = 0;
		long end = maxTime * M;
		long result = end;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			long sum = 0;
			
			for (int i=0; i<N; i++) {
				sum += mid / timeArr[i];
				if (sum >= M) {
					break;
				}
			}
			
			if (sum >= M) {
				end = mid - 1;
				result = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		System.out.println(result);
	}

}