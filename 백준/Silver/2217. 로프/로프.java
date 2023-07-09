import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] lope = new int[N];
		
		for(int i=0; i<N; i++) {
			lope[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(lope);	// 오름차순 정렬
		int maxWeight = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			maxWeight = Math.max(maxWeight, lope[i] * (N - i));
		}
		
		System.out.println(maxWeight);
	}

}
