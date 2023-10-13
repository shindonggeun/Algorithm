import java.util.*;
import java.io.*;

public class Main {

	// 해당 도시의 간선정보를 담은 내부 클래스
	static class Edge {
		int toVertex;	// 간선의 도착 도시 번호
		int weight;	// 간선의 가중치 (도시까지 가는데 드는 비용)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 도시의 개수
	static int M;	// 버스의 개수
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();	// 그래프 표현을 위한 리스트
	static int[] dist;	// 출발 도시로부터의 최소 비용을 저장하는 배열
	static boolean[] visited;	// 방문 배열
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dist = new int[N+1];	// 출발도시로부터의 ([1] ~ [N]번 도시) 최소 비용
		visited = new boolean[N+1];
		Arrays.fill(dist, INF);	// 모든 도시의 최소 비용 최대값으로 초기화
		
		// 그래프에서 정점들 생성
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 단방향 간선 연결 (출발지 -> 도착지)
			graph.get(fromVertex).add(new Edge(toVertex, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start, end);	// 다익스트라 알고리즘을 통해 최소비용 계산
		System.out.println(dist[end]);
	}
	
	// 다익스트라 알고리즘을 이용해 최소 비용 계산해주는 ㅔㅁ서드
	public static void dijkstra(int start, int end) {
		// 다익스트라 알고리즘에서 사용될 우선순위 큐 (가중치(도시간 이동하는데 드는 비용) 오름차순 정렬)
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));	// 시작 도시를 우선순위 큐에 저장
		dist[start] = 0;	// 시작 도시의 최소 비용 0으로 설정
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();	// 현재 도시 뽑아내기
			int to = now.toVertex;	// 현재 도시의 번호
			
			// 해당 도시가 방문처리 안된 경우
			if(!visited[to]) {
				visited[to] = true;	// 해당 도시 방문 처리
				
				// 해당 도시에 연결된 간선들 탐색
				for(Edge edge: graph.get(to)) {
					// 해당 도시에 연결된 간선들 중 도착지가 방문되지 않은 경우
					if(!visited[edge.toVertex]) {
						int cost = dist[to] + edge.weight;	// 현재 도시를 통해 도착지까지 가는 비용 계산
						// 현재 계산된 비용이 기존의 비용보다 작은 경우
						if(cost < dist[edge.toVertex]) {
							dist[edge.toVertex] = cost;	// 현재 도시(도착지)의 최소 비용 갱신
							// 우선순위큐에 해당 도착지 번호와 도착지까지 가는데 걸리는 비용 저장
							pq.add(new Edge(edge.toVertex, dist[edge.toVertex]));	
						}
					}
				}
			}
			
		}
	}

}
