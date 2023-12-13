import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge implements Comparable<Edge> {
		int fromVertex;
		int toVertex;
		int weight;
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static int N;
	static int M;
	static int[] parents;
	static ArrayList<Edge> edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		edgeList = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		

		long totalWeight = 0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeList.add(new Edge(fromVertex, toVertex, weight));
			totalWeight += weight;
		}
		
		Collections.sort(edgeList);
		
		long minTotalWeight = 0;
		for(Edge edge: edgeList) {
			// 사이클이 형성되지 않으면 해당 간선 선택
			if(find(edge.fromVertex) != find(edge.toVertex)) {
				union(edge.fromVertex, edge.toVertex);
				minTotalWeight += edge.weight;
			}
		}
		
		Set<Integer> set = new HashSet<>();
		
		for(int i=1; i<=N; i++) {
			int root = find(i);
			set.add(root);
		}
		
		if(set.size() == 1) {
			System.out.println(totalWeight - minTotalWeight);
		}
		else {
			System.out.println(-1);
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
		else if(aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}
