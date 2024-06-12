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
	static int A; // 시작지
	static int B; // 도착지
	static int C; // 가진 돈
	static int[] dist;
	static boolean[] visited;
 	static final int INF = Integer.MAX_VALUE;
	static ArrayList<ArrayList<Edge>> graph;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		visited = new boolean[N+1];
		graph = new ArrayList<>();

		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		result = -1;
		binarySearch();
		System.out.println(result);
	}
	
	public static void binarySearch() {
		int start = 0;
		int end = 1000;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (dijkstra(mid)) {
				end = mid - 1;
				result = mid;
			}
			else {
				start = mid + 1;
			}
		}
	}
	
	public static boolean dijkstra(int maxWeight) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(A, 0));
		Arrays.fill(dist, INF);
		Arrays.fill(visited, false);
		dist[A] = 0;
		
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			if (!visited[nowVertex]) {
				visited[nowVertex] = true;
				
				for (Edge next: graph.get(nowVertex)) {
					if (next.weight > maxWeight) {
						continue;
					}
					
					int cost = nowWeight + next.weight;
					if (cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;
						pq.add(new Edge(next.toVertex, cost));
					}
				}
			}
		}
		
		if (dist[B] <= C) {
			return true;
		}
		
		return false;
	}

}