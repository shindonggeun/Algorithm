import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		StringBuilder sb = new StringBuilder();
		int testCase = 1;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if (N == 0 && M == 0) {
				break;
			}
			
			parents = new int[N+1];
			
			for (int i=1; i<=N; i++) {
				parents[i] = i;
			}
			
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int vertex1 = Integer.parseInt(st.nextToken());
				int vertex2 = Integer.parseInt(st.nextToken());
				
				union(vertex1, vertex2);
			}
			
			Set<Integer> rootSet = new HashSet<>();
			
			for (int i=1; i<=N; i++) {
				int root = find(i);
				if (root > 0) {
					rootSet.add(root);
				}
			}
			
			sb.append("Case ").append(testCase).append(": ");
			
			if (rootSet.size() == 0) {
				sb.append("No trees.");
			}
			else if (rootSet.size() == 1) {
				sb.append("There is one tree.");
			}
			else {
				sb.append("A forest of ").append(rootSet.size()).append(" trees.");
			}
			
			
			sb.append("\n");
			testCase++;
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
			parents[aRoot] = 0;
		}
		else if (aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}