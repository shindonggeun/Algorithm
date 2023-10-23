import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] parents;
	static int[] rank;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			Map<String, Integer> map = new HashMap<>();
			N = Integer.parseInt(br.readLine());
			parents = new int[N*2];
			rank = new int[N*2];
			
			for(int i=0; i<N*2; i++) {
				parents[i] = i;
				rank[i] = 1;
			}
			
			int idx = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				
				String fromName = st.nextToken();
				String toName = st.nextToken();
				
				if(!map.containsKey(fromName)) {
					map.put(fromName, idx);
					idx++;
				}
				
				if(!map.containsKey(toName)) {
					map.put(toName, idx);
					idx++;
				}
				
				int aRoot = map.get(fromName);
				int bRoot = map.get(toName);
			
				int network = union(aRoot, bRoot);
				sb.append(network).append("\n");
				
			}
			
		}
		System.out.print(sb);

	}
	
	public static int find(int x) {
		if(x == parents[x]) {
			return x;
		}
		parents[x] = find(parents[x]);
		return parents[x];
	}
	
	// 합집합 연산
	public static int union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot != bRoot) {
			// bRoot가 부모인 경우
			if(aRoot > bRoot) {
				parents[aRoot] = bRoot;
				rank[bRoot] += rank[aRoot];
				return rank[bRoot];
			} 
			// aRoot가 부모인 경우
			else {
				parents[bRoot] = aRoot;
				rank[aRoot] += rank[bRoot];
			}
		}
		
		return rank[aRoot];
	}

}
