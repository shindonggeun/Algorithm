import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge {
		int toVertex;
		int weight;
		int state;
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
		
		public Edge(int toVertex, int weight, int state) {
			this.toVertex = toVertex;
			this.weight = weight;
			this.state = state;
		}
	}
	
	static int N;
	static int M;
	static int[] distByFox;
	static int[][] distByWolf;
	static final int INF = Integer.MAX_VALUE;
	static boolean[] visitedByFox;
	static boolean[][] visitedByWolf;
	static ArrayList<ArrayList<Edge>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		distByFox = new int[N+1];
		distByWolf = new int[2][N+1];	// [0]: 달리는 상태, [1]: 걷는 상태
		visitedByFox = new boolean[N+1];
		visitedByWolf = new boolean[2][N+1];
		graph = new ArrayList<>();
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 양방향 간선 연결
			graph.get(fromVertex).add(new Edge(toVertex, weight*2));
			graph.get(toVertex).add(new Edge(fromVertex, weight*2));
		}
		
		
		dijkstraByFox(1);
		dijkstraByWolf(1);
		
		int count = 0;
		for (int i=2; i<=N; i++) {
			if (distByFox[i] < Math.min(distByWolf[0][i], distByWolf[1][i])) {
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	public static void dijkstraByFox(int vertex) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(vertex, 0));
		
		Arrays.fill(distByFox, INF);
		Arrays.fill(visitedByFox, false);
		
		distByFox[vertex] = 0;
		
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			if (!visitedByFox[nowVertex]) {
				visitedByFox[nowVertex] = true;
				
				for (Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight;
					if (cost < distByFox[next.toVertex]) {
						distByFox[next.toVertex] = cost;
						pq.add(new Edge(next.toVertex, cost));
					}
				}
			}
		}
	}
	
	public static void dijkstraByWolf(int vertex) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(vertex, 0, 0));
		
		for (int i=0; i<2; i++) {
			Arrays.fill(distByWolf[i], INF);
			Arrays.fill(visitedByWolf[i], false);
		}
		
		distByWolf[0][vertex] = 0;
		
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			int nowState = now.state;
			
			if (!visitedByWolf[nowState][nowVertex]) {
				visitedByWolf[nowState][nowVertex] = true;
				
				for (Edge next: graph.get(nowVertex)) {
					int nextState = -1;
					int nextWeight = 0;
					if (nowState == 0) {
						nextWeight = next.weight / 2;
						nextState = 1;
					}
					else {
						nextWeight = next.weight * 2;
						nextState = 0;
					}
						
					int cost = nowWeight + nextWeight;
						
					if (cost < distByWolf[nextState][next.toVertex]) {
						distByWolf[nextState][next.toVertex] = cost;
						pq.add(new Edge(next.toVertex, cost, nextState));
					}
				}
			}
				
		}
	}

}