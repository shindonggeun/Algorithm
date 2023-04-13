import java.util.*;
import java.io.*;

public class Main {
	static int[] arr;
	static int[] output;
	static boolean[] visited;
	static int n;
	static int k;
	static HashSet<String> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		output = new int[n];
		visited = new boolean[n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0);
		System.out.println(set.size());
	}
	// 순열 메서드 이용
	public static void dfs(int depth) {
		if(depth == k) {
			String result = "";
			for(int i=0; i<k; i++) {
				result+=String.valueOf(output[i]);
			}
			set.add(result);
		}
		
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = arr[i];
				dfs(depth+1);
				visited[i] = false;
			}
		}
	}

}
