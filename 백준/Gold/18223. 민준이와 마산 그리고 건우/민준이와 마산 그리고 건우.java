import java.util.*;
import java.io.*;

public class Main {
	
	// 간선의 정보를 담은 내부 클래스
	static class Edge {
		int toVertex;	// 연결된 목적지 정점
		int weight;	// 해당 간선의 가중치
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int V;	// 정점의 개수
	static int E;	// 간선의 개수
	static int P;	// 건우가 위치한 정점 P
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();	// 각 정점에서 연결된 간선들을 저장하는 그래프
	static int[] dist;	// 각 정점까지의 최단 거리를 저장하는 배열
	static final int INF = Integer.MAX_VALUE;	// 다익스트라 알고리즘에서 사용하기 위해 거리를 무한대로 초기화하기 위한 상수
	static boolean[] visited;	// 각 정점의 방문 여부를 확인하는 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		visited = new boolean[V+1];	// [1] ~ [V]
		dist = new int[V+1];
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<>());	
			dist[i] = INF;
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		// 1. 출발지에서 도착지(V => 마산)까지 최단거리 구하기
		dijkstra(1);	// 1번정점에서 해당 정점들 사이에 최단거리 구하기 (다익스트라 메서드 호출)
		
		int distanceToV = dist[V];	// 1번정점(출발지)에서부터 V정점(마산)까지의 최단거리
		int tempDistance1 = dist[P];	// 1번정점(출발지)에서부터 P정점(건우)까지의 최단거리
		
		// 2. 중간지점에서(P => 건우) 도착지까지 최단거리 구하기
		Arrays.fill(dist, INF);		// 각 정점의 최단거리를 저장할 배열 다시 초기화
		Arrays.fill(visited, false);	// 각 정점의 방문 여부 다시 초기화
		
		dijkstra(P);	// P정점에서부터 해당 정점들 사이에 최단거리 구하기 (다익스트라 메서드 호출)
		int tempDistance2 = dist[V];	// P정점(건우)에서부터 V정점(마산)까지의 최단거리
		
		// 최단 경로가 건우를 도울 수 있는 경로인 경우
		if(distanceToV == tempDistance1 + tempDistance2) {
			System.out.println("SAVE HIM");
        } 
		else {
            System.out.println("GOOD BYE");
        }
	}
	
	public static void dijkstra(int vertex) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(vertex, 0));
		dist[vertex] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			// 해당 정점이 방문 안된 경우
			if(!visited[nowVertex]) {
				visited[nowVertex] = true; // 해당 정점 방문처리
				
				for(Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight;
					
					// 계산한 현재 비용이 연결된 다음 정점의 최소 비용보다 작은 경우
					if(cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));
					}
				}
			}
		}
	}

}
