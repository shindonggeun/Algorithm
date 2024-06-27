import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int S;
	static int[] numArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		numArr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = 0;
		int minLength = Integer.MAX_VALUE;
		int sum = 0;
		
		while (left <= N && right <= N) {
			if (sum >= S) {
				minLength = Math.min(minLength, right - left);
			}
			
			if (sum < S) {
				sum += numArr[right];
				right++;
			}
			else {
				sum -= numArr[left];
				left++;
			}
		}
		
		System.out.println(minLength == Integer.MAX_VALUE ? 0 : minLength);
	}

}