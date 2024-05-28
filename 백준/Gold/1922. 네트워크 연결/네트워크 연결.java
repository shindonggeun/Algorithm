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
	
	static int N;	// 컴퓨터의 수 (정점의 수)
	static int M;	// 연결할 수 있는 선의 수 (간선의 수)
	static ArrayList<Edge> edgeList;	// 간선들을 저장하는 리스트
	static int[] parents;	// 크루스칼 알고리즘(유니온 파인드 응용)을 이용하기 위한 부모 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		edgeList = new ArrayList<>();
		parents = new int[N+1];	// [1] ~ [N]
		
		for (int i=1; i<=N; i++) {
			parents[i] = i;	// 각 정점의 부모를 자기 자신으로 설정
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());	// 시작 컴퓨터 번호 (시작 정점)
			int toVertex = Integer.parseInt(st.nextToken());	// 도착 컴퓨터 번호 (도착 정점)
			int weight = Integer.parseInt(st.nextToken());	// 비용 (가중치)
			
			// 간선 정보 간선 리스트에 저장
			edgeList.add(new Edge(fromVertex, toVertex, weight));
		}
		
		// 크루스칼 알고리즘을 이용하기 위해 간선 리스트 가중치 기준으로 오름차순 정렬
		Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
		
		int minWeight = 0;	// 최소 신장 트리를 완성할 때 드는 최소 비용
		// 모든 간선에 대해서 순회
		for (Edge edge: edgeList) {
			int root1 = find(edge.fromVertex);	// 시작 정점의 루트
			int root2 = find(edge.toVertex);	// 도착 정점의 루트
			
			// 두 정점의 루트가 다른 경우
			if (root1 != root2) {
				// 두 트리를 합치게끔 하기 위해 유니온 연산 수행
				union(edge.fromVertex, edge.toVertex);
				minWeight += edge.weight;	// 최소 신장 트리를 형성할 때 드는 최소 비용에 해당 간선의 가중치 더해줌
			}
		}
		
		System.out.println(minWeight);
	}
	
	// 유니온 파인드에서의 파인드 메서드
	public static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return find(parents[a]);
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