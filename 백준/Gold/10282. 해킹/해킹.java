import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge {
		int toVertex;	// 도착지 컴퓨터 (컴퓨터 a가 b를 의존할 때 b를 나타냄)
		int weight;	// 가중치 (감염된 시간)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}

	static int N;	// 컴퓨터의 개수
	static int D;	// 의존성의 개수
	static int C;	// 해킹당한 컴퓨터의 번호
	static ArrayList<ArrayList<Edge>> graph;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;
	static boolean[] visited;
	static int infectedCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			dist = new int[N+1];
			visited = new boolean[N+1];
			graph = new ArrayList<>();
			infectedCount = 0;
			for(int i=0; i<=N; i++) {
				graph.add(new ArrayList<>());
				dist[i] = INF;
			}
			
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());	// 컴퓨터 a가
				int toVertex = Integer.parseInt(st.nextToken());	// 컴퓨터 b를 의존한다
				int weight = Integer.parseInt(st.nextToken());
				
				// 컴퓨터 a가 컴퓨터 b를 의존한다
				graph.get(toVertex).add(new Edge(fromVertex, weight));
			}
			
			dijkstra(C);
			
			int infectedCount = 0;
			int totalInfectedTime = 0;
			
			for(int i=1; i<=N; i++) {
				if(dist[i] != INF) {
					infectedCount++;
					totalInfectedTime = Math.max(totalInfectedTime, dist[i]);
				}
			}
			
			sb.append(infectedCount).append(" ").append(totalInfectedTime).append("\n");
		}
		System.out.print(sb);
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
					int cost = next.weight + weight;
					if(cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));
					}
				}
			}
		}
	}

}
