import java.util.*;
import java.io.*;

public class Main {
	
	// 간선의 정보를 담고 있는 내부 클래스
	static class Edge {
		int toVertex; // 도착지
		int weight; // 가중치 (여물)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N; // 헛간의 개수 (정점의 개수)
	static int M; // 길의 개수 (간선의 개수)
	static ArrayList<ArrayList<Edge>> graph; // 각 정점당 연결된 간선들을 담고 있는 그래프 (인접 리스트 이용)
	static boolean[] visited; // 각 정점당 방문 여부를 나타내는 배열
	static int[] dist; // 각 정점당 최단 거리를 저장하는 배열
	static final int INF = Integer.MAX_VALUE; // 다익스트라 알고리즘에서 사용하는 무한대를 나타내는 상수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>(); // 그래프 초기화
		visited = new boolean[N+1]; // [1] ~ [N]
		dist = new int[N+1]; // [1] ~ [N]
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>()); // 각 정점당 리스트 초기화
			dist[i] = INF; // 각 정점당 최단 거리 무한대로 초기화
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken()); // 시작 정점 (시작 헛간)
			int toVertex = Integer.parseInt(st.nextToken()); // 도착 정점 (도착 헛간)
			int weight = Integer.parseInt(st.nextToken()); // 가중치 (여물)
			
			// 양방향 간선 연결
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		dijkstra(1); // 시작지(1번 정점)에서부터 도착지까지의 최단거리를 구하기 위해 다익스트라 메서드 실행
		
		// 도착지까지의 최단 거리 (최소 여물) 출력
		System.out.println(dist[N]);

	}
	
	// 시작지에서 도착지까지의 최단 거리 (최소 여물)을 구하는 메서드 (다익스트라)
	public static void dijkstra(int start) {
		// 다익스트라 알고리즘을 이용하기 위해 우선순위 큐 선언 및 생성
		// 간선들의 가중치를 기준으로 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0)); // 시작 정점 우선순위 큐에 저장
		dist[start] = 0; // 시작 정점의 최단 거리 0으로 초기화
		
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 현재 간선 정보 뽑아냄
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			// 현재 정점 (헛간)이 방문처리 되지 않은 경우
			if (!visited[nowVertex]) {
				visited[nowVertex] = true; // 해당 정점 방문 처리
				
				// 해당 정점과 연결된 간선들 탐색
				for (Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight; // 탐색한 정점까지의 거리 계산
					// 탐색한 정점의 거리가 탐색한 정점의 최단거리보다 작은 경우
					if (cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost; // 탐색한 정점의 최단 거리 갱신
						pq.add(new Edge(next.toVertex, cost)); // 우선순위 큐에 탐색한 정점 및 거리 (간선 정보) 저장
					}
				}
			}
		}
	}

}