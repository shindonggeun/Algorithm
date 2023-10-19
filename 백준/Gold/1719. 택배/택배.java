import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge {
		int toVetex;
		int weight;
		
		public Edge(int toVertex, int weight) {
			this.toVetex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;
	static int M;
	static int[] dist;
	static boolean[] visited;
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	static int[][] pathMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		visited = new boolean[N+1];
		pathMap = new int[N+1][N+1];
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(dist, INF);
			Arrays.fill(visited, false);
			dijkstra(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i == j) {
					sb.append("-").append(" ");
				}
				else {
					sb.append(pathMap[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int to = now.toVetex;
			int weight = now.weight;
			
			if(!visited[to]) {
				visited[to] = true;
				
				for(Edge next: graph.get(to)) {
					int cost = next.weight + weight;
					if(cost < dist[next.toVetex]) {
						dist[next.toVetex] = cost;
						pq.add(new Edge(next.toVetex, dist[next.toVetex]));
						pathMap[next.toVetex][start] = to;
					}
				}
			}
		}
	}

}
