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
		
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			friendCost[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int friendV = Integer.parseInt(st.nextToken());
			int friendW = Integer.parseInt(st.nextToken());
		
			union(friendV, friendW);
		}
		
		Set<Integer> set = new HashSet<>();
		
		for(int i=1; i<=N; i++) {
			set.add(find(i));
		}
		
		int costSum = 0;
		for(int friendNum: set) {
			costSum += friendCost[friendNum];
		}
		
		if(costSum <= K) {
			System.out.println(costSum);
		}
		else {
			System.out.println("Oh no");
		}

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
		
		if(friendCost[aRoot] < friendCost[bRoot]) {
			parents[bRoot] = aRoot;
		}
		else {
			parents[aRoot] = bRoot;
		}
	}

}
