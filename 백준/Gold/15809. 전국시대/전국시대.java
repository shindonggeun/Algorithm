import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static int[] parents;
	static int[] militaryCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		militaryCount = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			militaryCount[i] = Integer.parseInt(st.nextToken());
			parents[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int isWar = Integer.parseInt(st.nextToken());
			int nationA = Integer.parseInt(st.nextToken());
			int nationB = Integer.parseInt(st.nextToken());
			
			union(nationA, nationB, isWar);
		}
		
		Set<Integer> set = new HashSet<>();
		for(int i=1; i<=N; i++) {
			int root = find(i);
			if(root == 0) {
				continue;
			}
			set.add(root);
		}
		
		StringBuilder sb = new StringBuilder();
		List<Integer> list = new ArrayList<>();
		for(int nationNum: set) {
			list.add(militaryCount[nationNum]);
		}
		
		Collections.sort(list);
		System.out.println(set.size());
		
		for(int military: list) {
			sb.append(military).append(" ");
		}
		System.out.println(sb);
	}
	
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	public static void union(int a, int b, int isWar) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return;
		}
		
		if(isWar == 1) {	
			if(militaryCount[aRoot] >= militaryCount[bRoot]) {
				militaryCount[aRoot] += militaryCount[bRoot];
				militaryCount[bRoot] = 0;
				parents[bRoot] = aRoot;
			}
			else {
				militaryCount[bRoot] += militaryCount[aRoot];
				militaryCount[aRoot] = 0;
				parents[aRoot] = bRoot;
			}
		}
		else {
			// 두 나라 멸망
			if(militaryCount[aRoot] == militaryCount[bRoot]) {
				militaryCount[aRoot] = 0;
				militaryCount[bRoot] = 0;
				parents[aRoot] = 0;
				parents[bRoot] = 0;
			}
			else if(militaryCount[aRoot] > militaryCount[bRoot]) {
				militaryCount[aRoot] -= militaryCount[bRoot];
				militaryCount[bRoot] = 0;
				parents[bRoot] = aRoot;
			}
			else {
				militaryCount[bRoot] -= militaryCount[aRoot];
				militaryCount[aRoot] = 0;
				parents[aRoot] = bRoot;
			}
		}
		
		
	}

}
