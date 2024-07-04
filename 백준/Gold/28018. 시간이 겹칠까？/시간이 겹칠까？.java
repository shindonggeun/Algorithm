import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] diffArr;
	static int[] resultArr;
	static int Q;
	static final int MAX = 1000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		diffArr = new int[MAX+2];
		resultArr = new int[MAX+1];
		
		for (int i=0; i<N; i++) {
			st =  new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			diffArr[S] += 1;
			diffArr[E+1] -= 1;
		}
		
		
		int sum = 0;
		for (int i=1; i<=MAX; i++) {
			sum += diffArr[i];
			resultArr[i] = sum;
		}
		
		Q = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<Q; i++) {
			int time = Integer.parseInt(st.nextToken());
			sb.append(resultArr[time]).append("\n");
		}
		
		System.out.print(sb);
	}

}