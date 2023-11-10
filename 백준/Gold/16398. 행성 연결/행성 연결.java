import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge {
		int toVertex;
		int weight;
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;
	static int[][] flowCost;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		flowCost = new int[N][N];
		visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				flowCost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long minTotalFlowCost = prim(0);
		System.out.println(minTotalFlowCost);
	}
	
	public static long prim(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));
		long totalWeight = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int to = now.toVertex;
			int weight = now.weight;
			
			if(!visited[to]) {
				visited[to] = true;
				totalWeight += weight;
				
				for(int i=0; i<N; i++) {
					if(!visited[i] && flowCost[to][i] != 0) {
						pq.add(new Edge(i, flowCost[to][i]));
					}
				}
			}
		}
		
		return totalWeight;
	}

}
