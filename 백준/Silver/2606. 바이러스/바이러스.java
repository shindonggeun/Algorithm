import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 컴퓨터의 수 (정점 개수)
	static int M; // 네트워크로 연결된 컴퓨터 쌍의 수 (간선 수)
	static List<List<Integer>> graph;
	static boolean[] visited;
	static int virusCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<>();
		visited = new boolean[N+1]; // [1] ~ [N]
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			// 양방향 간선 연결
			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}
		
		dfs(1);
		
		System.out.println(virusCount);
	}
	
	public static void dfs(int vertex) {
		visited[vertex] = true;
		
		for (int next: graph.get(vertex)) {
			if (!visited[next]) {
				dfs(next);
				virusCount++;
			}
		}
	}

}