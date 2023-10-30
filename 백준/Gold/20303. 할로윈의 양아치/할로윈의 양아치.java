import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int K;
	static int[] parents;
	static int[] candy;
	static int[] groupCandy;
	static int[] groupCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		candy = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			parents[i] = i;
			candy[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int friend1 = Integer.parseInt(st.nextToken());
			int friend2 = Integer.parseInt(st.nextToken());
			
			union(friend1, friend2);
		}
		
		Set<Integer> set = new HashSet<>();
		for(int i=1; i<=N; i++) {
			set.add(find(i));
		}
		
		groupCandy = new int[N+1];
		groupCount = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			int root = find(i);
			groupCandy[root] += candy[i];
			groupCount[root]++;
		}
		
		List<Integer> uniqueGroupList = new ArrayList<>(set);
		int[][] dp = new int[uniqueGroupList.size() + 1][K];
		
		for(int i=1; i<=uniqueGroupList.size(); i++) {
			int groupRoot = uniqueGroupList.get(i-1);
			int groupValue = groupCandy[groupRoot];
			int groupWeight = groupCount[groupRoot];
			
			for(int j=0; j<K; j++) {
				dp[i][j] = dp[i-1][j];
				if(j >= groupWeight) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j - groupWeight] + groupValue);
				}
			}
		}
		
		int answer = 0;
		for(int i=0; i<K; i++) {
			answer = Math.max(answer, dp[uniqueGroupList.size()][i]);
		}
		System.out.println(answer);
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
