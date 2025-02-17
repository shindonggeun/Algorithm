import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] leftCounts;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		leftCounts = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			leftCounts[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] result = new int[N];
		
		for (int height=1; height<=N; height++) {
			int leftCount = leftCounts[height - 1];
			int count = 0;
			
			for (int i=0; i<N; i++) {
				if (result[i] == 0) {
					if (count == leftCount) {
						result[i] = height;
						break;
					}
					count++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			sb.append(result[i]).append(" ");
		}
		
		System.out.println(sb);

	}

}