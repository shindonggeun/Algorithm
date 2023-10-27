import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] parents;
	static int[] enemy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		enemy = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		boolean isPossible = true;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int vertexA = Integer.parseInt(st.nextToken());
			int vertexB = Integer.parseInt(st.nextToken());
			
			if(!isPossible) {
				continue;
			}
			
			if(!allianceCheck(vertexA, vertexB)) {
				isPossible = false;
			}
		}
		
		System.out.println(isPossible ? 1 : 0);
	}
	
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	public static boolean allianceCheck(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return false;
		}
		
		int enemy1 = enemy[a];
		int enemy2 = enemy[b];
		
		if(enemy1 != 0) {
			union(enemy1, bRoot);
		}
		else {
			enemy[a] = bRoot;
		}
		
		if(enemy2 != 0) {
			union(enemy2, aRoot);
		}
		else {
			enemy[b] = aRoot;
		}
		
		return true;
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
