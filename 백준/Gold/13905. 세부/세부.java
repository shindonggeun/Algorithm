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
			// 각 간선들 가중치 기준으로 내림차순 정렬
			return o.weight - this.weight;
		}
	}
	
	static int N;
	static int M;
	static int start;
	static int end;
	static int[] parents;
	static ArrayList<Edge> edgeList;
	static int maxCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		edgeList = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			parents[i] = i;
 		}
		
		for(int i=0; i<M; i++) { 
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeList.add(new Edge(fromVertex, toVertex, weight));
		}
		
		Collections.sort(edgeList);
		
		maxCount = 0;
		for(int i=0; i<edgeList.size(); i++) {
			int from = edgeList.get(i).fromVertex;
			int to = edgeList.get(i).toVertex;
			maxCount = edgeList.get(i).weight;
			union(from, to);
			
			if(find(start) == find(end)) {
				break;
			}
		}
		
		if(parents[start] != parents[end]) {
			maxCount = 0;
		}
		
		System.out.println(maxCount);
		
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
