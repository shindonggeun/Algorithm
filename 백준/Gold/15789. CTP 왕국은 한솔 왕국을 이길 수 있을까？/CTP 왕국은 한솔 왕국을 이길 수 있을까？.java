import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] parents;
	static int C;
	static int H;
	static int K;
	static int[] kingdomPower;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
		
			union(fromVertex, toVertex);
		}
		
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int cRoot = find(C);
		int hRoot = find(H);
		
		
		kingdomPower = new int[N+1];
		for(int i=1; i<=N; i++) {
			int root = find(i);
			kingdomPower[root]++;
		}
		
		int cKingdomPower = kingdomPower[cRoot];
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> kingdomPower[b] - kingdomPower[a]);
		for(int i=1; i<=N; i++) {
			if(find(i) != hRoot && find(i) != cRoot) {
				pq.add(i);
			}
		}
		
		int ckingdomMaxPower = cKingdomPower;
		int partnerShipCount = 0;
		
		while(!pq.isEmpty()) {
			if(partnerShipCount >= K) {
				break;
			}
			
			int nextKingdom = pq.poll();
			int rootKingdom = find(nextKingdom);
			ckingdomMaxPower += kingdomPower[rootKingdom];
			partnerShipCount++;
		}
		
		System.out.println(ckingdomMaxPower);
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
