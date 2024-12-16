import java.util.*;
import java.io.*;

public class Main {

	// 간선 정보들을 담고있는 내부 클래스
	static class Edge {
		int toVertex; // 도착 정점 (도착지)
		int weight; // 가중치 (버스 비용)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N; // 도시의 개수
	static int M; // 버스의 개수
	static int[] dist; // 출발 도시에서 각 도시(정점)까지의 최단 거리를 나타내는 배열
	static final int INF = Integer.MAX_VALUE; // 다익스트라 알고리즘에서 사용할 무한대를 나타내는 상수
	static boolean[] visited; // 각 도시마다 방문 여부를 나타내는 배열
	static ArrayList<ArrayList<Edge>> graph; // 각 도시마다 연결된 도시 정보를 담고있는 그래프 (인접 리스트)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N+1]; // [1] ~ [N]
		visited = new boolean[N+1]; // [1] ~ [N]
		
		graph = new ArrayList<>(); // 그래프 초기화
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>()); // 각 정점(도시)마다 인접 리스트 초기화
			dist[i] = INF; // 해당 도시 최단 거리 무한대로 초기화
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken()); // 출발 도시
			int toVertex = Integer.parseInt(st.nextToken()); // 도착 도시
			int weight = Integer.parseInt(st.nextToken()); // 버스 비용
			
			// 출발 도시에서부터 도착 도시까지 연결된 정보 저장 (단방향 간선)
			graph.get(fromVertex).add(new Edge(toVertex, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()); // 출발 도시
		int end = Integer.parseInt(st.nextToken()); // 도착 도시
		
		dijkstra(start); // 출발 도시에서부터 도착 도시까지 드는데 최소 비용을 구하기 위한 메서드 호출
		
		System.out.println(dist[end]);
	}
	
	// 출발 도시에서부터 도착 도시까지 드는데 최소 비용을 구하기 위한 메서드 (다익스트라 알고리즘)
	public static void dijkstra(int start) {
		// 다익스트라 알고리즘을 이용하기위해 우선순위 큐 선언 및 생성
		// 가중치를 기준으로 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0)); // 시작 도시 정보 우선운위 큐에 저장
		dist[start] = 0; // 시작 도시의 최단 거리 0으로 초기화
		
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 현재 도시 정보 뽑아냄 (간선 정보)
			Edge now = pq.poll();
			int nowVertex = now.toVertex; // 현재 도시 번호
			int nowWeight = now.weight; // 현재까지의 버스 비용
			
			// 현재 도시가 방문되지 않은 경우
			if (!visited[nowVertex]) {
				visited[nowVertex] = true; // 현재 도시 방문 처리
				
				// 현재 도시와 연결된 도시들 탐색
				for (Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight; // 다음 도시까지의 버스 비용 계산
					// 해당 비용이 탐색한 도시의 최단 거리(최소 비용)보다 작은 경우
					if (cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost; // 탐색한 도시의 최단 거리(최소 비용) 갱신
						pq.add(new Edge(next.toVertex, cost)); // 우선순위 큐에 탐색한 도시 정보 저장
					}
				}
			}
		}
	}

}