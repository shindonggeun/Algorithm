import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] parents;
	static boolean[] fact;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		fact = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<num; i++) {
			int idx = Integer.parseInt(st.nextToken());
			fact[idx] = true;
		}
		
		ArrayList<HashSet<Integer>> partyList = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			partyList.add(new HashSet<>());
			
			String[] input = br.readLine().split(" ");
			int partyHumanCount = Integer.parseInt(input[0]);
			
			if(partyHumanCount <= 1) {
				partyList.get(i).add(Integer.parseInt(input[1]));
				continue;
			}
			
			for(int j=1; j<partyHumanCount; j++) {
				int humanA = Integer.parseInt(input[j]);
				int humanB = Integer.parseInt(input[j+1]);
				
				union(humanA, humanB);
				partyList.get(i).add(humanA);
				partyList.get(i).add(humanB);
			}
		}
		
		boolean[] visited = new boolean[N+1];	// 방문배열
		
		for(int i=1; i<=N; i++) {
			if(!visited[i] && fact[i]) {
				int root = find(i);
				for(int j=1; j<=N; j++) {
					if(root == find(j)) {
						visited[j] = true;
						fact[j] = true;
					}
				}
			}
		}
		
		int conversationCount = 0;
		for(int i=0; i<M; i++) {
			boolean factCheck = false;
			for(int humanNum: partyList.get(i)) {
				if(fact[humanNum]) {
					factCheck = true;
					break;
				}
			}
			
			if(!factCheck) {
				conversationCount++;
			}
		}
		
		System.out.println(conversationCount);
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
