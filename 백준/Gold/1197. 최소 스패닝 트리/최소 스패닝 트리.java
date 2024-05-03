import java.util.*;
import java.io.*;

public class Main {
	
	// 간선 정보를 나타내는 내부 클래스
	static class Edge {
		int fromVertex;	// 시작 정점
		int toVertex;	// 도착 정점
		int weight;	// 가중치
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int V;	// 정점의 개수
	static int E;	// 간선의 개수
	static int[] parents;	// 크루스칼 알고리즘(유니온 파인드 응용)을 이용하기 위한 부모 배열
	static ArrayList<Edge> edgeList;	// 간선 목록을 저장하는 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parents = new int[V+1];	// [1] ~ [V]
		edgeList = new ArrayList<>();
		for (int i=1; i<=V; i++) {
			parents[i] = i;	// 각 점정의 부모를 자기 자신으로 설정
		}
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 간선 정보 간선 리스트에 저장
			edgeList.add(new Edge(fromVertex, toVertex, weight));
		}
		
		// 크루스칼 알고리즘을 이용하기 위해 (최소신장트리) 간선의 가중치 오름차순 정렬
		Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
		
		int totalWeight = 0;	// 최소신장트리를 형성할 때 드는 최소 비용
		
		// 모든 간선에 대해서 순회
		for (Edge edge: edgeList) {
			int root1 = find(edge.fromVertex);	// 시작 정점의 루트
			int root2 = find(edge.toVertex);	// 도착 정점의 루트
			
			// 두 정점의 루트가 다른 경우
			if (root1 != root2) {
				union(edge.fromVertex, edge.toVertex);	// 두 트리를 합치게끔 해서 유니온 연산 수행
				totalWeight += edge.weight;	// 최소신장트리를 형성할 때 드는 최소 비용에 해당 간선의 가중치 누적
			}
		}
		
		System.out.println(totalWeight);
	}
	
	// 유니온 파인드에서의 파인드 메서드
	public static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	// 유니온 파인드에서의 유니온 메서드
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
