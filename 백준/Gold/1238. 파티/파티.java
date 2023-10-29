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
	static int X;
	static int[] dist;
	static int[] toX;	// 각 학생 마을에서 X 마을로의 최단 거리를 저장한 배열
	static int[] fromX;	// X마을에서 각 학생의 마을로 가는데 최단 거리를 저장한 배열
	static final int INF = Integer.MAX_VALUE;
	static boolean[] visited;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		visited = new boolean[N+1];
		toX = new int[N+1];
		fromX = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(new Edge(toVertex, weight));
		}
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(dist, INF);
			Arrays.fill(visited, false);
			dijkstra(i);
			toX[i] = dist[X];	// 각 학생 마을에서 X 마을로의 최단 거리 계산
		}
		
		// X마을에서 시작해서 각 학생 마을로의 최단 거리를 계산하는 과정
		Arrays.fill(dist, INF);
		Arrays.fill(visited, false);
		dijkstra(X);
		
		for(int i=1; i<=N; i++) {
			fromX[i] = dist[i];
		}
		
		int maxTime = Integer.MIN_VALUE;
		for(int i=1; i<=N; i++) {
			maxTime = Math.max(maxTime, toX[i] + fromX[i]);
		}
		System.out.println(maxTime);
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

}
