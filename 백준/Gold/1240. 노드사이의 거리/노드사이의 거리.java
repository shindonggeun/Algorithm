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
	static ArrayList<ArrayList<Edge>> graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
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
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N+1];
			int distance = bfs(start, end);
			sb.append(distance).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static int bfs(int start, int end) {
		Queue<Edge> queue = new LinkedList<>();
		queue.add(new Edge(start, 0));
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			Edge now = queue.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			if(nowVertex == end) {
				return nowWeight;
			}
			
			for(Edge next: graph.get(nowVertex)) {
				if(!visited[next.toVertex]) {
					visited[next.toVertex] = true;
					queue.add(new Edge(next.toVertex, nowWeight + next.weight));
				}
			}
		}
		
		// 목표 노드에 도달하지 못한 경우
		return -1;	// -1 반환
	}

}
