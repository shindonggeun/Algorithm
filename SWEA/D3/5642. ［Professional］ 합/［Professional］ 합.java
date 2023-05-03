import java.util.*;
import java.io.*;

class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			int max = Integer.MIN_VALUE;
			int sum = 0;
			
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 배열에서 연속된 합(구간합)을 구해야하므로
			for(int i=0; i<N; i++) {
				sum += arr[i];
				if(sum > max) {
					max = sum;
				}
				// 합이 음수가 되면 최대가 될 수 없다
				if(sum < 0) {
					sum = 0;
				}
			}
			
			sb.append("#" + tc + " " + max).append("\n");
		}
		
		System.out.print(sb);
	}
}
