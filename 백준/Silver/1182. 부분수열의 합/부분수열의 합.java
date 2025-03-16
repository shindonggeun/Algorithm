import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int S;
	static int[] nums;
	static int resultCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		
		if (S == 0) {
			resultCount--;
		}
		
		System.out.println(resultCount);

	}
	
	public static void dfs(int depth, int sum) {
		if (depth == N) {
			if (sum == S) {
				resultCount++;
			}
			return;
		}
		
		dfs(depth + 1, sum + nums[depth]);
		
		dfs(depth + 1, sum);
	}

}