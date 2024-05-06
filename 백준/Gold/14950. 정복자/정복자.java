import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge {
		int fromVertex;
		int toVertex;
		int weight;
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;
	static int M;
	static int t;
	static int[] parents;
	static ArrayList<Edge> edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		edgeList = new ArrayList<>();
		
		for (int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeList.add(new Edge(fromVertex, toVertex, weight));
		}
		
		Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
		
		int minWeight = 0;
		int start = 0;
		for (Edge edge: edgeList) {
			int root1 = find(edge.fromVertex);
			int root2 = find(edge.toVertex);
			
			if (root1 != root2) {
				union(edge.fromVertex, edge.toVertex);
				minWeight += edge.weight + (t * start);
				start++;
			}
		}
		
		System.out.println(minWeight);
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
		else if (aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}