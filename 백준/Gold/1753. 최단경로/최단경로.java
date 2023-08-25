import java.util.*;
import java.io.*;

public class Main {
	
	static class Node {
		int vertex;
		int weight;
		Node next;
		
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());	// 정점의 개수
		int E = Integer.parseInt(st.nextToken());	// 간선의 개수
		int k = Integer.parseInt(br.readLine()) - 1;	// 시작 정점 번호
		
		// 다익스트라 알고리즘을 이용하기 위해 인접리스트 이용하기
		Node[] adjList = new Node[V];	// 노드를 이용한 인접리스트
		int[] distance = new int[V];	// 시작점에서 자신으로 오는 최단 거리
		boolean[] visited = new boolean[V];	// 각 정점들 방문 여부 처리할 배열
		
		// 간선의 개수만큼 연결해주기
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken()) - 1;
			int toVertex = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[fromVertex] = new Node(toVertex, weight, adjList[fromVertex]);
		}
		
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(distance, INF);
		
		distance[k] = 0;	// 시작지점의 최단거리 0으로
		int min = 0;
		int stopOver = 0;	// 경유지
		
		for(int i=0; i<V; i++) {	// 모든 정점을 다 처리할 때 까지 반복
			// step1: 미방문 정점 중 출발지에서 가장 가까운 정점을 경유지로 선택
			stopOver = -1;
			min = INF;	// 최단거리 INF로 설정
			for(int j=0; j<V; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					stopOver = j;
				}
			}
			
			// 경유지가 -1인 경우
			if(stopOver == -1) {
				break;
			}
			
			// step2: 방문처리
			visited[stopOver] = true;
			
			// step3: 경유지를 이용하여 미방문 정점들의 출발지에서 자신으로의 최소비용 고려
			for(Node temp = adjList[stopOver]; temp != null; temp = temp.next) {
				if(!visited[temp.vertex] &&
						distance[temp.vertex] > min + temp.weight) {
					distance[temp.vertex] = min + temp.weight;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<V; i++) {
			if(distance[i] == INF) {
				sb.append("INF").append("\n");
			}
			else {
				sb.append(distance[i]).append("\n");
			}
		}
		System.out.print(sb);
	}

}
