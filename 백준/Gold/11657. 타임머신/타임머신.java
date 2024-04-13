import java.util.*;
import java.io.*;

public class Main {
	
	// 시작도시와 도착도시, 버스를 타고 이동화는데 걸리는 시간을 담은 내부 클래스
	// 벨만포드 알고리즘을 이용하기 위해 fromVertex (시작 도시)가 있다. (다익스트라 알고리즘과 다른 점)
	static class Edge {
		int fromVertex;
		int toVertex;
		int weight;
		
		public Edge(int fromVertex, int toVertex, int weight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 도시의 개수
	static int M;	// 버스의 개수
	static Edge[] edges;
	static long[] dist;	// 1번 도시에서 시작해 각 도시까지 가는 최단 경로를 담은 배열
	static final long INF = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new Edge[M];
		dist = new long[N+1];	// [1] ~ [N]
		Arrays.fill(dist, INF);
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(fromVertex, toVertex, weight);
		}
		
		// 벨만포드 알고리즘 이용해서 음수 사이클이 존재 하는 경우
		// 즉, 1번 도시에서 출발해 어떤 도시로 가는 과정에서 시간 무한히 오래 전으로 되돌릴 수 있는 경우
		if (bellmanFord(1)) {
			System.out.println(-1);
		}
		else {	// 음수 사이클이 존재하지 않은 경우
			StringBuilder sb = new StringBuilder();
			// 2번 도시부터 탐색
			for (int i=2; i<=N; i++) {
				// 1번 도시에서 출발해 해당 도시로 가는 경로가 없는 경우
				if (dist[i] == INF) {
					sb.append(-1).append("\n");
				}
				else {
					sb.append(dist[i]).append("\n");
				}
			}
			System.out.print(sb);
		}
		
	}
	
	// 벨만포드 알고리즘을 이용한 메서드
	public static boolean bellmanFord(int start) {
		dist[start] = 0;
		
		// 1. 정점의 개수만큼 반복
		for (int i=1; i<=N; i++) {
			// 1. 모든 간선에 대해서 확인하기
			for (Edge edge: edges) {
				if (dist[edge.fromVertex] != INF && dist[edge.toVertex] > dist[edge.fromVertex] + edge.weight) {
					dist[edge.toVertex] = dist[edge.fromVertex] + edge.weight;
				}
			}
		}
		
		// 2. 음수 사이클 존재하는지 확인하기
		for (Edge edge : edges) {
			if (dist[edge.fromVertex] != INF && dist[edge.toVertex] > dist[edge.fromVertex] + edge.weight) {
				return true;	// 음수 사이클 존재
			}
		}
		
		// 그 이외는 음수 사이클 존재하지 않음
		return false;
	}

}
