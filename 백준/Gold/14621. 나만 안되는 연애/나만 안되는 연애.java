import java.util.*;
import java.io.*;

public class Main {
	
	// 간선 정보를 저장하는 내부 클래스
	static class Edge implements Comparable<Edge> {
		int fromVertex;	// 시작 학교 (시작지)
		int toVertex;	// 도착 학교 (도착지)
		int weight;	// 거리 (가중치)
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// 가중치를 기준으로 오름차순 정렬
			return this.weight - o.weight;
		}
	}
	
	static int N;	// 학교의 수
	static int M;	// 학교를 연결하는 도로의 개수
	static int[] parents;	// 각 원소의 부모 원소를 저장하는 배열 (유니온 파인드에 이용)
	static char[] gender;	// 각 학교마다 어느 성별의 학교인지 여부를 저장하는 배열
	static ArrayList<Edge> edgeList;	// 모든 간선을 저장하는 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];	// [1] ~ [N]
		edgeList = new ArrayList<>();	// 간선을 저장할 리스트 초기화
		gender = new char[N+1];	// [1] ~ [N]
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			parents[i] = i;	// 초기 부모는 자기 자신으로 초기화
			gender[i] = st.nextToken().charAt(0);	// 각 학교의 성별 저장
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 간선 리스트에 간선 추가
			edgeList.add(new Edge(fromVertex, toVertex, weight));
		}
		
		// 크루스칼 알고리즘 이용
		Collections.sort(edgeList);
		
		int minTotalWeight = 0;	// MST를 형성할 대 드는 최소 비용
		// 모든 간선들에 대해 탐색
		for(Edge edge: edgeList) {
			int fromVertex = edge.fromVertex;
			int toVertex = edge.toVertex;
			int weight = edge.weight;
			
			// 사이클이 형성되지 않은 경우
			if(find(fromVertex) != find(toVertex)) {
				// 서로 다른 성별의 학교인 경우 (즉, 서로 각각 남초대학, 여초대학인 경우)
				if(gender[fromVertex] != gender[toVertex]) {
					// 해당 간선을 MST에 추가할 수 있게끔
					union(fromVertex, toVertex);	 // 두 노드를 합치는 유니온 연산 수행
					minTotalWeight += weight;	// 최소 비용에 간선의 가중치 더해줌
				}
			}
		}
		
		boolean isConnected = true;	// 모든 학교가 연결되었는지 여부를 판단하는 변수
		for(int i=2; i<=N; i++) {
			// 1번 학교의 루트와 연결되지 않은 경우
			// (MST를 형성하고 난 뒤 모든 학교가 연결되어 있으면 루트 노드가 같아야함)
			if(find(1) != find(i)) {
				isConnected = false;	// 하나라도 연결되지 않은 학교가 있는 경우 false 반환
				break;	// 반복문 빠져나옴
			}
		}
		
		// 모든 학교가 연결되어 있는 경우
		if(isConnected) {
			System.out.println(minTotalWeight);
		}
		// 연결되어 있지 않은 경우
		else {
			System.out.println(-1);
		}
	}
	
	// 유니온 파인드의 find 연산 (재귀적으로 부모를 찾아감)
	public static int find(int a) {
		// 자기 자신이 부모인 경우, 즉, 루트인 경우
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);	// 경로 압축을 위해 부모를 루트로 설정
	}
	
	// 유니온 파인드의 union 연산 (두 원소의 집합을 합침)
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
