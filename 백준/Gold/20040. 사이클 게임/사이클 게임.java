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
		
		parents = new int[N]; // [0] ~ [N-1]
		
		for (int i=0; i<N; i++) {
			parents[i] = i;
		}
		
		boolean isCycle = false;
		int result = 0;
		
		for (int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			if (isCycle) {
				continue;
			}
			
			if (find(fromVertex) != find(toVertex)) {
				union(fromVertex, toVertex);
			}
			else {
				isCycle = true;
				result = i;
			}
		}
		
		System.out.println(isCycle == true ? result : 0);
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
			parents[bRoot] = aRoot;
		}
	}

}