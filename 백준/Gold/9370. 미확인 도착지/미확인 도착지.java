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
	
	static int T;
	static int[] distFromS;
	static int[] distFromG;
	static int[] distFromH;
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<ArrayList<Edge>> graph;
	static boolean[] visited;
	static int[] destination;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());	// 교차로 (정점) 개수 입력
			int m = Integer.parseInt(st.nextToken());	// 도로 (간선) 개수 입력
			int t = Integer.parseInt(st.nextToken());	// 목적지 후보 개수 입력
			
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());	// 출발지 입력
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();
			
			distFromS = new int[n+1];
			distFromG = new int[n+1];
			distFromH = new int[n+1];
			
			visited = new boolean[n+1];
			destination = new int[t];
			
			for (int i=0; i<=n; i++) {
				graph.add(new ArrayList<>());
			}
			
			for (int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				graph.get(fromVertex).add(new Edge(toVertex, weight));
				graph.get(toVertex).add(new Edge(fromVertex, weight));
			}
			
			for (int i=0; i<t; i++) {
				destination[i] = Integer.parseInt(br.readLine());
			}
			
			// 다익스트라 알고리즘 실행
			dijkstra(s, distFromS);
			dijkstra(g, distFromG);
			dijkstra(h, distFromH);
			
			// 결과 검증 및 출력
            ArrayList<Integer> validDestinations = new ArrayList<>();
            for (int dest : destination) {
                int distViaGH = distFromS[g] + distFromG[h] + distFromH[dest];
                int distViaHG = distFromS[h] + distFromH[g] + distFromG[dest];
                if (distFromS[dest] == distViaGH || distFromS[dest] == distViaHG) {
                    validDestinations.add(dest);
                }
            }

            Collections.sort(validDestinations);
            for (int res : validDestinations) {
            	sb.append(res).append(" ");
            }
            sb.append("\n");
		}
		
		System.out.print(sb);
		
	}
	
	public static void dijkstra(int vertex, int[] dist) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(vertex, 0));
		Arrays.fill(dist, INF);
		Arrays.fill(visited, false);
		dist[vertex] = 0;
		
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			if (!visited[nowVertex]) {
				visited[nowVertex] = true;
				
				for (Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight;
					if (cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));
					}
				}
			}
		}
	}

}