import java.util.*;
import java.io.*;

public class Main {
	
	static int T;
	static int N;
	static int[] nums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int testCase=0; testCase<T; testCase++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			nums = new int[N];
			
			for (int i=0; i<N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			long gcdSum = 0;
			for (int i=0; i<N-1; i++) {
				for (int j=i+1; j<N; j++) {
					gcdSum += gcd(nums[i], nums[j]);
				}
			}
			
			sb.append(gcdSum).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

}