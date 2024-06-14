import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] parents;
	static int[] factPeople;
	static boolean[] fact;
	static ArrayList<ArrayList<Integer>> partyList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		fact = new boolean[N+1];
		
		for (int i=0; i<=N; i++) {
			parents[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		int factCount = Integer.parseInt(st.nextToken());
		
		factPeople = new int[factCount];
		
		for (int i=0; i<factCount; i++) {
			int num = Integer.parseInt(st.nextToken());
			factPeople[i] = num;
			fact[num] = true;
		}
		
		partyList = new ArrayList<>();
		
		for (int i=0; i<M; i++) {
			partyList.add(new ArrayList<>());
			
			st = new StringTokenizer(br.readLine());
			int humanCount = Integer.parseInt(st.nextToken());
			
			int firstHuman = Integer.parseInt(st.nextToken());
			partyList.get(i).add(firstHuman);
			
			for (int j=1; j<humanCount; j++) {
				int nextHuman = Integer.parseInt(st.nextToken());
				partyList.get(i).add(nextHuman);
				union(firstHuman, nextHuman);
			}
		}
		
		for (int i=0; i<factCount; i++) {
			int root = find(factPeople[i]);
			for (int j=1; j<=N; j++) {
				if (root == find(j)) {
					fact[j] = true;
				}
			}
		}
		
		int lieTalkCount = 0;
		
		for (ArrayList<Integer> party: partyList) {
			boolean liePossible = true;
			
			for (int human: party) {
				int root = find(human);
				if (fact[root]) {
					liePossible = false;
					break;
				}
			}
			
			if (liePossible) {
				lieTalkCount++;
			}
		}
		
		System.out.println(lieTalkCount);
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
			parents[aRoot] = bRoot;
		}
	}

}