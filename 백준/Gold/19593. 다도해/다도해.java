import java.util.*;
import java.io.*;

public class Main {
	
	static int T;
	static int N;
	static long seed;
	static long A;
	static long B;
	static int[] parents;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			seed = Long.parseLong(st.nextToken());
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			
			parents = new int[N]; // [0] ~ [N-1]
			visited = new boolean[N*N];
			
			for (int i=0; i<N; i++) {
				parents[i] = i;
			}
			
			long E = seed % (N * N);
			visited[(int) E] = true;
			boolean allConnect = false;
			int connectCount = 0;
			int day = 0;
			
			
			while (true) {
				int x = (int) (E / N);
				int y = (int) (E % N);
				
				if (x != y && union(x, y)) {
					connectCount++;
				}
				
				E = (E * A + B) % (N * N);
				day++;
				
				if (connectCount >= N-1) {
					allConnect = true;
					break;
				}
				
				if (visited[(int) E]) {
					break;
				}
				
				if (connectConfirm()) {
					allConnect = true;
					break;
				}
				
				visited[(int) E] = true;
			}
			
			if (allConnect) {
				sb.append(day).append("\n");
			}
			else {
				sb.append(0).append("\n");
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
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}
	
	public static boolean connectConfirm() {
		int root = find(0);
		
		for (int i=1; i<N; i++) {
			if (root != find(i)) {
				return false;
			}
		}
		
		return true;
	}

}