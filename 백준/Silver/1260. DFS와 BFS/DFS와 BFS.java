import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int V;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		graph = new ArrayList<>();
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}
		
		for (int i=1; i<=N; i++) {
			Collections.sort(graph.get(i));
		}
		
		sb = new StringBuilder();
		dfs(V);
		sb.append("\n");
		
		visited = new boolean[N+1];
		bfs(V);
		
		System.out.println(sb);
	}
	
	public static void dfs(int vertex) {
		if (!visited[vertex]) {
			visited[vertex] = true;
			sb.append(vertex).append(" ");
			
			for (int next: graph.get(vertex)) {
				dfs(next);
			}
		}
	}
	
	public static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;
		sb.append(start).append(" ");
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int next: graph.get(now)) {
				if (!visited[next]) {
					visited[next] = true;
					sb.append(next).append(" ");
					queue.add(next);
				}
			}
		}
	}

}