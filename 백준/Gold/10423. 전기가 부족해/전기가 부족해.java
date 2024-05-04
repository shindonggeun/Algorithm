import java.util.*;
import java.io.*;

public class Main {
	
	// 간선정보를 담고있는 내부 클래스
	static class Edge {
		int fromVertex;	// 시작 정점 (시작 도시)
		int toVertex;	// 도착 정점 (도착 도시)
		int weight;	// 케이블 설치 비용 (가중치)
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 도시의 개수
	static int M;	// 설치 가능한 케이블의 수
	static int K;	// 발전소의 개수
	static int[] powerStation;	// 발전소들의 번호를 저장한 배열
	static int[] parents;	// 크루스칼 알고리즘(유니온 파인드 응용)을 이용하기 위한 부모 배열
	static ArrayList<Edge> edgeList;	// 간선들을 저장하는 간선 리스트
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];	// [1] ~ [N]
		powerStation = new int[K];
		edgeList = new ArrayList<>();
		
		for (int i=1; i<=N; i++) {
			parents[i] = i;	// 각 점정(도시)의 부모를 자기 자신으로 설정
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
			powerStation[i] = Integer.parseInt(st.nextToken());
		}
		
		// 발전소가 있는 모든 도시를 하나의 묶음으로 만들기
		int superNode = powerStation[0];	// 첫번째 발전소 도시를 슈퍼노드로 선택
		// 그다음 발전소부터 하나의 묶음으로 만들 수 있도록 유니온 연산 수행
		for (int i=1; i<K; i++) {
			union(superNode, powerStation[i]);
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 간선 정보를 리스트에 저장
			edgeList.add(new Edge(fromVertex, toVertex, weight));
		}
		
		// 크루스칼 알고리즘을 이용하기 위해 (최소신장트리) 간선의 가중치 오름차순 정렬
		Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
		
		int totalWeight = 0;	// 최소신장트리를 형성할 때 드는 최소 비용
		// 모든 간선에 대해서 순회
		for (Edge edge: edgeList) {
			int root1 = find(edge.fromVertex);	// 시작 정점(도시)의 루트
			int root2 = find(edge.toVertex);	// 도착 정점(도시)의 루트
			
			// 두 정점의 루트가 다른 경우
			if (root1 != root2) {
				union(edge.fromVertex, edge.toVertex);	// 두 트리를 합치게끔 해서 유니온 연산 수행
				totalWeight += edge.weight;	// 최소신장트리를 형성할 때 드는 최소 비용에 해당 간선의 가중치 누적
			}
		}
		
		System.out.println(totalWeight);
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
