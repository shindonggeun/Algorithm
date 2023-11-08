import java.util.*;
import java.io.*;

public class Main {
	
	// 간선의 정보를 저장해주는 내부 클래스
	static class Edge {
		int toVertex;	// 진입 정점
		int weight;	// 가중치
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int V;	// 정점의 개수
	static int E;	// 간선의 개수
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();	// 각 정점에 연결된 간선정보를 저장해주는 그래프 (인접 리스트 이용)
	static boolean[] visited;	// 각 정점들 방문 여부를 판단해주는 방문배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// 그래프에서 각 정점 생성해주는 과정
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<>());
		}
		
		visited = new boolean[V+1];	// [1] ~ [V]
		
		// 경로 입력하는 과정
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());	// 시작 정점 입력
			int toVertex = Integer.parseInt(st.nextToken());	// 끝 정점 (도착 정점) 입력
			int weight = Integer.parseInt(st.nextToken());	// 가중치 입력
			
			// 양방향 간선 연결 (최소 신장 트리 -> 프림 알고리즘을 사용하기 위해서는 무방향이여야 함)
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		int minTotalWeight = prim(1);	// 프림 알고리즘을 이용하여 최소 신장 트리의 가중치 구하기
		System.out.println(minTotalWeight);	// 최소 신장 트리의 가중치 출력
	}
	
	// 최소 스패닝 트리 (최소 신장 트리)를 구하기 위한 프림 알고리즘 메서드
	public static int prim(int start) {
		// 프림 알고리즘을 이용하기 위해 우선순위 큐 이용
		// 각 간선의 가중치 오름차순에 따른 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));	// 시작 정점 우선순위 큐에 저장
		int totalWeight = 0;	// 모든 정점들을 연결할 때 가중치의 합
		
		// 프림 알고리즘 이용
		while(!pq.isEmpty()) {
			// 현재 간선 정보 뽑아내기
			Edge now = pq.poll();
			int to = now.toVertex;
			int weight = now.weight;
			
			// 해당 정점이 방문되지 않은 경우
			if(!visited[to]) {
				visited[to] = true;	// 해당 정점 방문 처리
				totalWeight += weight;	// 해당 간선의 가중치 더해주기
				// 해당 정점에 연결된 간선들 탐색
				for(Edge next: graph.get(to)) {
					// 연결된 간선중 도착지(정점)이 방문되지 않은 경우
					if(!visited[next.toVertex]) {
						pq.add(next);	// 해당 간선정보 우선순위 큐에 저장
					}
				}
			}
		}
		
		// 모든 정점들을 연결할 때 가중치의 합 반환
		return totalWeight;
	}

}
