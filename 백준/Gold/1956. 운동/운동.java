import java.util.*;
import java.io.*;

public class Main {
	
	static int V;
	static int E;
	static int[][] dist;
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dist = new int[V+1][V+1];
		for(int i=1; i<=V; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			dist[fromVertex][toVertex] = weight;
		}
		
		// 플로이드-워셜 알고리즘 이용
		for(int k=1; k<=V; k++) {
			for(int i=1; i<=V; i++) {
				for(int j=1; j<=V; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		
		int minCycleDistance = INF;
		
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i == j) {
					continue;
				}
				// 자기 자신을 제외한 두 정점이
                // 서로에게 가는 경로가 있다면, 사이클이 존재한다는 의미
				if(dist[i][j] != INF && dist[j][i] != INF) {
		            minCycleDistance = Math.min(minCycleDistance, dist[i][j] + dist[j][i]);
		        }
			}
		}
		
		if(minCycleDistance == INF) {
			System.out.println(-1);
		}
		else {
			System.out.println(minCycleDistance);
		}

	}

}
