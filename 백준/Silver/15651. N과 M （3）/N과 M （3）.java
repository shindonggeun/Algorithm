import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] permutation;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		permutation = new int[M];
		sb = new StringBuilder();
		
		generatePermutation(0);
		
		System.out.print(sb);
	}
	
	public static void generatePermutation(int depth) {
		if (depth == M) {
			for (int i=0; i<M; i++) {
				sb.append(permutation[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=0; i<N; i++) {
			permutation[depth] = i + 1;
			generatePermutation(depth + 1);
		}
	}

}