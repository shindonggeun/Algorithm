import java.util.*;
import java.io.*;

public class Main {
	
	// 간선의 정보를 담고 있는 내부 클래스
	static class Edge {
		int toVertex;	// 도착 정점 (도착지)
		int weight;	// 가중치 (버스 비용)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 도시의 개수
	static int M;	// 버스의 개수
	static ArrayList<ArrayList<Edge>> graph;	// 각 정점마다 간선이 연결된걸 표현한 그래프 (인접 리스트 사용)
	static int[] dist;	// 시작지점부터 각 위치까지의 최소 비용을 저장하는 배열
	static final int INF = Integer.MAX_VALUE;	// 무한대를 나타내는 상수
	static boolean[] visited;	// 각 정점마다 방문여부를 표현할 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N+1];	// [1] ~ [N]
		graph = new ArrayList<>();	// 그래프 초기화
		visited = new boolean[N+1];
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());	// 각 정점 인접 리스트 초기화
			dist[i] = INF;	// 시작지점에서부터 각 정점까지의 최소 비용 무한대로 초기화
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 단방향 간선 열결
			graph.get(fromVertex).add(new Edge(toVertex, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());	// 출발 도시
		int end = Integer.parseInt(st.nextToken());	// 도착 도시
		
		dijkstra(start);	// 출발 도시에서부터 다익스트라 알고리즘 시작
		
		System.out.println(dist[end]);
	}
	
	// 출발 도시에서 도착 도시까지 드는 최소비용을 계산할 다익스트라 메서드
	public static void dijkstra(int vertex) {
		// 다익스트라를 이용하기 위해 우선순위 큐 선언 및 생성
		// 간선에 저장된 비용(가중치)를 기준으로 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(vertex, 0));	// 우선순위 큐에 해당 간선 정보 저장
		dist[vertex] = 0;	// 시작 정점의 최소 비용 0으로 초기화
		
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 현재 간선 정보 뽑아냄
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			// 현재 정점(도시)이 방문하지 않은 정점(도시)인 경우
			if (!visited[nowVertex]) {
				visited[nowVertex] = true;	// 현재 정점 방문 처리
				
				// 현재 정점에 연결된 간선들 탐색
				for (Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight;	// 다음 정점까지의 비용 계산
					
					// 탐색한 정점의 비용이 탐색한 정점의 최소 비용보다 작은 경우
					if (cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;	// 탐색한 정점의 최소 비용 갱신
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));	// 해당 간선 정보 우선순위 큐에 저장
					}
				}
			}
		}
	}

}