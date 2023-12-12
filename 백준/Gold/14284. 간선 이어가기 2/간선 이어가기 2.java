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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		visited = new boolean[N+1];
		for(int i=0; i<=N; i++) {
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
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		System.out.println(dist[end]);
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			if(!visited[nowVertex]) {
				visited[nowVertex] = true;
				
				for(Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight;
					
					if(cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));
					}
				}
			}
		}
	}

}
