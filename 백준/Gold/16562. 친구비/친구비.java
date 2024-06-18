import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int K;
	static int[] parents;
	static int[] friendCost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		friendCost = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			parents[i] = i;
			friendCost[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int friend1 = Integer.parseInt(st.nextToken());
			int friend2 = Integer.parseInt(st.nextToken());
			
			union(friend1, friend2);
		}
		
		Set<Integer> rootSet = new HashSet<>();
		
		for (int i=1; i<=N; i++) {
			int root = find(i);
			rootSet.add(root);
		}
		
		int resultCost = 0;
		
		for (int friend: rootSet) {
			resultCost += friendCost[friend];
		}
		
		System.out.println(resultCost <= K ? resultCost : "Oh no");

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
		
		if (friendCost[aRoot] > friendCost[bRoot]) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}