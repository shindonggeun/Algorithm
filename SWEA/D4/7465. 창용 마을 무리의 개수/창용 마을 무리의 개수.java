import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;	// 창용 마을에 사는 사람의 수
	static int M;	// 서로를 알고 있는 사람의 관계 수
	static ArrayList<ArrayList<Integer>> graph;	// 해당 사람과 관계를 나타내는 그래프 
	static boolean[] visited;	// 방문 배열
	
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
			
			// 해당 정점(사람 번호) 생성하는 과정
			for(int i=0; i<=N; i++) {
				graph.add(new ArrayList<>());
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				
				// 양방향 간선 연결 (사람간 관계 연결)
				graph.get(fromVertex).add(toVertex);
				graph.get(toVertex).add(fromVertex);
			}
			
			// 해당 정점에 연결된 간선들 오름차순 정렬해주기
			for(int i=1; i<=N; i++) {
				Collections.sort(graph.get(i));
			}
			
			int count = 0;	// 해당 무리의 개수
			// 1번 사람부터 N번 사람까지 서로를 알고있는 사람의 관계를 파악하기 위한 과정
			for(int i=1; i<=N; i++) {
				// 방문하지 않은 사람인 경우
				if(!visited[i]) {
					dfs(i);	// 깊이우선탐색 실시
					count++;	// 해당 무리의 개수 증가
				}
			}
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.print(sb);

	}
	
	// 깊이우선탐색 메서드
	public static void dfs(int vertex) {
		visited[vertex] = true;	// 해당 정점(사람 번호) 방문
		
		// 해당 정점에 연결된 정점들 순회 (사람 관계 파악하기)
		for(int i: graph.get(vertex)) {
			// 방문하지 않은 정점인 경우
			if(!visited[i]) {
				dfs(i);	// 깊이우선탐색 실시
			}
		}
	}

}
