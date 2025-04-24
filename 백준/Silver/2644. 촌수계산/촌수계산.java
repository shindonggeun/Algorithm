import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 전체 사람의 수
	static int M; // 부모 자식들 간의 관계의 수
	static List<List<Integer>> graph;
	static boolean[] visited;
	static int resultCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<>();
		visited=  new boolean[N+1];
		resultCount = -1;
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		
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
		
		dfs(0, start, end);

		System.out.println(resultCount);
	}
	
	public static void dfs(int depth, int vertex, int end) {
		// 기저조건
		if (vertex == end) {
			resultCount = depth;
			return; // 메서드 종료
		}
		
		visited[vertex] = true;
		
		for (int next: graph.get(vertex)) {
			if (!visited[next]) {
				dfs(depth + 1, next, end);
			}
		}
	}

}