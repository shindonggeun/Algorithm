import java.util.*;
import java.io.*;

public class Main {

	static int V;
	static int E;
	static int[] colors;
	static ArrayList<ArrayList<Integer>> graph;
	static final int RED = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=K; tc++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();
			colors = new int[V+1];
			for(int i=0; i<=V; i++) {
				graph.add(new ArrayList<>());
			}
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				
				graph.get(fromVertex).add(toVertex);
				graph.get(toVertex).add(fromVertex);
			}
			
			boolean graphCheck = false;
			for(int i=1; i<=V; i++) {
				if(colors[i] == 0) {
					graphCheck = isBipartiteGraphCheck(i, RED);
				}
				
				if(!graphCheck) {
					break;
				}
			}
			
			if(graphCheck) {
				sb.append("YES").append("\n");
			}
			else {
				sb.append("NO").append("\n");
			}
		}
		System.out.print(sb);

	}
	
	public static boolean isBipartiteGraphCheck(int start, int color) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		colors[start] = color;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int next: graph.get(now)) {
				if(colors[next] == colors[now]) {
					return false;
				}
				
				if(colors[next] == 0) {
					colors[next] = colors[now] * -1;
					queue.add(next);
				}
			}
		}
		
		return true;
		
		
	}

}
