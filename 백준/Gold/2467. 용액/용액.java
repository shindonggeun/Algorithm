import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long[] numArr = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			long num = Long.parseLong(st.nextToken());
			numArr[i] = num;
		}
		
		// 투포인터 알고리즘 이용
		int left = 0;
		int right = N-1;
		int minLeft = 0;
		int minRight = 0;
		long min = Long.MAX_VALUE;
		
		while(left < right) {
			long sum = numArr[left] + numArr[right];
			// 최소값 갱신
			if(min > Math.abs(sum)) {
				min = Math.abs(sum);
				minLeft = left;
				minRight = right;
			}
			
			if(sum>=0) {
				right--;
			}
			else {
				left++;
			}
		}
		System.out.println(numArr[minLeft] + " " + numArr[minRight]);

	}

}
