import java.util.*;
import java.io.*;

public class Main {

	// 해당 정점에 연결된 간선 정보를 저장하는 내부 클래스
	static class Edge {
		int toVertex;	// 도착지 정점
		int weight;	// 가중치
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int V;	// 정점의 개수
	static int E;	// 간선의 개수
	static int K;	// 시작 정점 번호
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();	// 각 정점에 연결된 정정 및 간선정보를 저장할 리스트 (그래프)
	static int[] dist;	// 각 정점들의 최단경로를 저장할 배열
	static final int INF = Integer.MAX_VALUE;	// 다익스트라 알고리즘에서 최소 비용 초기화시 초기에 사용할 무한대 값
	static boolean[] visited;	// 각 정점 방문여부를 체크할 방문배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		dist = new int[V+1];	// [1] ~ [V]까지의 각 정점들의 최단 경로값
		visited = new boolean[V+1];
		// 그래프간 정점들 생성
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<>());
			dist[i] = INF;
		}
		
		// 간선 연결 작업 과정
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());	// 시작지
			int toVertex = Integer.parseInt(st.nextToken());	// 도착지
			int weight = Integer.parseInt(st.nextToken());	// 가중치
			
			// 단방향 간선 연결 (출발지 -> 도착지)
			graph.get(fromVertex).add(new Edge(toVertex, weight));
		}
		
		dijkstra(K);	// 다익스트라 메서드 호출
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=V; i++) {
			if(dist[i] == INF) {
				sb.append("INF").append("\n");
				continue;
			}
			sb.append(dist[i]).append("\n");
		}
		System.out.print(sb);
		
	}
	
	// 시작점에서 다른 모든 정점으로의 최단 경로를 구해줄 다익스트라 알고리즘 메서드
	public static void dijkstra(int start) {
		// 다익스트라 알고리즘에서 사용할 우선순위 큐 선언 및 초기화
		// 가중치 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		dist[start] = 0;	// 시작점 최소비용 0으로 초기화
		pq.add(new Edge(start, 0));	// 우선순위 큐에 시작점 정보 저장
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();	
			int to = now.toVertex;
			int weight = now.weight;
			
			// 해당 정점이 방문 안된 경우
			if(!visited[to]) {
				visited[to] = true;	// 해당 정점 방문 처리
				
				// 해당 정점에 연결된 간선 정보 뽑아내는 과정
				for(Edge next: graph.get(to)) {
					int cost = weight + next.weight;	// 다음 위치까지의 비용 계산하기
					// 계산한 현재 비용이 다음 위치(다음 정점)의 최소 비용보다 작은 경우
					if(cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;	// 다음 정점의 최소 비용 갱신해주기 
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));	// 우선순위 큐에 다음 정점 정보 저장
					}
				}
			}
			
			
		}
	}
	

}
