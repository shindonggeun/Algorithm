import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] population;
	static int[] parents;
	static boolean[] isSelected;
	static ArrayList<ArrayList<Integer>> graph;
	static int minPopulationDiff;	// 두 선거구의 인구 차이의 최소값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		parents = new int[N+1];
		isSelected = new boolean[N+1];
		graph = new ArrayList<>();
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i=1; i<=N; i++) {
			st= new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			
			for(int c=0; c<count; c++) {
				int fromVertex = i;
				int toVertex = Integer.parseInt(st.nextToken());
				graph.get(fromVertex).add(toVertex);
			}
		}
		
		minPopulationDiff = Integer.MAX_VALUE;
		generateSubset(1);
		
		if(minPopulationDiff == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(minPopulationDiff);
		}
	}
	
	public static void generateSubset(int cnt) {
		if(cnt == N+1) {
			populationDiffCalculate();
			return;
		}
		
		isSelected[cnt] = true;
		generateSubset(cnt+1);
		isSelected[cnt] = false;
		generateSubset(cnt+1);
	}
	
	public static int find(int x) {
		if(x == parents[x]) {
			return x;
		}
		return parents[x] = find(parents[x]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot != bRoot) {
			parents[aRoot] = bRoot;
		}
	}
	
	public static void populationDiffCalculate() {
		ArrayList<Integer> groupA = new ArrayList<>();
		ArrayList<Integer> groupB = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		for(int i=1; i<=N; i++) {
			if(isSelected[i]) {
				groupA.add(i);
			}
			else {
				groupB.add(i);
			}
		}
		
		if(groupA.size() == 0 || groupB.size() == 0) {
			return;
		}
		
		for(int i=1; i<=N; i++) {
			for(int vertex: graph.get(i)) {
				if(isSelected[i] == isSelected[vertex]) {
					union(i, vertex);
				}
			}
		}
		
		int repA = find(groupA.get(0));
		int repB = find(groupB.get(0));
		
		for(int a: groupA) {
			if(find(a) != repA) {
				return;
			}
		}
		
		for(int b: groupB) {
			if(find(b) != repB) {
				return;
			}
		}
		
		// 선거구 A의 인구 총합 구하기
		int populationSumA = 0;
		for(int i: groupA) {
			populationSumA += population[i];
		}
		
		// 선거구 B의 인구 총합 구하기
		int populationSumB = 0;
		for(int i: groupB) {
			populationSumB += population[i];
		}
		
		
		minPopulationDiff = Math.min(minPopulationDiff, Math.abs(populationSumA - populationSumB));
	}

}
