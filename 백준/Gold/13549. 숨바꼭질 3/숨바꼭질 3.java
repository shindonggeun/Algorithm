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
	static int K;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 100000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dist = new int[MAX+1];
		Arrays.fill(dist, INF);
		
		bfsZeroOne(N);
		
		System.out.println(dist[K]);
	}
	
	public static void bfsZeroOne(int start) {
		Deque<Edge> deque = new ArrayDeque<>();
		deque.add(new Edge(start, 0));
		dist[start] = 0;
		
		while(!deque.isEmpty()) {
			Edge now = deque.poll();
			int nowPosition = now.toVertex;
			int nowTime = now.weight;
			
			if(nowPosition * 2 <= MAX && dist[nowPosition * 2] > nowTime) {
				dist[nowPosition * 2] = nowTime;
				deque.addFirst(new Edge(nowPosition * 2, nowTime));
			}
			
			if(nowPosition - 1 >= 0 && dist[nowPosition - 1] > nowTime + 1) {
				dist[nowPosition - 1] = nowTime + 1;
				deque.addLast(new Edge(nowPosition - 1, nowTime + 1));
			}
			
			if(nowPosition + 1 <= MAX && dist[nowPosition + 1] > nowTime + 1) {
				dist[nowPosition + 1] = nowTime + 1;
				deque.addLast(new Edge(nowPosition + 1, nowTime + 1));
			}
		}
	}

}
