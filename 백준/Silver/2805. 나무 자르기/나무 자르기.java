import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] woodArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		woodArr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			woodArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(woodArr);
		
		long start = 0;
		long end = woodArr[N-1];
		long cutMaxHeight = 0;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			
			if (checkWoodCut(mid)) {
				cutMaxHeight = mid;
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		
		System.out.println(cutMaxHeight);

	}
	
	public static boolean checkWoodCut(long cutHeight) {
		long sum = 0;
		
		for (int i=0; i<N; i++) {
			if (woodArr[i] > cutHeight) {
				sum += woodArr[i] - cutHeight;
			}
		}
		
		return sum >= M;
	}

}