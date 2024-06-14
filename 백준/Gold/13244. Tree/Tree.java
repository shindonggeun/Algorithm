import java.util.*;
import java.io.*;

public class Main {
	
	static int T;
	static int N;
	static int M;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			parents = new int[N+1];
			for (int i=1; i<=N; i++) {
				parents[i] = i;
			}
			
			boolean cycle = false;
			
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				
				if (find(node1) != find(node2)) {
					union(node1, node2);
				}
				else {
					cycle = true;
				}
			}
			
			// 간선(엣지) 개수가 노드 개수 - 1이 아닌 경우
			// 트리가 되려면 엣지수가 N-1이어야 한다.
			if (M != N - 1) {
				sb.append("graph").append("\n");
				continue;
			}
			
			int root = find(1);
			for (int i=2; i<=N; i++) {
				if (root != find(i)) {
					cycle = true;
					break;
				}
			}
			
			if (cycle) {
				sb.append("graph").append("\n");
			}
			else {
				sb.append("tree").append("\n");
			}
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