import java.util.*;
import java.io.*;

public class Main {

	static int[] parents;
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		for(int i=0; i<N; i++) {
			parents[i] = i;
		}
		
		boolean cycleCheck = false;
		int cycleNum = 0;
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			if(cycleCheck) {
				continue;
			}
			
			if(isCycle(fromVertex, toVertex)) {
				cycleCheck = true;
				cycleNum = i;
			}
		}
		
		System.out.println(cycleNum);
	}
	
	public static int find(int a) {
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	
	public static boolean isCycle(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return true;
		}
		
		parents[aRoot] = bRoot;
		return false;
	}
	
	

}
