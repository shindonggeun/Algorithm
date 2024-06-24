import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		visited = new boolean[N+1];
		parents = new int[N+1];
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}
		
		dfs(1);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=2; i<=N; i++) {
			sb.append(parents[i]).append("\n");
		}
		
		System.out.print(sb);
		
	}
	
	public static void dfs(int vertex) {
		visited[vertex] = true;
		
		for (int i: graph.get(vertex)) {
			if (!visited[i]) {
				parents[i] = vertex;
				dfs(i);
			}
		}
	}

}