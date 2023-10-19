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
	
	static int N;	// 방의 개수
	static int M;	// 비밀통로의 개수
	static int K;
	static int[][] dist;
	static final int INF = Integer.MAX_VALUE;
	static boolean[] visited;
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
			
			visited = new boolean[N+1];
			graph = new ArrayList<>();
			
			for(int i=0; i<=N; i++) {
				graph.add(new ArrayList<>());
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				graph.get(fromVertex).add(new Edge(toVertex, weight));
				graph.get(toVertex).add(new Edge(fromVertex, weight));
			}
			
			K = Integer.parseInt(br.readLine());
			dist = new int[K][N+1];
			
			for(int i=0; i<K; i++) {
				Arrays.fill(dist[i], INF);
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<K; i++) {
				int friendNum = Integer.parseInt(st.nextToken());
				Arrays.fill(visited, false);
				dijkstra(friendNum, i);
			}
			
			
			int minTotalDistance = INF; // 초기 최솟값 설정
	        int bestMeetingPlace = -1;

	        for(int i=1; i<=N; i++) {
	            int totalDistance = 0;
	            for(int j=0; j<K; j++) {
	                totalDistance += dist[j][i]; // i번 방을 모임 장소로 선택했을 때, 모든 친구의 이동 거리의 합
	            }

	            if(totalDistance < minTotalDistance) {
	                minTotalDistance = totalDistance;
	                bestMeetingPlace = i; // 최솟값 갱신시 모임 장소 업데이트
	            }
	        }
	        
	        sb.append(bestMeetingPlace).append("\n");
		}
		System.out.print(sb);
		
	}
	
	public static void dijkstra(int start, int idx) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));
		dist[idx][start] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int to = now.toVertex;
			int weight = now.weight;
			
			if(!visited[to]) {
				visited[to] = true;
				
				for(Edge next: graph.get(to)) {
					int cost = next.weight + weight;
					if(cost < dist[idx][next.toVertex]) {
						dist[idx][next.toVertex] = cost;
						pq.add(new Edge(next.toVertex, dist[idx][next.toVertex]));
					}
				}
			}
		}
	}

}
