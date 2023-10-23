import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		StringTokenizer st = null;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				int command = Integer.parseInt(st.nextToken());
				if(command == 1) {
					union(i, j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int start = find(Integer.parseInt(st.nextToken()));
		boolean isOk = true;
		for(int i=1; i<M; i++) {
			int now = Integer.parseInt(st.nextToken());
			
			if(start != find(now)) {
				isOk = false;
				break;
			}
		}
		
		if(isOk) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
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
		
		if(aRoot != bRoot) {
			parents[aRoot] = bRoot;
		}
		
	}

}
