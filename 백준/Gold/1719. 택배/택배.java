import java.util.*;
import java.io.*;

public class Main {
	
	// 간선의 정보를 담고 있는 내부 클래스
	static class Edge {
		int toVertex; // 도착 정점 (도착지 집하장)
		int weight; // 가중치 (걸리는 시간)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N; // 집하장의 개수
	static int M; // 경로의 개수
	static ArrayList<ArrayList<Edge>> graph; // 각 정점(집하장)마다 연결된 간선정보를 나타내기 위한 그래프 (인접 리스트)
	static final int INF = Integer.MAX_VALUE; // 다익스트라 알고리즘에서 사용할 무한대를 나타내는 상수
	static int[] dist; // 최단 거리를 저장하는 배열
	static boolean[] visited; // 각 정점(집하장)마다 방문 여부를 나타내는 배열
	static int[][] pathMap; // 경로표를 저장하는 2차원 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>(); // 그래프 생성
		dist = new int[N+1]; // [1] ~ [N]
		visited = new boolean[N+1];
		pathMap = new int[N+1][N+1]; // [1][1] ~ [N][N]
		
		// 각 정점마다 간선을 연결하기 위한 인접 리스트 생성
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken()); // 시작 정점 (시작 집하장)
			int toVertex = Integer.parseInt(st.nextToken()); // 도착 정점 (도착 집하장)
			int weight = Integer.parseInt(st.nextToken()); // 가중치 (이동하는데 걸리는 시간)
			
			// 양방향 간선 연결
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		// 1번 정점(집하장)부터 각 정점까지의 최단거리 계산하기 위해 다익스트라 메서드 실행
		for (int i=1; i<=N; i++) {
			dijkstra(i);
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 1번 정점부터 N번 정점까지 경로표 출력
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				if (i == j) {
					sb.append("-").append(" ");
				}
				else {
					sb.append(pathMap[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 시작정점(집하장)에서부터 각 정점까지의 최단거리를 구하는 다익스트라 메서드
	public static void dijkstra(int vertex) {
		// 다익스트라 알고리즘을 이용하기 위해 우선순위 큐 선언 및 생성
		// 간선의 가중치를 기준으로 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(vertex, 0)); // 해당 정점의 간선 정보 저장
		
		Arrays.fill(dist, INF); // 각 정점들의 최단거리 무한대로 초기화
		Arrays.fill(visited, false); // 각 정점들의 방문 여부 초기화
		
		dist[vertex] = 0; // 해당 정점 최단거리 0으로 초기화
		
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 해당 간선 정보 뽑아냄
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			// 해당 정점(집하장)을 방문하지 않은 경우
			if (!visited[nowVertex]) {
				visited[nowVertex] = true; // 방문 처리
				
				// 해당 정점(집하장)과 연결된 간선들 탐색
				for (Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight; // 탐색한 정점까지 이동하는데 걸리는 비용 계산
					
					// 해당 비용이 탐색한 정점의 최단거리보다 작은 경우
					if (cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost; // 최단거리 갱신
						pq.add(new Edge(next.toVertex, cost)); // 탐색한 정점 및 간선정보 저장
						// 경로표 갱신 (탐색한 정점에서 시작 정점까지 갈때 먼저 들리는 경로 우선순위 큐에 뽑아낸 정점(집하장)으로 갱신
						pathMap[next.toVertex][vertex] = nowVertex;
					}
				}
			}
		}
	}

}