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
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	static boolean[] visited;
	static long resultDistance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		visited = new boolean[N+1];
		resultDistance = Long.MIN_VALUE;
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		dfs(1, 0);
		System.out.println(resultDistance);
	}
	
	public static void dfs(int vertex, long sumWeight) {
		if(resultDistance < sumWeight) {
			resultDistance = sumWeight;
		}
		
		visited[vertex] = true;
		
		for(Edge next: graph.get(vertex)) {
			if(!visited[next.toVertex]) {
				visited[next.toVertex] = true;
				dfs(next.toVertex, sumWeight+next.weight);
			}
		}
	}

}
