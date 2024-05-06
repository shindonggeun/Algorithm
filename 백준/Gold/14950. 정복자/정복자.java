import java.util.*;
import java.io.*;

public class Main {
	
	// 간선의 정보를 담고 있는 내부 클래스
	static class Edge {
		int fromVertex;	// 시작 정점 (시작 도시)
		int toVertex;	// 도착 정점 (도착 도시)
		int weight;	// 가중치 (도로 비용)
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 도시의 개수 (정점의 개수)
	static int M;	// 도로의 개수 (간선의 개수)
	static int t;	// 한번 정복할 때마다 증가하는 도로의 비용
	static int[] parents;	// 크루스칼 알고리즘(유니온 파인드 응용)을 이용하기 위한 부모 배열
	static ArrayList<Edge> edgeList;	// 간선 정보들을 담고있는 간선 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];	// [1] ~ [N]
		edgeList = new ArrayList<>();	// 간선 리스트 초기화
		
		for (int i=1; i<=N; i++) {
			parents[i] = i;	// 각 정점(도시)의 부모를 자기 자신으로 설정
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 크루스칼 알고리즘을 이용한 최소 신장 트리 문제에서는 도로가 양방향이라는 점 고려할 필요 없음
			// 간선 정보를 리스트에 저장
			edgeList.add(new Edge(fromVertex, toVertex, weight));
		}
		
		// 크루스칼 알고리즘을 사용하기 위해 간선 리스트에서 간선의 가중치를 기준으로 오름차순 정렬
		Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
		
		int minWeight = 0;	// 최소 신장 트리(MST)를 구성하기 위해 필요한 가중치 (도로 길이 합)
		int start = 0;	// 도시를 정복할 때마다 증가된 도로 개수
		for (Edge edge: edgeList) {
			int root1 = find(edge.fromVertex);	// 시작 정점(도시)의 루트 노드
			int root2 = find(edge.toVertex);	// 도착 정점(도시)의 루트 노드
			
			// 두 루트노드가 다른 경우
			if (root1 != root2) {
				union(edge.fromVertex, edge.toVertex);	// 유니온 연산 수행
				// 최소 신장 트리를 형설할 때 드는 최소 비용(최소 랜선)에 해당 간선의 가중치 (도로 비용) 누적 및 증가하는 도로 비용까지 누적
				minWeight += edge.weight + (t * start);	
				start++;	// 도시 정복한 개수 증가 
			}
		}
		
		System.out.println(minWeight);
	}
	
	// 유니온 파인드에서 파인드 연산을 담당하는 메서드
	public static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	// 유니온 파인드에서 유니온 연산을 담당하는 메서드
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) {
			return;
		}
		else if (aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}