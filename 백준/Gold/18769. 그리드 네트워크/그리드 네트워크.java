import java.util.*;
import java.io.*;

public class Main {
	
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
	
	static int R;
	static int C;
	static int[] parents;
	static ArrayList<Edge> edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			edgeList = new ArrayList<>();
			parents = new int[R * C];
			
			for (int i=0; i<R*C; i++) {
				parents[i] = i;
			}
			
			for (int i=0; i<R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<C-1; j++) {
					int weight = Integer.parseInt(st.nextToken());
					int fromVertex = i * C + j;
					int toVertex = i * C + (j + 1);
					
					edgeList.add(new Edge(fromVertex, toVertex, weight));
				}
			}
			
			for (int i=0; i<R-1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<C; j++) {
					int weight = Integer.parseInt(st.nextToken());
					int fromVertex = i * C + j;
					int toVertex = (i + 1) * C + j;
					
					edgeList.add(new Edge(fromVertex, toVertex, weight));
				}
			}
			
			Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
			
			int totalWeight = 0;
			
			for (Edge edge: edgeList) {
				int root1 = find(edge.fromVertex);
				int root2 = find(edge.toVertex);
				
				if (root1 != root2) {
					union(edge.fromVertex, edge.toVertex);
					totalWeight += edge.weight;
				}
			}
			
			sb.append(totalWeight).append("\n");
		}
		
		System.out.print(sb);

	}
	
	public static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
		}
	}

}
