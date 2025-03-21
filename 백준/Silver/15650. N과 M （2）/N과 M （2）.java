import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] arr;
	static int[] combination;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N]; // [0] ~ [N-1]
		combination = new int[M]; // [0] ~ [M-1]
		
		for (int i=0; i<N; i++) {
			arr[i] = i + 1;
		}
		
		sb = new StringBuilder();
		
		generateCombination(0, 0);
		
		System.out.print(sb);
	}
	
	public static void generateCombination(int depth, int idx) {
		if (depth == M) {
			for (int i=0; i<M; i++) {
				sb.append(combination[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=idx; i<N; i++) {
			combination[depth] = arr[i];
			generateCombination(depth + 1, i + 1);
		}
	}

}