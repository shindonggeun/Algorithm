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
	
	static int M;	// 집의 수
	static int N;	// 길의 수
	static ArrayList<ArrayList<Edge>> graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			if(M == 0 && N == 0) {
				break;
			}
			
			graph = new ArrayList<>();
			visited = new boolean[M];
			
			for(int i=0; i<M; i++) {
				graph.add(new ArrayList<>());
			}
			
			int totalWeight = 0;	// 모든 길에 가로등이 있을 때 비용
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				totalWeight += weight;
				graph.get(fromVertex).add(new Edge(toVertex, weight));
				graph.get(toVertex).add(new Edge(fromVertex, weight));
			}
			
			
			int minTotalWeight = prim(0);
			
			sb.append(totalWeight - minTotalWeight).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static int prim(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));
		int totalWeight = 0;
		
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
