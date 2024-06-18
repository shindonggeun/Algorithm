import java.util.*;
import java.io.*;

public class Main {

	static int T;
	static int F;
	static int[] parents;
	static int[] count;
	static Map<String, Integer> friendMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			F = Integer.parseInt(br.readLine());
			
			parents = new int[F*2+1];
			count = new int[F*2+1];
			
			for (int i=1; i<=F*2; i++) {
				parents[i] = i;
				count[i] = 1;
			}
			
			friendMap = new HashMap<>();
			int idx = 1;
			
			for (int i=0; i<F; i++) {
				st = new StringTokenizer(br.readLine());
				
				String friend1 = st.nextToken();
				String friend2 = st.nextToken();
				
				if (!friendMap.containsKey(friend1)) {
					friendMap.put(friend1, idx++);
				}
				
				if (!friendMap.containsKey(friend2)) {
					friendMap.put(friend2, idx++);
				}
				
				int node1 = friendMap.get(friend1);
				int node2 = friendMap.get(friend2);
				
				union(node1, node2);
				
				sb.append(count[find(node2)]).append("\n");
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
		
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			count[aRoot] += count[bRoot];
		}
	}

}