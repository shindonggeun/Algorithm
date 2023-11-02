import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] parents;
	static int[] groupDrain;
	static int[] groupRainfall;
	static int[] floodAreaCount;
	static int resultFloodAreaCount = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		groupDrain = new int[N+1];
		groupRainfall = new int[N+1];
		floodAreaCount = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			parents[i] = i;
			groupDrain[i] = Integer.parseInt(st.nextToken());
			floodAreaCount[i] = 1;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			groupRainfall[i] = Integer.parseInt(st.nextToken());
			if(groupDrain[i] < groupRainfall[i]) {
				resultFloodAreaCount++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());
			
			if(query == 1) {
				int area1 = Integer.parseInt(st.nextToken());
				int area2 = Integer.parseInt(st.nextToken());
				
				union(area1, area2);
			}
			else {
				sb.append(resultFloodAreaCount).append("\n");
			}
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
			if(groupDrain[bRoot] < groupRainfall[bRoot]) {
				resultFloodAreaCount -= floodAreaCount[bRoot];
			}
			
			if(groupDrain[aRoot] < groupRainfall[aRoot]) {
				resultFloodAreaCount -= floodAreaCount[aRoot];
			}
			
			parents[aRoot] = bRoot;
			groupDrain[bRoot] += groupDrain[aRoot];
			groupRainfall[bRoot] += groupRainfall[aRoot];
			floodAreaCount[bRoot] += floodAreaCount[aRoot];
		
			if(groupDrain[bRoot] < groupRainfall[bRoot]) {
				resultFloodAreaCount += floodAreaCount[bRoot];
			}
		}
		else {
			if(groupDrain[aRoot] < groupRainfall[aRoot]) {
				resultFloodAreaCount -= floodAreaCount[aRoot];
			}
			
			if(groupDrain[bRoot] < groupRainfall[bRoot]) {
				resultFloodAreaCount -= floodAreaCount[bRoot];
			}
			
			parents[bRoot] = aRoot;
			groupDrain[aRoot] += groupDrain[bRoot];
			groupRainfall[aRoot] += groupRainfall[bRoot];
			floodAreaCount[aRoot] += floodAreaCount[bRoot];
			
			if(groupDrain[aRoot] < groupRainfall[aRoot]) {
				resultFloodAreaCount += floodAreaCount[aRoot];
			}
		}
	}
}
