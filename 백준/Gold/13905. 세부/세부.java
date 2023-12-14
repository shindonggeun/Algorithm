import java.util.*;
import java.io.*;

public class Main {

	// 간선의 정보들을 담고있는 내부 클래스
	static class Edge implements Comparable<Edge> {
		int fromVertex;
		int toVertex;
		int weight;
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// 간선에서 가중치(다리의 무게제한)을 기준으로 내림차순 정렬
			return o.weight - this.weight;
		}
	}
	
	static int N;	// 섬에 존재하는 집의 수
	static int M;	// 섬을 연결하는 다리의 수
	static int[] parents;	// 유니온 파인드를 위한 부모 노드 배열
	static ArrayList<Edge> edgeList;	// 간선들을 저장할 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];	// [1] ~ [N], 부모 노드 배열 초기화
		edgeList = new ArrayList<>();	// 간선 리스트 초기화
		for(int i=1; i<=N; i++) {
			parents[i] = i;	// 초기 부모는 자기 자신으로 설정
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());	// 출발위치
		int end = Integer.parseInt(st.nextToken());	// 도착위치
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 간선 정보들 리스트에 추가
			edgeList.add(new Edge(fromVertex, toVertex, weight));
		}
		
		// 크루스칼 알고리즘 변형 이용 (유니온 파인드 이용)
		// 간선들을 저장한 리스트 가중치 기준으로 내림차순 정렬
		// 최대 무게 제한을 가진 간선부터 고려하기
		Collections.sort(edgeList);
		
		int maxCount = 0;	// 출발위치에서 목적지 위치까지 숭이가 들고 갈 수 있는 금빼빼로의 최대 개수
		
		// 간선 정보들 저장한 리스트 탐색
		for(Edge edge: edgeList) {
			int fromVertex = edge.fromVertex;
			int toVertex = edge.toVertex;
			
			union(fromVertex, toVertex);	// 두 노드 연결해주는 유니온 연산 수행
			// 출발지와 도착지가 연결되어있는 경우
			if(find(start) == find(end)) {
				maxCount = edge.weight;	// 해당 간선의 가중치가 금빼배로의 최대 개수가 됨
				break;	// 간선 리스트 탐색 종료
			}
		}
		
		System.out.println(maxCount);
		
	}
	
	// 유니온파인드의 find 연산 (재귀적으로 부모를 찾아감)
	public static int find(int a) {
		// 자기 자신이 부모인 경우, 즉 루트인 경우
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);	// 경로 압축을 위해 부모를 루트로 설정
	}
	
	// 유니온파인드의 union 연산 (두 원소의 집합을 합침)
	public static void union(int a, int b) {
		// 각 원소들의 루트를 찾음
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 두 원소가 같은 집합에 속해 있는 경우
		if(aRoot == bRoot) {
			return;
		}
		// 작은 번호의 원소가 루트가 되도록 합침
		else if(aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}
