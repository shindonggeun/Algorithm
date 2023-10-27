import java.util.*;
import java.io.*;

public class Main {
	
	static class Bridge {
		int fromVertex;
		int toVertex;
		int weight;
		
		public Bridge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int P;
	static int W;
	static int start;
	static int end;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		P = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		parents = new int[P];
		for(int i=0; i<P; i++) {
			parents[i] = i;
		}
		
		
		PriorityQueue<Bridge> pq = new PriorityQueue<>((a, b) -> b.weight - a.weight);
		for(int i=0; i<W; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.add(new Bridge(fromVertex, toVertex, weight));
		}
		
		int result = 0;
		while(!pq.isEmpty()) {
			Bridge now = pq.poll();
			int nowFromVertex = now.fromVertex;
			int nowToVertex = now.toVertex;
			int nowWeight = now.weight;
			
			union(nowFromVertex, nowToVertex);
			if(find(start) == find(end)) {
				result = nowWeight;
				break;
			}
		}
		
		System.out.println(result);
		
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
