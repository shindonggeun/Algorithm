import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge {
		int toVertex;
		long weight;
		
		public Edge(int toVertex, long weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;
	static int M;
	static int[] view;
	static long[] dist;
	static boolean[] visited;
	static final long INF = Long.MAX_VALUE;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		view = new int[N];
		dist = new long[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			view[i] = Integer.parseInt(st.nextToken());
			graph.add(new ArrayList<>());
			dist[i] = INF;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		
		dijkstra(0);
		if(dist[N-1] == Long.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(dist[N-1]);
		}
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
		pq.add(new Edge(start, 0));
		dist[0] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int to = now.toVertex;
			long weight = now.weight;
			
			if(!visited[to] && view[to] == 0) {
				visited[to] = true;
				
				for(Edge next: graph.get(to)) {
					long cost = next.weight + weight;
					if(cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));
					}
				}
			}
		}
	}

}
