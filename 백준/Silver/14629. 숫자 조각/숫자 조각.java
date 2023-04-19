import java.util.*;
import java.io.*;

public class Main {
	
	static String num;
	static long N;
	static int[] arr;
	static boolean[] visited;
	static long min = Long.MAX_VALUE;
	static long result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		num = st.nextToken();
		N = Long.parseLong(num);
		
		arr = new int[10];	// 0 ~ 9
		visited = new boolean[10];
		
		for(int i=0; i<10; i++) {
			arr[i] = i;
		}
		
		if(N > 9876543210L) {
			System.out.println("9876543210");
		}
		else {
			dfs(0, 0);
			System.out.println(result);
		}
		
	}
	
	// 순열 메서드 이용
	public static void dfs(int depth, long n) {
		if(depth == num.length()) {
			//System.out.println(n);
			long diff = Math.abs(N - n);
			if(min > diff) {
				min = diff;
				result = n;
			}
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(depth+1, n*10+arr[i]);
				visited[i] = false;
			}
		}
	}

}
