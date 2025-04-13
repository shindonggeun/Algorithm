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
	static int[] parents;
	static List<Edge> edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];
		edgeList = new ArrayList<>();
		
		for (int i=1; i<=N; i++) {
			int weight = Integer.parseInt(br.readLine());
			edgeList.add(new Edge(0, i, weight));
			parents[i] = i;
		}
		
		
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				edgeList.add(new Edge(i, j, weight));
			}
		}
		
		Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
		
		int totalWeight = 0;
		
		for (Edge edge: edgeList) {
			int rootA = find(edge.fromVertex);
			int rootB = find(edge.toVertex);
			
			if (rootA != rootB) {
				union(edge.fromVertex, edge.toVertex);
				totalWeight += edge.weight;
			}
		}
		
		System.out.println(totalWeight);

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