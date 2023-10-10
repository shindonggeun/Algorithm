import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int M;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			visited = new boolean[N+1];
			
			for(int i=0; i<=N; i++) {
				graph.add(new ArrayList<>());
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				
				// 양방향 간선 연결
				graph.get(fromVertex).add(toVertex);
				graph.get(toVertex).add(fromVertex);
			}
			
			// 해당 정점에 연결된 간선들 오름차순 정렬해주기
			for(int i=1; i<=N; i++) {
				Collections.sort(graph.get(i));
			}
			
			int count = 0;
			for(int i=1; i<=N; i++) {
				if(!visited[i]) {
					dfs(i);
					count++;
				}
			}
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void dfs(int vertex) {
		visited[vertex] = true;
		
		for(int i: graph.get(vertex)) {
			if(!visited[i]) {
				dfs(i);
			}
		}
	}

}
