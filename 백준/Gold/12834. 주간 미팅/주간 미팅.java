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
	static int V;
	static int E;
	static ArrayList<ArrayList<Edge>> graph;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		
		for (int i=0; i<=V; i++) {
			graph.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] team = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			team[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		
		int[] distFromA = dijkstra(A);
		int[] distFromB = dijkstra(B);
		
		int totalMinDistance = 0;
		
		for (int i=0; i<N; i++) {
			int house = team[i];
			
			int distToA = distFromA[house];
			int distToB = distFromB[house];
			
			if (distToA == INF) {
				distToA = -1;
			}
			
			if (distToB == INF) {
				distToB = -1;
			}
			
			totalMinDistance += (distToA + distToB);
		}
		
		System.out.println(totalMinDistance);
		
	}
	
	public static int[] dijkstra(int vertex) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(vertex, 0));
		
		int[] dist = new int[V+1];
		boolean[] visited = new boolean[V+1];
		Arrays.fill(dist, INF);
		
		dist[vertex] = 0;
		
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			if (!visited[nowVertex]) {
				visited[nowVertex] = true;
				
				for (Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight;
					
					if (cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;
						pq.add(new Edge(next.toVertex, cost));
					}
				}
			}
		}
		
		return dist;
	}

}