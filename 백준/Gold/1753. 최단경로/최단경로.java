import java.util.*;
import java.io.*;

public class Main {
	
	// 간선의 정보를 담고있는 내부 클래스
	static class Edge {
		int toVertex;
		int weight;
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int V;	// 정점의 개수
	static int E;	// 간선의 개수
	static int K;	// 시작 정점
	static ArrayList<ArrayList<Edge>> graph;	// 각 정점에서 연결된 간선들을 저장하는 그래프
	static int[] dist;	// 시작 정점에서 각 정점들의 최단거리를 저장하는 배열
	static boolean[] visited;	// 각 정점들의 방문 여부를 체크하는 배열
	static final int INF = Integer.MAX_VALUE;	// 무한대를 나타내는 상수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<>();
		dist = new int[V+1];
		visited = new boolean[V+1];	//[1] ~ [V]
		for (int i=0; i<=V; i++) {
			graph.add(new ArrayList<>());
			dist[i] = INF;
		}
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 방향그래프이므로 해당 간선 연결
			graph.get(fromVertex).add(new Edge(toVertex, weight));
		}
		
		dijkstra(K);
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=V; i++) {
			if (dist[i] == INF) {
				sb.append("INF").append("\n");
			} 
			else {
				sb.append(dist[i]).append("\n");
			}
		}
		System.out.print(sb);
		
	}
	
	// 시작 정점에서 각 정점까지의 최단 거리를 저장하는 메서드 (다익스트라 알고리즘)
	public static void dijkstra(int start) {
		// 다익스트라 알고리즘 이용하기 위해 우선순위 큐 선언 및 생성
		// 간선의 가중치가 낮은 순서대로 처리 (오름차순 정렬)
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));	// 시작 정점 우선순위 큐에 저장
		dist[start] = 0;	// 시작 정점의 최단 거리 0으로 설정
		
		// 다익스트라 알고리즘 이용
		while (!pq.isEmpty()) {
			// 현재 간선 정보 뽑아내기
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			// 해당 정점이 방문처리 안된 경우
			if (!visited[nowVertex]) {
				visited[nowVertex] = true;
				
				// 현재 정점과 연결된 모든 간선들 탐색
				for (Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight;	// 다음 정점까지의 총 가중치 계산
					
					// 다음 정점까지의 드는 거리 비용이 기존 최단 거리보다 작은 경우
					if (cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;	// 탐색한 정점의 최단 거리 갱신
						// 해당 정점 및 최단거리 정보 우선순위 큐에 저장
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));
					}
				}
			}
		}
	}

}
