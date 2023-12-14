import java.util.*;
import java.io.*;

public class Main {
	
	// 간선 정보를 저장하는 내부 클래스
	static class Edge {
		int toVertex;	// 도착 노드
		int weight;	// 가중치 (거리)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 노드의 개수
	static int M;	// 거리를 알고 싶은 노드 쌍의 개수
	static ArrayList<ArrayList<Edge>> graph;	// 각 노드에 연결된 간선 정보를 저장하는 그래프
	static boolean[] visited;	// 각 노드당 방문 여부를 체크하는 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());	// 그래프 초기화 작업
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 양방향 간선 연결
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());	// 시작노드 입력
			int end = Integer.parseInt(st.nextToken());		// 도착 노드 입력
			
			visited = new boolean[N+1];	// [1] ~ [N], 방문배열 초기화
			int distance = bfs(start, end);	// 너비우선탐색을 실시하여 거리 계산
			sb.append(distance).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 시작노드와 도착노드 사이의 거리를 구하기 위한 너비우선탐색 메서드
	public static int bfs(int start, int end) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Edge> queue = new LinkedList<>();
		queue.add(new Edge(start, 0));	// 시작노드의 간선 정보 큐에 저장
		visited[start] = true;	// 시작 노드 방문 처리
		
		// 너비우선탐색 실시
		while(!queue.isEmpty()) {
			// 현재 간선 정보 및 현재까지의 거리 뽑아내기
			Edge now = queue.poll();
			int nowVertex = now.toVertex;
			int nowTotalDistance = now.weight;	// 현재까지의 거리
			
			// 현재 노드가 도착지 노드인 경우 (즉, 목표 노드에 도달한 경우)
			if(nowVertex == end) {
				return nowTotalDistance;	// 현재까지의 거리 반환
			}
			
			// 현재 노드에 연결된 간선들 탐색
			for(Edge next: graph.get(nowVertex)) {
				// 탐색한 노드가 방문처리 안된 경우
				if(!visited[next.toVertex]) {
					visited[next.toVertex] = true;	// 탐색한 노드 방문처리
					// 큐에 해당 노드 및 현재까지의 거리 저장(간선정보)
					queue.add(new Edge(next.toVertex, nowTotalDistance + next.weight));
				}
			}
		}
		
		// 목표 노드에 도달하지 못한 경우
		return -1;	// -1 반환
	}

}
