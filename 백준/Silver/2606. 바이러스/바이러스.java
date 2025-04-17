import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 컴퓨터의 수 (정점 개수)
	static int M; // 네트워크로 연결된 컴퓨터 쌍의 수 (간선 수)
	static List<List<Integer>> graph; // 그래프 (인접 리스트 이용)
	static boolean[] visited; // 각 컴퓨터마다 방문 여부를 나타내는 배열
	static int virusCount; // 바이러스 걸린 컴퓨터 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<>(); // 그래프 생성
		visited = new boolean[N+1]; // [1] ~ [N]
		
		// 그래프 초기화 (각 정점마다 인접 리스트 초기화)
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
		
		dfs(1); // 1번 컴퓨터부터 깊이 우선 탐색 실시
		
		System.out.println(virusCount);
	}
	
	// 깊이 우선 탐색을 통해 해당 컴퓨터에서부터 시작하여 바이러스가 걸린 컴퓨터들의 수를 찾는 메서드
	public static void dfs(int vertex) {
		visited[vertex] = true; // 해당 탐색한 컴퓨터 방문 처리
		
		// 해당 탐색한 컴퓨터로부터 연결된 컴퓨터들 탐색
		for (int next: graph.get(vertex)) {
			// 해당 컴퓨터가 방문처리 안된 경우
			if (!visited[next]) {
				virusCount++; // 바이러스 개수 증가
				dfs(next); // 연결된 다음 컴퓨터 다시 깊이 우선 탐색 실시
			}
		}
	}

}