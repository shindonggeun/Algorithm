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
		for(int i=0; i<=N; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeList.add(new Edge(fromVertex, toVertex, weight));
		}
		
		Collections.sort(edgeList);
		int totalWeight = 0;
		int maxWeight = 0; // 가장 큰 가중치를 저장할 변수
		
		for(Edge edge: edgeList) {
			int root1 = find(edge.fromVertex);
			int root2 = find(edge.toVertex);
			
			if(root1 != root2) {
				union(edge.fromVertex, edge.toVertex);
				totalWeight += edge.weight;
				maxWeight = Math.max(maxWeight, edge.weight); // 가장 큰 가중치 갱신
			}
		}
		
		System.out.println(totalWeight - maxWeight);
		
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
