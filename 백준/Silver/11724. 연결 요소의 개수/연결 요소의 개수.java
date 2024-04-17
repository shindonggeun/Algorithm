import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 정점의 개수
	static int M;	// 간선의 개수
	static ArrayList<ArrayList<Integer>> graph;	// 그래프
	static boolean[] visited;	// 정점들 방분 여부를 나타내는 배열
	static int connectedCount;	// 연결 요소의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();	
		visited = new boolean[N+1];	// [1] ~ [N]
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			// 양방향 간선 연결 (양방향 그래프 => 방향 없는 그래프)
			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}
		
		// 1번부터 N번 정점까지 탐색
		for (int i=1; i<=N; i++) {
			// 방문안한 정점인 경우
			if (!visited[i]) {
				connectedCount++;	// 연결 요소의 개수 증가
				dfs(i);	// 해당 정점부터 깊이우선탐색 실시
			}
		}
		
		System.out.println(connectedCount);
	}
	
	// 깊이우선탐색 메서드
	public static void dfs(int vertex) {
		visited[vertex] = true;	// 해당 정점 방문처리
	
		// 현재 방문한 정점에 연결된 정점들 탐색
		for (int i: graph.get(vertex)) {
			// 방문안한 정점인 경우
			if (!visited[i]) {
				dfs(i);	// 깊이우선탐색 실시 (재귀 호출)
			}
		}
	}

}
