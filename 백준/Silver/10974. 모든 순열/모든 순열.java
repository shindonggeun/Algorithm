import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] arr;
	static int[] output;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		output = new int[N];
		visited = new boolean[N];
		
		sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			arr[i] = i+1;
		}
		
		dfs(0);
		System.out.print(sb);
	}
	
	// 순열 메서드
	public static void dfs(int depth) {
		if(depth == N) {
			for(int i=0; i<N; i++) {
				sb.append(output[i] + " ");
			}
			sb.append("\n");
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