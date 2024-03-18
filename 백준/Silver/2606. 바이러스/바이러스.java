import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 정점의 개수 (컴퓨터의 수)
	static int M;	// 간선의 개수 (네트워크 상에 연결되어 있는 컴퓨터 쌍의 수)
	static ArrayList<ArrayList<Integer>> graph;	// 그래프 선언
	static boolean[] visited;	// 각 정점(컴퓨터) 방문 여부를 저장하는 배열
	static int virusCount;	// 바이러스에 걸린 컴퓨터의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<>();
		visited = new boolean[N+1];	// [1] ~ [N]
		virusCount = 0;	// 바이러스에 걸린 컴퓨터의 수 초기화
		
		// 그래프 정점(노드) 생성해주기
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}

		M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			// 양방향 간선 연결 (네트워크로 연결)
			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}
		
		dfs(1);	// 1번 바이러스부터 깊이우선탐색 실시
		System.out.println(virusCount);
	}
	
	public static void dfs(int vertex) {
		visited[vertex] = true;	// 해당 정점 (컴퓨터) 방문처리
		
		// 해당 정점(컴퓨터)에 연결된 다른 컴퓨터들 탐색
		for (int i: graph.get(vertex)) {
			// 방문하지 않은 정점(컴퓨터)인 경우
			if (!visited[i]) {
				virusCount++;	// 바이러스 걸린 컴퓨터의 수 증가
				dfs(i);	// 깊이우선탐색 실시
			}
		}
	}

}
