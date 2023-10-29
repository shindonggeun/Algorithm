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
	static int E;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	static int[] dist;
	static boolean[] visited;
	static final int INF = 200000000;
	static int vertex1;
	static int vertex2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		visited = new boolean[N+1];
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		vertex1 = Integer.parseInt(st.nextToken());
		vertex2 = Integer.parseInt(st.nextToken());
		
		int resultDistance1 = calculateMinDistance(1, vertex1) + 
				calculateMinDistance(vertex1, vertex2) +
				calculateMinDistance(vertex2, N);
		
		int resultDistance2 = calculateMinDistance(1, vertex2) +
				calculateMinDistance(vertex2, vertex1) +
				calculateMinDistance(vertex1, N);
		
		int resultMinDistance = Math.min(resultDistance1, resultDistance2);
		
		if(resultMinDistance >= INF) {
			System.out.println(-1);
		}
		else {
			System.out.println(resultMinDistance);
		}
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int to = now.toVertex;
			int weight = now.weight;
			if(!visited[to]) {
				visited[to] = true;
				for(Edge next: graph.get(to)) {
					int cost = weight + next.weight;
					if(cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));
					}
				}
			}
			
		}
	}
	
	public static int calculateMinDistance(int start, int end) {
		Arrays.fill(dist, INF);
		Arrays.fill(visited, false);
		dijkstra(start);
		return dist[end];
		
	}

}
