import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static int[] parents;
	static int[] planetCount;	// 은하가 연결될 때 마다 해당 은하에 있는 행성의 수를 더해 각 집합의 크기를 관리하는 배열
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		planetCount = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			parents[i] = i;
			planetCount[i] = Integer.parseInt(br.readLine());
		}
		
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			union(fromVertex, toVertex);
			int rootNode = find(fromVertex);
			
			sb.append(planetCount[rootNode]).append("\n");
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
			planetCount[bRoot] += planetCount[aRoot];	// 행성 수 합치기
		}
		else {
			parents[bRoot] = aRoot;
			planetCount[aRoot] += planetCount[bRoot];	// 행성 수 합치기
		}
	}

}
