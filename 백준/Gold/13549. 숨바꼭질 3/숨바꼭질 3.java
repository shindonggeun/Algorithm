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
	
	static int N;	// 수빈이가 있는 위치
	static int K;	// 동생이 있는 위치
	static int[] dist;
	static boolean[] visited;
	static final int MAX = 100000;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dist = new int[MAX + 1];
		visited = new boolean[MAX + 1];
		Arrays.fill(dist, INF);
		
		dijkstra(N);
		System.out.println(dist[K]);
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int nowPosition = now.toVertex;
			int nowTime = now.weight;
			
			if(!visited[nowPosition]) {
				visited[nowPosition] = true;
				
				if(nowPosition - 1 >= 0 && dist[nowPosition -1] > nowTime + 1) {
					dist[nowPosition - 1] = nowTime + 1;
					pq.add(new Edge(nowPosition - 1, nowTime + 1));
				}
				
				if(nowPosition + 1 <= MAX && dist[nowPosition + 1] > nowTime + 1) {
					dist[nowPosition + 1] = nowTime + 1;
					pq.add(new Edge(nowPosition + 1, nowTime + 1));
				}
				
				if(nowPosition * 2 <= MAX && dist[nowPosition * 2] > nowTime) {
					dist[nowPosition * 2] = nowTime;
					pq.add(new Edge(nowPosition * 2, nowTime));
				}
			}
		}
	}

}
