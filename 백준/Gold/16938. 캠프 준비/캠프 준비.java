import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int L;
	static int R;
	static int X;
	static int[] questionArr;
	static int[] combination;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		questionArr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			questionArr[i] = Integer.parseInt(st.nextToken());
		}
		
		count = 0;
		
		for (int i=2; i<=N; i++) {
			combination = new int[i];
			generateCombination(0, 0, i, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
		}
		
		System.out.println(count);
	}
	
	public static void generateCombination(int depth, int idx, int target, int sum, int min, int max) {
		if (depth == target) {
			if (sum >= L && sum <= R && (max - min) >= X) {
				count++;
			}
			return;
		}
		
		for (int i=idx; i<N; i++) {
			combination[depth] = questionArr[i];
			int newMin = Math.min(min, questionArr[i]);
            int newMax = Math.max(max, questionArr[i]);
			generateCombination(depth+1, i+1, target, sum + combination[depth], newMin, newMax);
		}
	}

}
