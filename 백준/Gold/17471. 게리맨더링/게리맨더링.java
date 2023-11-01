import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] population;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] isSelected;
	static int minPopulationDiff;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		population = new int[N+1];
		isSelected = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int areaCount = Integer.parseInt(st.nextToken());
			
			for(int c=0; c<areaCount; c++) {
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
	
	public static void generateSubset(int depth) {
		if(depth == N+1) {
			if(populationDiffCalculate()) {
				int populationSumA = 0;
				int populationSumB = 0;
				
				for(int i=1; i<=N; i++) {
					if(isSelected[i]) {
						populationSumA += population[i];
					}
					else {
						populationSumB += population[i];
					}
				}
				
				minPopulationDiff = Math.min(minPopulationDiff, Math.abs(populationSumA - populationSumB));
			}
			return;
		}
		
		isSelected[depth] = true;
		generateSubset(depth+1);
		isSelected[depth] = false;
		generateSubset(depth+1);
	}
	
	public static boolean populationDiffCalculate() {
		boolean[] visited = new boolean[N+1];
		int areaA = 0;
		for(int i=1; i<=N; i++) {
			if(isSelected[i]) {
				areaA = i;
				break;
			}
		}
		
		int areaB = 0;
		for(int i=1; i<=N; i++) {
			if(!isSelected[i]) {
				areaB = i;
				break;
			}
		}
		
		if(areaA == 0 || areaB == 0) {
			return false;
		}
		
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(areaA);
		visited[areaA] = true;
		
		while(!queue.isEmpty()) {
			int nowVertex = queue.poll();
			
			for(int i=0; i<graph.get(nowVertex).size(); i++) {
				int adjVertex = graph.get(nowVertex).get(i);
				
				// 이미 방문한 정점인 경우
				if(visited[adjVertex]) {
					continue;	// 넘어감
				}
				
				if(isSelected[adjVertex]) {
					queue.add(adjVertex);
					visited[adjVertex] = true;;
				}
			}
		}
		
		queue.add(areaB);
		visited[areaB] = true;
		
		while(!queue.isEmpty()) {
			int nowVertex = queue.poll();
			
			for(int i=0; i<graph.get(nowVertex).size(); i++) {
				int adjVertex = graph.get(nowVertex).get(i);
				
				if(visited[adjVertex]) {
					continue;
				}
				
				if(!isSelected[adjVertex]) {
					queue.add(adjVertex);
					visited[adjVertex] = true;
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				return false;
			}
		}
		
		return true;
	}

}
