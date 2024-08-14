import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int K;
	static int[] temperatureArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		temperatureArr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			temperatureArr[i] = Integer.parseInt(st.nextToken());
		}
		
		int continuousSum = 0;
		for (int i=0; i<K; i++) {
			continuousSum += temperatureArr[i];
		}
		
		int maxContinuousSum = continuousSum;
		
		for (int i=K; i<N; i++) {
			continuousSum += temperatureArr[i] - temperatureArr[i-K];
			maxContinuousSum = Math.max(maxContinuousSum, continuousSum);
		}
		
		System.out.println(maxContinuousSum);
	}

}