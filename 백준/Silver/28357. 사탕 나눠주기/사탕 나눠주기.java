import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static long K;
	static long[] scoreArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
		
		scoreArr = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			scoreArr[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(scoreArr);
		
		long start = 0;
		long end = scoreArr[N-1];
		
		long result = 0;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			
			if (candyCheck(mid)) {
				result = mid;
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		
		System.out.println(result);

	}
	
	public static boolean candyCheck(long X) {
		long totalCandyCount = 0;
		
		for (int i=0; i<N; i++) {
			if (scoreArr[i] > X) {
				totalCandyCount += scoreArr[i] - X;
				if (totalCandyCount > K) {
					return false;
				}
			}
		}
		
		// 나눠줘야하는 전체 캔디 수가 K 이하인 경우 true 반환
		// 전체 캔디 수가 K를 넘는 경우 false 반환
		return totalCandyCount <= K;
	}

}