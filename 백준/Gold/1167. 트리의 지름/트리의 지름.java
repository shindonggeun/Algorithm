import java.util.*;
import java.io.*;

public class Main {
	
	// 각 간선의 정보를 담고있는 내부 클래스
	static class Edge {
		int toVertex;	// 연결된 정점
		int weight;		// 간선의 가중치 (거리)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int V;	// 트리의 정점 개수
	static ArrayList<ArrayList<Edge>> graph;	// 그래프를 표현하는 인접 리스트
	static boolean[] visited;	// 각 정점의 방문 여부를 체크하는 배열
	static int farthestVertex;	// 가장 먼 정점
	static int maxDistance;	// 임의의 두 정점 사이의 거리 중 가장 먼 거리 (즉, 트리의 지름)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		V = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();	// 그래프 초기화
		
		// 각 정점별로 인접리스트 초기화
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 간선정보 입력받기
		for(int i=1; i<=V; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			
			while(true) {
				int toVertex = Integer.parseInt(st.nextToken());
				// -1 입력될 시 무한반복 종료
				if(toVertex == -1) {
					break;	// 빠져나옴
				}
				
				int weight = Integer.parseInt(st.nextToken());
				
				// 간선 정보 저장 (입력에서 어차피 양방향으로 주어지므로 단방향으로 연결해도 됨)
				graph.get(fromVertex).add(new Edge(toVertex, weight));
			}
		}
		
		maxDistance = 0;
		visited = new boolean[V+1];	// [1] ~ [V], 각 정점 방문여부 배열 초기화
		dfs(1, 0);	// 첫번째 깊이우선탐색 실시 (임의의 시작정점 1에서 부터 탐색 시작)
		
		Arrays.fill(visited, false);	// 방문배열 초기화
		dfs(farthestVertex, 0);	// 가장 멀리 있는 정점에서 깊이우선탐색 재실시
		
		System.out.println(maxDistance);
	}
	
	// 트리의 지름을 찾는 깊이우선탐색 메서드
	public static void dfs(int now, int distance) {
		visited[now] = true;	// 현재 정점 방문처리
		
		// 현재 거리가 기존 최대 거리보다 큰 경우
		if(distance > maxDistance) {
			maxDistance = distance;	// 최대 거리 (트리의 지름) 갱신
			farthestVertex = now;	// 현재 정점이 가장 멀리 있는 정점으로 갱신
		}
		
		// 현재 정점과 연결된 간선들 탐색
		for(Edge edge: graph.get(now)) {
			// 방문하지 않은 정점인 경우
			if(!visited[edge.toVertex]) {
				// 탐색한 정점 및 기존 거리정보를 더해 깊이우선탐색 실시
				dfs(edge.toVertex, distance + edge.weight);	
			}
		}
		
	}

}
