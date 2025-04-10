import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] parents;
	static int[] sizes;
	static final int MAX = 1000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		parents = new int[MAX+1];
		sizes = new int[MAX+1];
		
		for (int i=1; i<=MAX; i++) {
			parents[i] = i;
			sizes[i] = 1;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			if (command.equals("I")) {
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				
				union(fromVertex, toVertex);
			}
			else {
				int vertex = Integer.parseInt(st.nextToken());
				int root = find(vertex);
				sb.append(sizes[root]).append("\n");
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
			sizes[bRoot] += sizes[aRoot];
		}
		else {
			parents[bRoot] = aRoot;
			sizes[aRoot] += sizes[bRoot];
		}
	}

}