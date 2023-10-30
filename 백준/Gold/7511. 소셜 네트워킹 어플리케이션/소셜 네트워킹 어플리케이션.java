import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int K;
	static int[] parents;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			sb.append("Scenario ").append(tc).append(":\n");
			
			N = Integer.parseInt(br.readLine());
			K = Integer.parseInt(br.readLine());
			
			parents = new int[N+1];
			for(int i=1; i<=N; i++) {
				parents[i] = i;
			}
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				
				union(fromVertex, toVertex);
			}
			
			M = Integer.parseInt(br.readLine());
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				
				if(find(fromVertex) == find(toVertex)) {
					sb.append(1).append("\n");
				}
				else {
					sb.append(0).append("\n");
				}
			}
			sb.append("\n");
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
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}
