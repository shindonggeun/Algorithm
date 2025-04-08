import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int K;
	static int[] parents;
	static int[] sizes;
	static int uK;
	static int vK;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1]; // [1] ~ [N]
		sizes = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			parents[i] = i;
			sizes[i] = 1;
		}
		
		for (int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			if (i == K) {
				uK = fromVertex;
				vK = toVertex;
				continue;
			}
			
			union(fromVertex, toVertex);
		}
		
		if (find(uK) == find(vK)) {
			System.out.println(0);
		}
		else {
			long a = sizes[find(uK)];
			long b = sizes[find(vK)];
			
			System.out.println(a * b);
		}
		
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
		
		if (aRoot == bRoot) {
			return;
		}
		
		if (sizes[aRoot] < sizes[bRoot]) {
			parents[aRoot] = bRoot;
			sizes[bRoot] += sizes[aRoot];
		}
		else {
			parents[bRoot] = aRoot;
			sizes[aRoot] += sizes[bRoot];
		}
	}
	

}