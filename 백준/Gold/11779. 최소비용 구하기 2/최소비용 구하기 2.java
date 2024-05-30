import java.util.*;
import java.io.*;

public class Main {
	
	// 간선의 정보를 담고 있는 내부 클래스
	static class Edge {
		int toVertex;	// 도착 도시
		int weight;	// 가중치 (비용)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 도시의 개수 (정점의 개수)
	static int M;	// 버스의 개수 (간선의 개수)
	static int[] dist;	// 시작 도시에서 출발하여 각 도시들까지의 최소 거리를 저장할 배열
	static final int INF = Integer.MAX_VALUE;	// 다익스트라 알고리즘에서 사용할 무한대를 나타내는 상수
	static boolean[] visited;	// 각 도시들의 방문 여부를 나타내는 배열
	static ArrayList<ArrayList<Edge>> graph;	// 각 정점마다 간선이 연결된 걸 표현한 그래프 (인접 리스트) 
	static int[] previousCity;	// 출발 도시에서 도착 도시까지 갈 때 각 도시의 이전 도시의 번호를 저장할 배열
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N+1];	// [1] ~ [N]
		visited = new boolean[N+1];
		graph = new ArrayList<>();
		previousCity = new int[N+1];
		
		for (int i=0; i<=N; i++) {
			dist[i] = INF;	// 시작 정점에서부터 각 정점까지의 최단 거리 최대값으로 초기화
			graph.add(new ArrayList<>());	// 각 정점 인접 리스트 초기화
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());	// 시작 도시 (시작 정점)
			int toVertex = Integer.parseInt(st.nextToken());	// 도착 도시 (도착 정점)
			int weight = Integer.parseInt(st.nextToken());	// 버스 비용 (가중치)
			
			// 단방향 간선 연결
			graph.get(fromVertex).add(new Edge(toVertex, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());	// 출발 도시
		int end = Integer.parseInt(st.nextToken());	// 도착 도시
		
		dijkstra(start);	// 출발 도시에서부터 출발하여 각 도시까지의 최소 비용을 구하기 위한 다익스트라 메서드 호출
		backTrackingCity(start, end);	// 출발도시에서 도착도시까지의 최소 비용을 갖는 경로를 찾는 메서드 호출
		System.out.println(dist[end]);
		System.out.print(sb);
	}
	
	// 출발도시에서부터 도착도시까지 최소 비용을 계산해줄 다익스트라 메서드
	public static void dijkstra(int vertex) {
		// 다익스트라 알고리즘을 이용하기 위해 우선순위 큐 선언 및 생성
		// 각 간선들 가중치를 기준으로 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(vertex, 0));	// 우선순위큐에 출발 도시 정보 저장
		dist[vertex] = 0;	// 출발 도시 최소 비용 0으로 초기화
		
		while (!pq.isEmpty()) {
			// 우선순위큐에서 해당 도시 정보 뽑아냄
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			// 해당 도시 방문하지 않은 경우
			if (!visited[nowVertex]) {
				visited[nowVertex] = true;	// 해당 도시 방문 처리
				
				// 해당 도시와 연결된 도로들(간선들) 탐색
				for (Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight;	// 해당 도시와 연결된 도시로 가는데 드는 비용 계산
					
					// 해당 비용이 해당 도시의 최소 비용보다 작은 경우
					if (cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;	// 최소 비용 갱신
						// 우선순위 큐에 해당 도시 정보 저장
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));
						previousCity[next.toVertex] = nowVertex;	// 역추적을 위해 해당 도시에 이전 도시(정점) 번호를 저장
					}
				}
			}
		}
	}
	
	// 시작도시에서 도착도시까지 최소 비용을 갖는 경로를 찾는 메서드
	public static void backTrackingCity(int start, int end) {
		// 시작 도시에서 도착 도시까지 경로 역추적을 위한 스택 선언 및 호출 
		Stack<Integer> pathStack = new Stack<>();
		int nowCity = end;	// 도착 도시를 현재 도시로 설정 (역으로 추적해야하므로)
		
		// 현재도시와 시작 도시가 같지 않을 때까지 반복
		while (nowCity != start) {
			pathStack.push(nowCity);	// 스택에 현재 도시 번호 저장
			nowCity = previousCity[nowCity];	// 현재 도시의 이전 도시 번호를 현재 도시로 갱신
		}
		
		pathStack.push(start);	// 스택에 출발 도시 번호 저장
		
		sb.append(pathStack.size()).append("\n");
		
		while (!pathStack.isEmpty()) {
			sb.append(pathStack.pop()).append(" ");
		}
	}

}