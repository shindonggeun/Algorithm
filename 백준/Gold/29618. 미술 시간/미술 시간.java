import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int Q;
	static int[] parents;
	static int[] paint;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		parents = new int[N+2];
		paint = new int[N+1];
		
		for (int i=1; i<=N+1; i++) {
			parents[i] = i;
		}
		
		for (int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			int start = find(a);
			while (start <= b) {
				paint[start] = x;
				union(start, start+1);
				start = find(start);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=1; i<=N; i++) {
			sb.append(paint[i]).append(" ");
		}
		
		System.out.println(sb);
	}
	
	public static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot != bRoot) {
			parents[aRoot] = bRoot;
		}
	}

}