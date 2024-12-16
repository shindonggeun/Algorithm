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
	
	static int C;
	static int P;
	static int PB;
	static int PA1;
	static int PA2;
	static int[] distFromPB;
	static int[] distFromPA1;
	static int[] distFromPA2;
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<ArrayList<Edge>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		PB = Integer.parseInt(st.nextToken());
		PA1 = Integer.parseInt(st.nextToken());
		PA2 = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		
		for (int i=0; i<=P; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<C; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		distFromPB = dijkstra(PB);
		distFromPA1	= dijkstra(PA1);
		distFromPA2 = dijkstra(PA2);
		
		long path1 = (long) distFromPB[PA1] + distFromPA1[PA2];
		long path2 = (long) distFromPB[PA2] + distFromPA2[PA1];
		
		System.out.println(Math.min(path1, path2));
	}
	
	public static int[] dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));
		
		int[] dist = new int[P+1];
		boolean[] visited = new boolean[P+1];
		
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
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