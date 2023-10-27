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
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		for(int i=1; i<=N-2; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			union(fromVertex, toVertex);
		}
		
		Set<Integer> set = new HashSet<>();
		for(int i=1; i<=N; i++) {
			set.add(find(i));
		}
		
		StringBuilder sb = new StringBuilder();
		for(int islandNum: set) {
			sb.append(islandNum).append(" ");
		}
		System.out.print(sb);
	}
	
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return;
		}
		else if(aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}
