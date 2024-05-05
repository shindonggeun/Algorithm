import java.util.*;
import java.io.*;

public class Main {
	
	// 간선의 정보를 담고있는 내부 클래스
	static class Edge {
		int fromVertex;	// 시작 정점 (시작지 컴퓨터)
		int toVertex;	// 도착 정점 (도착지 컴퓨터)
		int weight;	// 가중치 (랜선 길이)
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}

	static int N;	// 컴퓨터의 개수 (정점의 개수)
	static int[] parents;	// 크루스칼 알고리즘(유니온 파인드 응용)을 이용하기 위한 부모 배열
	static ArrayList<Edge> edgeList;	// 간선 정보들을 담고있는 간선 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];	// [1] ~ [N]
		edgeList = new ArrayList<>();	// 간선 리스트 초기화
		
		for (int i=1; i<=N; i++) {
			parents[i] = i;	// 각 점정(도시)의 부모를 자기 자신으로 설정
		}
		
		int totalWeight = 0;	// 컴퓨터가 연결된 총 랜선(가중치)의 길이(합)
		for (int i=1; i<=N; i++) {
			String input = br.readLine();
			for (int j=1; j<=N; j++) {
				int fromVertex = i;	// 시작 정점 (시작 컴퓨터)
				int toVertex = j;	// 도착 정점 (도착 컴퓨터)
				char ch = input.charAt(j-1);	// 문자 추출하기
				int weight = 0;	// 랜선 길이(가중치) 0으로 초기화
				
				// 문자가 '0'인 경우 컴퓨터끼리 연결된 랜선이 없으므로
				if (ch == '0') {
					continue;	// 다음 컴퓨터 확인하도록 넘어감
				}
				
				// 문자가 소문자 'a' ~ 'z' 범위에 있는 문자인 경우 
				if (ch >= 'a' && ch <= 'z') {
					weight = ch - 'a' + 1;	// 랜선 길이 계산 (a = 1, ... , z = 26)
				}
				// 문자가 'a' ~ 'z' 범위에 있는 문자가 아닌 경우 (대문자인 경우)
				else {
					weight = ch - 'A' + 27;	// 랜선 길이 계산 (A = 27, ... , Z = 52)
				}

				totalWeight += weight;	//컴퓨터가 연결되어있는 총 랜선 길이 합산
				
				// 간선리스트에 간선 정보 저장
				edgeList.add(new Edge(fromVertex, toVertex, weight));
			}
		}
		
		// 크루스칼 알고리즘을 이용하기 위해 (최소신장트리) 간선의 가중치 기준으로 오름차순 정렬 
		Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
		
		int minWeight = 0;	// 최소 신장 트리를 이루기 위해 필요한 가중치 (랜선 길이 합)
		int edgeCount = 0;	// 연결된 간선 개수 (컴퓨터가 연결되어 있는 개수)
		// 모든 간선에 대해서 순회
		for (Edge edge: edgeList) {
			int root1 = find(edge.fromVertex);	// 시작 정점(시작 컴퓨터)의 루트 노드
			int root2 = find(edge.toVertex);	// 도착 정점(도착 컴퓨터)의 루트 노드
			
			// 두 정점의 노드가 다른 경우
			if (root1 != root2) {
				union(edge.fromVertex, edge.toVertex);	// 두 트리를 합치게끔 해서 유니온 연산 수행
				minWeight += edge.weight;	// 최소신장트리를 형성할 때 드는 최소 비용(최소 랜선)에 해당 간선의 가중치 누적
				edgeCount++;	// 간선 개수 증가
			}
			
			// 간선의 개수가 N-1인 경우 (최소 신장 트리를 위루기 위해서는 간선의 개수가 정점의 개수 - 1 이다)
			if (edgeCount == N-1) {
				break;	// 간선 더 탐색할 필요 없이 반복문 빠져나옴
			}
		}
		
		// 연결된 간선의 개수가 N-1개 미만인 경우 (즉, 최소 신장 트리 안됨)
		if (edgeCount < N-1) {
			System.out.println(-1);
		}
		else {
			System.out.println(totalWeight - minWeight);
		}
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