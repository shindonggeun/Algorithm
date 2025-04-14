import java.util.*;
import java.io.*;

public class Main {
	
	// 간선 정보를 저장하는 내부 클래스
	static class Edge {
		int fromVertex;
		int toVertex;
		int weight;
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N; // 논의 개수
	static int[] parents; // 유니온-파인드 알고리즘을 위해 사용할 배열 (각 정점의 부모 정점을 저장할 배열)
	static List<Edge> edgeList; // 모든 간선을 저장할 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		parents = new int[N+1]; // [1] ~ [N]
		edgeList = new ArrayList<>(); // 간선 정보를 저장하는 리스트 생성
		
		// 1. 각 논에 우물을 팔 경우의 비용을 간선으로 추가 (가상 노드 0 -> 논 i)
		for (int i=1; i<=N; i++) {
			int weight = Integer.parseInt(br.readLine()); // i번째 논에 우물을 팔 때 드는 비용
			edgeList.add(new Edge(0, i, weight));
			parents[i] = i; // 각 정점의 부모 정점은 자기 자신으로 초기화
		}
		
		// 2. 논들 간의 연결 비용 입력 및 간선 추가
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				edgeList.add(new Edge(i, j, weight));
			}
		}
		
		// 3. 크루스칼 알고리즘을 이용하기 위해 모든 간선을 비용 기준으로 오름차순 정렬
		Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
		
		int totalWeight = 0; // 최소신장트리의 총 비용
		
		// 4. 크루스칼 알고리즘 수행 (모든 간선 탐색)
		for (Edge edge: edgeList) {
			int rootA = find(edge.fromVertex);
			int rootB = find(edge.toVertex);
			
			// 사이클이 생기지 않은 경우만 간선을 선택
			if (rootA != rootB) {
				union(edge.fromVertex, edge.toVertex); // 해당 간선의 정점들 합치기
				totalWeight += edge.weight; // 해당 간선의 비용 누적
			}
		}
		
		System.out.println(totalWeight);

	}
	
	// 유니온-파인드에서 파인드 연산을 담당하는 메서드
	public static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	// 유니온-파인드에서 유니온 연산을 담당하는 메서드
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
		}
	}

}