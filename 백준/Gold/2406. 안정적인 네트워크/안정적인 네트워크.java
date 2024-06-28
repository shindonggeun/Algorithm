import java.util.*;
import java.io.*;

public class Main {
	
	static class Network {
		int fromComputer;
		int toComputer;
		int weight;
		
		public Network(int fromComputer, int toComputer, int weight) {
			this.fromComputer = fromComputer;
			this.toComputer = toComputer;
			this.weight = weight;
		}
	}
	
	static int N;
	static int M;
	static int[][] cost;
	static int[] parents;
	static ArrayList<Network> networkList;
	static ArrayList<Network> resultList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		cost = new int[N+1][N+1]; // [1][1] ~ [N][N]
		
		networkList = new ArrayList<>();
		resultList = new ArrayList<>();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			union(x, y);
		}
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=2; i<=N; i++) {
			for (int j=i+1; j<=N; j++) {
				networkList.add(new Network(i, j, cost[i][j]));
			}
		}
		
		Collections.sort(networkList, (a, b) -> a.weight - b.weight);
		
		int totalMinWeight = 0;
		
		for (Network network: networkList) {
			int root1 = find(network.fromComputer);
			int root2 = find(network.toComputer);
			
			if (root1 != root2) {
				union(network.fromComputer, network.toComputer);
				totalMinWeight += network.weight;
				resultList.add(network);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(totalMinWeight).append(" ").append(resultList.size()).append("\n");
		
		for (Network network :resultList) {
			sb.append(network.fromComputer).append(" ").append(network.toComputer).append("\n");
		}
		
		System.out.print(sb);
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
			parents[bRoot] = aRoot;
		}
	}

}