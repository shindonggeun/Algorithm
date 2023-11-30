import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge {
		int toVertex;
		int weight;
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;
	static int M;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;
	static boolean[] visited;
	static int[] prev;	
	static ArrayList<ArrayList<Edge>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			dist = new int[M];
			visited = new boolean[M];
			prev = new int[M];
			graph = new ArrayList<>();
			
			for(int i=0; i<M; i++) {
				graph.add(new ArrayList<>());
				dist[i] = INF;
				prev[i] = -1;
			}
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				graph.get(fromVertex).add(new Edge(toVertex, weight));
				graph.get(toVertex).add(new Edge(fromVertex, weight));
			}
			
			dijkstra(0);
			sb.append("Case #").append(tc).append(": ");
			
			if(dist[M-1] == INF) {
				sb.append(-1);
			}
			else {
				// 스택을 이용한 경로 추적
				Stack<Integer> path = new Stack<>();
				// 최고의원(M-1번 정치인)에서 시작하여 한신이(0번 정치인)까지 역순으로 경로 추적
				int nowVertex = M-1; // 현재 정점을 최고의원으로 설정
				// 한신이에 도달할 때까지 반복
				while (nowVertex != 0) { 
				    path.push(nowVertex); // 현재 정점을 경로에 추가
				    nowVertex = prev[nowVertex]; // 이전 정점으로 이동
				}
				path.push(0); // 한신이(0번 정치인)도 경로에 추가
				
				while(!path.isEmpty()) {
					sb.append(path.pop()).append(" ");
				}
			}
			
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	public static void dijkstra(int vertex) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(vertex, 0));
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			if(!visited[nowVertex]) {
				visited[nowVertex] = true;
				
				for(Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight;
					
					if(cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;
						prev[next.toVertex] = nowVertex;	// 경로 추적을 위해 이전 정점 저장
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));
					}
				}
			}
		}
	}

}
