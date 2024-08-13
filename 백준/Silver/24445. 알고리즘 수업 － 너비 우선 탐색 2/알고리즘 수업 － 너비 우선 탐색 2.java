import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int R;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] sequenceArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		visited = new boolean[N+1];
		sequenceArr = new int[N+1];
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}
		
		for (int i=1; i<=N; i++) {
			Collections.sort(graph.get(i), Collections.reverseOrder());
		}
		
		bfs();
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			sb.append(sequenceArr[i]).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(R);
		visited[R] = true;
		int order = 1;
		sequenceArr[R] = order;
		
		while (!queue.isEmpty()) {
			int nowVertex = queue.poll();
			
			for (int next: graph.get(nowVertex)) {
				if (!visited[next]) {
					queue.add(next);
					visited[next] = true;
					order++;
					sequenceArr[next] = order;
				}
			}
		}
	}

}