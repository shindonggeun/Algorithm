import java.util.*;
import java.io.*;

public class Main {
	
	// 컴퓨터끼리 연결할 때 간선 정보를 담은 내부 클래스
	static class Edge {
		int toVertex;	// 도착지 컴퓨터 (정점)
		int weight;	// 컴퓨터를 연결하는데 필요한 비용 (가중치) 
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 컴퓨터의 수 (정점의 수)
	static int M;	// 연결할 수 있는 선의 수 (간선의 수)
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();	// 프림 알고리즘에 사용할 그래프 (인접 리스트 이용)
	static boolean[] visited;	// 각 컴퓨터 (정점) 방문 여부
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];	// [1] ~ [N]
		// 각 정점에 연결된 간선들 표현하기 위해 각 정점들 생성 (그래프 초기화 작업)
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 양방향 간선 연결 (프림 알고리즘 -> 최소 신장 트리에서는 무방향 그래프여야 한다, 즉 서로 연결시켜줘야 함)
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		int minTotalWeight = prim(1);	// 프림 알고리즘을 이용하여 최소 신장 트리 비용 뽑아냄
		// 최소 신장 트리 비용 (즉, 모든 컴퓨터를 연결하는데 필요한 최소 비용 출력)
		System.out.println(minTotalWeight);	

	}
	
	// 모든 컴퓨터를 연결하는데 필요한 최소 비용을 계산해주는 메서드 (최소신장트리 비용 -> 프림 알고리즘)
	public static int prim(int start) {
		// 프림 알고리즘을 사용하기 위해 우선순위 큐 선언 및 생성
		// 연결된 간선들의 가중치 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));	// 시작정점부터 시작하기 위해 우선순위 큐에 시작정점 저장
		int totalWeigth = 0;	// 모든 컴퓨터를 연결할때 비용
		
		// 프림 알고리즘 이용
		while(!pq.isEmpty()) {
			// 현재 간선 정보 뽑아냄
			Edge now = pq.poll();
			int to = now.toVertex;
			int weight = now.weight;
			
			// 해당 정점이 방문되지 않은 경우
			if(!visited[to]) {
				visited[to] = true;	// 해당 정점 방문처리
				totalWeigth += weight;	// 해당 정점에 연결된 간선의 가중치 누적
				
				// 해당 정점과 연결된 간선들 탐색 (연결된 컴퓨터들 탐색하기)
				for(Edge next: graph.get(to)) {	
					// 해당 정점과 연결된 정점이 방문처리 안된 경우
					if(!visited[next.toVertex]) {
						pq.add(next);	// 연결된 정점 우선순위 큐에 저장 (다음 정보 탐색하기 위해)
					}
				}
			}
		}
		
		// 모든 컴퓨터를 연결할 때 비용 (최소 비용) 반환
		return totalWeigth;
	}

}
