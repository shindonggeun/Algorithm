import java.util.*;
import java.io.*;

public class Main {
	
	// 각 간선의 정보를 저장한 내부 클래스
	static class Edge {
		int fromVertex;	// 출발지
		int toVertex;	// 도착지
		int weight;	// 가중치(폭)
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 도시의 수
	static int M;	// 운하의 수
	static int K;	// 노선의 수
	static int[] parents;	// 유니온파인드를 위한 부모 배열
	static int[][] maxWidth;	// 각 노드 쌍 간의 가능한 최대 폭
	static ArrayList<ArrayList<Integer>> graph;	 // 각 노드와 연결된 노드들의 리스트를 저장
	
	// 운하 정보를 가중치(폭) 내림차순으로 저장하는 우선순위 큐
	// 각 간선들 폭(가중치) 기준으로 내침차순 정렬하게끔
	static PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> b.weight - a.weight);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		maxWidth = new int[N+1][N+1];
		
		graph = new ArrayList<>();	// 각 노드의 연결된 노드 리스트 초기화
		for(int i=0; i<=N; i++) {
			parents[i] = i;	// 초기에 각 노드의 부모는 자기 자신
			graph.add(new ArrayList<>());
			graph.get(i).add(i);	// 각 노드에 자기 자신 추가
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.add(new Edge(fromVertex, toVertex, weight));	// 운하 정보를 우선순위 큐에 추가
		}
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();	// 가장 폭이 넓은 운하부터 처리하게끔 우선순위 큐에서 현재 간선 정보 뽑음
			int fromVertex = now.fromVertex;
			int toVertex = now.toVertex;
			int weight = now.weight;
			
			union(fromVertex, toVertex, weight);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			sb.append(maxWidth[fromVertex][toVertex]).append("\n");
		}
		
		System.out.print(sb);
		
	}
	
	// 유니온파인드에서 루트 노드 찾는 메서드
	public static int find(int a) {
		// 자신이 루트노드인 경우
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);	// 경로를 압축해줌
	}
	
	// 두 집합을 합치는 유니온 연산
	public static void union(int a, int b, int weight) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 두 루트노드가 같은 집합인 경우
		if(aRoot == bRoot) {
			return;
		}
		// 두 루트노드가 다른 집합인 경우
		else {
			for(int aVertex: graph.get(aRoot)) {
				for(int bVertex: graph.get(bRoot)) {
					// 두 집합 간의 최대 폭 업데이트
					maxWidth[aVertex][bVertex] = weight;
					maxWidth[bVertex][aVertex] = weight;
				}
			}
			
			// aRoot의 모든 정점들을 bRoot의 리스트에 추가
	        graph.get(bRoot).addAll(graph.get(aRoot));
			parents[aRoot] = bRoot;	// aRoot의 부모를 bRoot로 설정
		}
	}
	

}
