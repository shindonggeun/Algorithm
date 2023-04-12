import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] arr;
	static int[] output;
	static boolean[] visited;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		output = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0);
		System.out.println(max);
	}
	
	// 순열 메서드
	public static void dfs(int depth) {
		if(depth == N) {
			int sum = 0;
			// |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]| 가 최대가 되게끔
			for(int i=0; i<N-1; i++) {
				int temp = Math.abs(output[i] - output[i+1]);
				sum+=temp;
			}
			max = Math.max(max, sum);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = arr[i];
				dfs(depth+1);
				visited[i] = false;
			}
		}
	}

}