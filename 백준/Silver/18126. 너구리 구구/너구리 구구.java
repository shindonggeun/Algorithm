import java.util.*;
import java.io.*;

public class Main {
	
	// 간선 정보를 담은 내부 클래스
	static class Edge {
		int toVertex;	// 도착지
		int weight;	// 가중치 (길의 길이)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 방의 개수
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();	// 정점간 연결한 그래프를 표현한 리스트
	static boolean[] visited;	// 방문배열
	static long resultDistance;	// 구구가 집 입구에서 멜론 아이스크림을 숨기려고 하는 방까지 이동하는 거리 (최대 이동 거리)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		visited = new boolean[N+1];	// [1] ~ [N]
		resultDistance = Long.MIN_VALUE;	// 구구가 방까지 이동하는 거리 일단 최소값으로 설정
		
		// 그래프에서 정점 생성해주는 과정
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());	// A번 방
			int toVertex = Integer.parseInt(st.nextToken());	// B번 방
			int weight = Integer.parseInt(st.nextToken());	// 길의 길이 (가중치)
			
			// 양방향 간선 연결
			graph.get(fromVertex).add(new Edge(toVertex, weight));
			graph.get(toVertex).add(new Edge(fromVertex, weight));
		}
		
		dfs(1, 0);	// 1번방(입구)부터 탐색 시작하게끔 dfs 메서드 호출
		System.out.println(resultDistance);
	}
	
	// 깊이우선탐색 메서드
	public static void dfs(int vertex, long sumWeight) {
		// 현재까지의 이동거리가 구구가 방까지 이동한 거리(최대 거리)보다 큰 경우
		if(resultDistance < sumWeight) {
			resultDistance = sumWeight;	// 구구가 방까지 이동한 거리(최대 거리) 갱신
		}
		
		visited[vertex] = true;	// 해당 방 번호(정점) 방문처리
		
		// 해당 방과 연결된 방 탐색하는 과정
		for(Edge next: graph.get(vertex)) {
			// 해당 방(정점)과 연결된 방이 방문처리 안된 경우
			if(!visited[next.toVertex]) {
				visited[next.toVertex] = true;	// 연결된 방 방문처리
				dfs(next.toVertex, sumWeight+next.weight);	// 연결된 방(다음 방) 탐색 시작
			}
		}
	}

}
