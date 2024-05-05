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
	static ArrayList<Edge> edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];
		edgeList = new ArrayList<>();
		
		for (int i=1; i<=N; i++) {
			parents[i] = i; 
		}
		
		int totalWeight = 0;
		for (int i=1; i<=N; i++) {
			String input = br.readLine();
			for (int j=1; j<=N; j++) {
				int fromVertex = i;
				int toVertex = j;
				char ch = input.charAt(j-1);
				int weight = 0;
				
				if (ch == '0') {
					continue;
				}
				
				if (ch >= 'a' && ch <= 'z') {
					weight = ch - 'a' + 1;
				}
				else {
					weight = ch - 'A' + 27;
				}

				totalWeight += weight;
				
				edgeList.add(new Edge(fromVertex, toVertex, weight));
			}
		}
		
		Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
		
		int minWeight = 0;
		int edgeCount = 0;
		for (Edge edge: edgeList) {
			int root1 = find(edge.fromVertex);
			int root2 = find(edge.toVertex);
			
			if (root1 != root2) {
				union(edge.fromVertex, edge.toVertex);
				minWeight += edge.weight;
				edgeCount++;
			}
			
			if (edgeCount == N-1) {
				break;
			}
		}
		
		if (edgeCount < N-1) {
			System.out.println(-1);
		}
		else {
			System.out.println(totalWeight - minWeight);
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
		else if (aRoot > bRoot) {
			parents[aRoot] = bRoot; 
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}
