import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		for (int i=0; i<N-2; i++) {
			st = new StringTokenizer(br.readLine());
			int island1 = Integer.parseInt(st.nextToken());
			int island2 = Integer.parseInt(st.nextToken());
			
			union(island1, island2);
		}
		
		Set<Integer> rootSet = new HashSet<>();
		
		for (int i=1; i<=N; i++) {
			int root = find(i);
			rootSet.add(root);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int island: rootSet) {
			sb.append(island).append(" ");
		}
		
		System.out.print(sb);
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