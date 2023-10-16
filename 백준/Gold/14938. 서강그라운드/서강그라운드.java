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
	
	static int N;	// 지역의 개수
	static int M;	// 수색 범위
	static int R;	// 길의 개수
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	static int[] itemCount;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;
	static boolean[] visited;
	static int maxItemCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		itemCount = new int[N+1];
		maxItemCount = Integer.MIN_VALUE;
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			itemCount[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		
		for(int i=1; i<=N; i++) {
			dist = new int[N+1];
			visited = new boolean[N+1];
			Arrays.fill(dist, INF);
			int tempItemCount = dijkstra(i);
			maxItemCount = Math.max(maxItemCount, tempItemCount);
		}
		
		System.out.println(maxItemCount);
	}
	
	public static int dijkstra(int start) {
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
					}
				}
			}
		}
		
		int resultItemCount = 0;
		for(int i=1; i<=N; i++) {
			if(dist[i] <= M) {
				resultItemCount += itemCount[i];
			}
		}
		
		return resultItemCount;
	}

}
