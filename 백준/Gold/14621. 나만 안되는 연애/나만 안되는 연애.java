import java.util.*;
import java.io.*;

public class Main {
	
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
			return this.weight - o.weight;
		}
	}
	
	static int N;	// 학교의 수
	static int M;	// 학교를 연결하는 도로의 개수
	static int[] parents;
	static char[] gender;
	static ArrayList<Edge> edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];	// [1] ~ [N]
		edgeList = new ArrayList<>();
		gender = new char[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			parents[i] = i;
			gender[i] = st.nextToken().charAt(0);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeList.add(new Edge(fromVertex, toVertex, weight));
		}
		
		// 크루스칼 알고리즘 이용
		Collections.sort(edgeList);
		
		int minTotalWeight = 0;
		for(Edge edge: edgeList) {
			int fromVertex = edge.fromVertex;
			int toVertex = edge.toVertex;
			int weight = edge.weight;
			
			if(find(fromVertex) != find(toVertex)) {
				// 서로 다른 성별의 학교인 경우 (즉, 서로 각각 남초대학, 여초대학인 경우)
				if(gender[fromVertex] != gender[toVertex]) {
					union(fromVertex, toVertex);
					minTotalWeight += weight;
				}
			}
		}
		
		boolean isConnected = true;	// 모든 학교가 연결되었는지 여부를 판단하는 변수
		for(int i=2; i<=N; i++) {
			if(find(1) != find(i)) {
				isConnected = false;
				break;
			}
		}
		
		if(isConnected) {
			System.out.println(minTotalWeight);
		}
		else {
			System.out.println(-1);
		}
	}
	
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return;
		}
		else if(aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}
