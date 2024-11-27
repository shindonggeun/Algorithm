import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] numArr;
	static int[] operationArr;
	static int minValue;
	static int maxValue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		numArr = new int[N];
		operationArr = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			operationArr[i] = Integer.parseInt(st.nextToken());
		}
		
		minValue = Integer.MAX_VALUE;
		maxValue = Integer.MIN_VALUE;
		
		calculate(1, numArr[0]);
		
		System.out.println(maxValue);
		System.out.println(minValue);
	}
	
	public static void calculate(int depth, int value) {
		if (depth == N) {
			maxValue = Math.max(maxValue, value);
			minValue = Math.min(minValue, value);
			return;
		}
		
		for (int i=0; i<4; i++) {
			if (operationArr[i] > 0) {
				operationArr[i]--;
				
				if (i == 0) {
					calculate(depth+1, value + numArr[depth]);
				}
				else if (i == 1) {
					calculate(depth+1, value - numArr[depth]);
				}
				else if (i == 2) {
					calculate(depth+1, value * numArr[depth]);
				}
				else {
					calculate(depth+1, value / numArr[depth]);
				}
				
				operationArr[i]++;
			}
		}
	}

}