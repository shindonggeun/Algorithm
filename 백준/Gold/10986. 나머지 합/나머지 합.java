import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] numArr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] prefixSum = new int[N+1];	// 누적합을 저장할 배열
		int[] remainderCount = new int[M];
		for(int i=0; i<N; i++) {
			prefixSum[i+1] = (prefixSum[i] + numArr[i]) % M;
		}
		
		long count = 0;
		for(int i=0; i<N+1; i++) {
			count += remainderCount[prefixSum[i]];
			remainderCount[prefixSum[i]]++;
			
		}
		
		System.out.println(count);
	}

}
