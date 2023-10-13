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
	static int M;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	static int[] dist;
	static boolean[] visited;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dist = new int[N+1];
		visited = new boolean[N+1];
		Arrays.fill(dist, INF);
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 단방향 간선 연결 (출발지 -> 도착지)
			graph.get(fromVertex).add(new Edge(toVertex, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start, end);
		System.out.println(dist[end]);
	}
	
	public static void dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int to = now.toVertex;
			
			if(!visited[to]) {
				visited[to] = true;
				
				for(Edge edge: graph.get(to)) {
					if(!visited[edge.toVertex]) {
						int cost = dist[to] + edge.weight;
						if(cost < dist[edge.toVertex]) {
							dist[edge.toVertex] = cost;
							pq.add(new Edge(edge.toVertex, dist[edge.toVertex]));
						}
					}
				}
			}
			
		}
	}

}
