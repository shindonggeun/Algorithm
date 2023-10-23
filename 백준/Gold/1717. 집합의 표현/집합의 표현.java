import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int operation = Integer.parseInt(st.nextToken());
			int aRoot = Integer.parseInt(st.nextToken());
			int bRoot = Integer.parseInt(st.nextToken());
			
			// 
			if(operation == 0) {
				union(aRoot, bRoot);
			}
			else {
				if(isSameParent(aRoot, bRoot)) {
					sb.append("YES").append("\n");
				}
				else {
					sb.append("NO").append("\n");
				}
			}
		}
		
		System.out.print(sb);
	}
	
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		}
		parents[a] = find(parents[a]);
		return parents[a];
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return false;
		}
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static boolean isSameParent(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return true;
		}
		return false;
		
	}

}
