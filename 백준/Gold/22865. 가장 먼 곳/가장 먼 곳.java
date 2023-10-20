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
	static int A;
	static int B;
	static int C;
	static int[][] dist;
	static boolean[] visited;
	static int[] minDist;
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		dist = new int[3][N+1];
		visited = new boolean[N+1];
		minDist = new int[N+1];
		
		for(int i=0; i<3; i++) {
			Arrays.fill(dist[i], INF);
		}
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		dijkstra(A, 0);
		dijkstra(B, 1);
		dijkstra(C, 2);
		
		int max = -1;
		int maxIdx = -1;
		
		for(int i=1; i<=N; i++) {
			int a = dist[0][i];
			int b = dist[1][i];
			int c = dist[2][i];
			
			int min = Math.min(a, Math.min(b, c));
			
			if(max == min) {
				maxIdx = Math.min(maxIdx, i);
			}
			else if(max < min) {
				max = min;
				maxIdx = i;
			}
		}
		System.out.println(maxIdx);
		
	}
	
	public static void dijkstra(int start, int idx) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));
		dist[idx][start] = 0;
		Arrays.fill(visited, false);
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int to = now.toVertex;
			int weight = now.weight;
			
			if(!visited[to]) {
				visited[to] = true;
				
				for(Edge next: graph.get(to)) {
					int cost = next.weight + weight;
					if(cost < dist[idx][next.toVertex]) {
						dist[idx][next.toVertex] = cost;
						pq.add(new Edge(next.toVertex, dist[idx][next.toVertex]));
					}
				}
			}
		}
	}

}
