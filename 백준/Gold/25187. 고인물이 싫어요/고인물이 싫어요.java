import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int Q;
	static int[] parents;
	static int[] waterType;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		waterType = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			int water = Integer.parseInt(st.nextToken());
			if(water == 0) {
				waterType[i] = -1;
			}
			else {
				waterType[i] = 1;
			}
			parents[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			union(fromVertex, toVertex);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<Q; i++) {
			int vertex = Integer.parseInt(br.readLine());
			int root = find(vertex);
			
			if(waterType[root] > 0) {
				sb.append(1).append("\n");
			}
			else {
				sb.append(0).append("\n");
			}
		}
		System.out.print(sb);
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
			waterType[bRoot] += waterType[aRoot];
			waterType[aRoot] = 0;
		}
		else {
			parents[bRoot] = aRoot;
			waterType[aRoot] += waterType[bRoot];
			waterType[bRoot] = 0;
		}
	}

}
