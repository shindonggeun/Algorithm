import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 누적합, 투포인터 알고리즘 이용
		int[] holeSumArr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			holeSumArr[i] = holeSumArr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int maxVolume = Integer.MIN_VALUE;
		int startIdx = 0;
		int endIdx = 0;
		
		while(endIdx <= N) {
			int sum = holeSumArr[endIdx] - holeSumArr[startIdx];
			if(sum <= M) {
				maxVolume = Math.max(maxVolume, sum);
				endIdx++;
			}
			else {
				startIdx++;
			}
			
		}
		System.out.println(maxVolume);
		

	}

}
