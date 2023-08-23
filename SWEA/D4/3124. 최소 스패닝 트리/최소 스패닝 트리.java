import java.util.*;
import java.io.*;

public class Solution {
	
	// 간선에 따른 정보들을 저장할 내부 클래스
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
		public int compareTo(Edge e) {
			// 가중치 기준으로 오름차순 정렬 (작은 순으로)
			return this.weight - e.weight;
		}
		
		
	}
	
	static Edge[] edgeList;
	static int V;
	static int E;
	static int[] parents;
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 수만큼 순회
		for(int tc=1; tc<=testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());	// 정점의 개수
			E = Integer.parseInt(st.nextToken());	// 간선의 개수
			
			edgeList = new Edge[E];
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());	// 가중치 입력
				edgeList[i] = new Edge(fromVertex, toVertex, weight);
			}
			
			// 간선리스트를 가중치 기준 오름차순 정렬
			Arrays.sort(edgeList);
			
			// V개의 정점으로 make set 작업
			makeSet();
			
			long mst = 0;	// 최소신장트리 비용 (long인거 주의)
							// 절대값이 1,000,000까지인데 정점의 개수 100,000이므로 1,000,000 * 100,000 => 1000억이므로 int 범위 넘어섬
			int count = 0;	// 연결된 간선의 개수
			
			// 간선리스트 순회하기
			for(Edge edge: edgeList) {
				if(unionSet(edge.fromVertex, edge.toVertex)) {
					mst += edge.weight;	// 해당 간선의 가중치 더해주기
					count++;	// 연결된 간선의 개수 증가
					// 최소신장트리는 V-1개의 간선으로 이루어져 있으므로
					if(count == V-1) {
						break;	// 더이상 탐색할 필요 없으므로 반복문 빠져나옴
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(mst).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void makeSet() {
		parents = new int[V+1];	// 정점 번호 1번부터 시작해서 V번까지 있으므로
		// 1번정점부터 V번 정점까지 부모노드 배열에 각 정점번호 저장
		for(int i=1; i<=V; i++) {
			parents[i] = i;
		}
	}
	
	public static int findSet(int a) {
		if(parents[a] == a) {
			return a;
		}
		return parents[a] = findSet(parents[a]);
	}
	
	public static boolean unionSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) {
			return false;
		}
		parents[bRoot] = aRoot;
		return true;
	}

}
