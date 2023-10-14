import java.util.*;
import java.io.*;

public class Main {
	
	// 해당 도시의 간선정보를 담은 내부 클래스
	static class Edge {
		int toVertex;	// 간선의 도착 도시 번호 (도착지)
		int weight;	// 간선의 가중치 (해당 도시까지 가는데 드는 비용)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 도시 개수
	static int M;	// 버스의 개수
	// 각 도시(정점)의 연결된 도시 및 간선 정보를 저장할 리스트 (그래프로 표현할 리스트)
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	static int[] dist;	// 각 도시들의 최소 비용을 저장할 배열
	static final int INF = Integer.MAX_VALUE;	// 다익스트라 알고리즘에서 최소 비용 초기화시 초기에 사용할 무한대 값 
	static boolean[] visited;	// 도시의 방문 여부를 판단할 방문배열
	static int[] previousCity;	// 각 도시에서 이전 도시의 번호를 저장할 배열
	static StringBuilder sb = new StringBuilder();
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N+1];	// [1] ~ [N]
		visited = new boolean[N+1];
		previousCity = new int[N+1];
		// 해당 도시(정점 번호) 생성하기
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
			dist[i] = INF;	// 각 정점(도시번호)의 최소 비용 무한대값으로 초기화
		}
		
		// 각 도시의 간선 정보들 입력하기
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 단방향 간선 연결 (시작지 -> 도착지)
			graph.get(fromVertex).add(new Edge(toVertex, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());	// 시작지 정보 입력
		int end = Integer.parseInt(st.nextToken());	// 도착지 정보 입력
		
		dijkstra(start);	// 다익스트라 알고리즘 메서드 호출
		System.out.println(dist[end]);	// 시작지에서 도착지까지의 최소 비용 출력
		backTrackingCity(start, end);	// 시작지에서 도착지까지의 최소 비용을 갖는 경로를 찾는 메서드 호출
		System.out.println(sb);
	}
	
	// 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 계산해줄 다익스트라 알고리즘 메서드
	public static void dijkstra(int start) {
		// 다익스트라 알고리즘에서 사용할 우선순위 큐 선언 및 초기화
		// 가중치 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		dist[start] = 0;	// 시작정점의 최소 비용 0으로 초기화
		pq.add(new Edge(start, 0));	// 우선순위 큐에 시작 정점 정보 저장
		
		while(!pq.isEmpty()) {
			// 현재 도시(정점) 정보 뽑아내기
			Edge now = pq.poll();	
			int to = now.toVertex;
			int weight = now.weight;
			
			// 해당 도시가 방문 안된 경우
			if(!visited[to]) {
				visited[to] = true;	// 해당 도시 방문 처리
				
				// 해당 도시에 연결된 도시들의 간선정보 확힌하는 과정
				for(Edge next: graph.get(to)) {
					int cost = weight + next.weight;	// 해당 도시와 연결된 도시로 가는데 드는 비용 계산
					// 해당 비용이 다음 도시의 최소 비용보다 작은 경우
					if(cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;	// 다음 도시의 최소 비용 갱신
						previousCity[next.toVertex] = to;	// 역추적을 위해 이전 도시를 저장
						// 우선순위 큐에 다음 도시의 정보 저장
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));	
					}
				}
			}
		}
	}
	
	// 시작지에서 도착지까지 최소 비용을 갖는 경로를 찾는 메서드
	public static void backTrackingCity(int start, int end) {
		// 해당 도착지의 경로 역추적을 위한 스택 선언 및 호출 (도착지부터 역으로 올라가기 때문)
		Stack<Integer> pathStack = new Stack<>();	
		int nowCity = end;	// 도착지를 현재 도시로
		// 현재 도시와 시작 도시가 같지 않을때까지 반복
		while(nowCity != start) {
			pathStack.push(nowCity);	// 현재 도시 스택에 저장
			nowCity = previousCity[nowCity];	// 현재 도시의 이전 도시 정보 현재 도시로 갱신
		}
		pathStack.push(start);	// 시작 도시 스택에 저장
		
		sb.append(pathStack.size()).append("\n");	// 최소 비용을 갖는 경로에 포함되어 있는 도시의 개수 출력
		// 스택이 빌때까지 반복 (출력부분)
		while(!pathStack.isEmpty()) {
			int stackCity = pathStack.pop();	// 스택에 뽑아낸 도시 번호
			sb.append(stackCity).append(" ");
		}
	}

}
