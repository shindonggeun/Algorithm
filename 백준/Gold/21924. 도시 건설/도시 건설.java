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
	
	static int N;	// 건물의 개수
	static int M;	// 도로의 개수
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		long totalWeight = 0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			totalWeight += weight;
			
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		long minTotalWeight = prim(1);
		
		boolean check = true;
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				check = false;
			}
		}
		
		if(check) {
			System.out.println(totalWeight - minTotalWeight);
		}
		else {
			System.out.println(-1);
		}
		
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
				
				for(Edge next: graph.get(to)) {
					if(!visited[next.toVertex]) {
						pq.add(next);
					}
				}
			}
		}
		
		return totalWeight;
	}

}
