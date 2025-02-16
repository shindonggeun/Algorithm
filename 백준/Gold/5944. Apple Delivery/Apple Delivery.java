import java.util.*;
import java.io.*;

public class Main {
	
	// 간선의 정보를 담고 있는 내부 클래스
	static class Edge {
		int toVertex;
		int weight;
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int C; // 간선의 개수
	static int P; // 정점(목초지)의 개수
	static int PB; // 시작 정점
	static int PA1; // 첫 번째 사과를 전달할 정점
	static int PA2; // 두 번째 사과를 전달할 정점
	static int[] distFromPB; // PB에서 각 정점까지의 최단 거리 배열
	static int[] distFromPA1; // PA1에서 각 정점까지의 최단 거리 배열
	static int[] distFromPA2; // PA2에서 각 정점까지의 최단 거리 배열
	static final int INF = Integer.MAX_VALUE; // 무한대를 나타내는 상수
	static ArrayList<ArrayList<Edge>> graph; // 인접 리스트를 활용한 그래프 저장 구조 (각 정점에서 연결된 간선들을 저장할 그래프)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		PB = Integer.parseInt(st.nextToken());
		PA1 = Integer.parseInt(st.nextToken());
		PA2 = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>(); // 그래프 초기화
		
		// 1. 각 정점마다 인접 리스트 초기화 하는 과정 (그래프 초기화 과정)
		for (int i=0; i<=P; i++) {
			graph.add(new ArrayList<>()); // 각 정점마다 연결할 리스트 초기화
		}
		
		// 2. 간선 입력 및 정점끼리 연결하는 과정
		for (int i=0; i<C; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken()); // 시작 정점 입력
			int toVertex = Integer.parseInt(st.nextToken()); // 도착 정점 입력
			int weight = Integer.parseInt(st.nextToken()); // 거리 (가중치) 입력
			
			// 양방향 간선 연결
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		// 3. 다익스트라 알고리즘을 이용하여 최단 거리 계산하는 과정
		distFromPB = dijkstra(PB); // PB에서 모든 정점까지의 최단 거리 계산
		distFromPA1	= dijkstra(PA1); // PA1에서 모든 정점까지의 최단 거리 계산
		distFromPA2 = dijkstra(PA2); // PA2에서 모든 정점까지의 최단 거리 계산
		
		// 4. 두 가지 경로 중 최소 거리 계산
		// 1) PB -> PA1 -> PA2
		// 2) PB -> PA2 -> PA1
		long path1 = (long) distFromPB[PA1] + distFromPA1[PA2]; // 첫 번째 경로의 총 거리
		long path2 = (long) distFromPB[PA2] + distFromPA2[PA1]; // 두 번째 경로의 총 거리
		
		System.out.println(Math.min(path1, path2)); // 두 경로 중 최소 거리 출력
	}
	
	// 다익스트라 알고리즘을 이용하여 최단 거리 계산하는 메서드
	public static int[] dijkstra(int start) {
		// 우선순위 큐 선언 및 생성
		// 최소 비용 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0)); // 시작 정점을 우선순위 큐에 추가
		
		int[] dist = new int[P+1]; // 최단 거리 배열 선언 및 생성 [1] ~ [P]
		boolean[] visited = new boolean[P+1]; // 각 정점당 방문 여부를 나타내는 배열
		
		Arrays.fill(dist, INF); // 최단 거리 배열 각 초기 값 무한대로 초기화
		dist[start] = 0; // 시작 정점의 최단 거리 0으로 초기화
		
		// 다익스트라 알고리즘 실행
		while (!pq.isEmpty()) {
			Edge now = pq.poll(); // 우선순위 큐에 저장된 현재 가장 짧은 거리의 정점 뽑아냄
			int nowVertex = now.toVertex; // 현재 정점
			int nowWeight = now.weight; // 현재 정점까지의 거리
			
			// 현재 정점을 방문한 적이 없는 경우
			if (!visited[nowVertex]) {
				visited[nowVertex] = true; // 현재 정점 방문 처리
				
				// 현재 정점과 연결된 모든 간선들 확인하는 과정
				for (Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight; // 현재까지의 거리 + 해당 간선의 거리
					
					// 탐색한 정점의 비용이 탐색한 정점의 최단 거리보다 작은 경우
					if (cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost; // 최단 거리 갱신 
						pq.add(new Edge(next.toVertex, cost)); // 우선순위 큐에 탐색한 간선 정보 추가
					}
				}
			}
		}
		
		return dist; // 최단 거리 배열 반환
	}

}