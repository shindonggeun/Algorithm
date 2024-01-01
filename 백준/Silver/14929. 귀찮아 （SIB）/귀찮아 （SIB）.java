import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] numArr;
	static int[] sumArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		numArr = new int[N];
		sumArr = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sumValue = 0;
		for(int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
			sumValue += numArr[i];
			sumArr[i+1] = sumValue;
		}
		
		long sum = 0;
        for(int i = 0; i < N - 1; i++) {
            int calculateSum = sumArr[N] - sumArr[i+1];
            sum += numArr[i] * calculateSum;
        }
        
        System.out.println(sum);
	}

}
