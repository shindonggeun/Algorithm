import java.util.*;
import java.io.*;

public class Main {
	
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int fromVertex;
		int toVertex;
		double weight;
		
		public Edge(int fromVertex, int toVertex, double weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	static int N;
	static int M;
	static Point[] pointArr;
	static int[] parents;
	static ArrayList<Edge> edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		pointArr = new Point[N+1];
		edgeList = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			parents[i] = i;
			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			pointArr[i] = new Point(x, y);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			union(fromVertex, toVertex);
		}
		
		for(int i=1; i<=N-1; i++) {
			for(int j=i+1; j<=N; j++) {
				double distance = calculateDistance(pointArr[i], pointArr[j]);
				edgeList.add(new Edge(i, j, distance));
			}
		}
		
		Collections.sort(edgeList);
		
		double totalWeight = 0;
		for(Edge edge: edgeList) {
			int root1 = find(edge.fromVertex);
			int root2 = find(edge.toVertex);
			
			if(root1 != root2) {
				union(edge.fromVertex, edge.toVertex);
				totalWeight += edge.weight;
			}
		}
		
		System.out.println(String.format("%.2f", totalWeight));
	}
	
	public static double calculateDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
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
