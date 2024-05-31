import java.util.*;
import java.io.*;

public class Main {
	
	// 간선들의 정보를 담고 있는 내부 클래스
	static class Edge {
		int toVertex;	// 도착 도시(정점)
		int weight;	// 거리 (가중치)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int T;	// 테스트 케이스 개수
	static int[] distFromS;	// 출발지 s에서 각 정점까지의 최단 거리를 저장한 배열
	static int[] distFromG;	// 정점(도시) g에서 각 정점까지의 최단 거리를 저장한 배열
	static int[] distFromH;	// 정점(도시) h에서 각 정점까지의 최단 거리를 저장한 배열
	static final int INF = Integer.MAX_VALUE;	// 다익스트라 알고리즘에서 사용할 무한대를 나타내는 상수
	static ArrayList<ArrayList<Edge>> graph;	// 각 정점마다 간선이 연결된 걸 표현한 그래프 (인접 리스트)
	static boolean[] visited;	// 각 도시 (정점)들의 방문 여부를 나타내는 배열
	static int[] destination;	// 목적지 후보들이 담긴 배열
	
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
			
			distFromS = new int[n+1];	// [1] ~ [n]
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
			
			// 결과 검증 및 출력 단계
			// 문제 조건에 맞는 목적지 후보들을 저장하기 위한 리스트 선언 및 생성
            List<Integer> validDestinations = new ArrayList<>();
            
            for (int dest : destination) {
            	// s -> g -> h -> 목적지 후보(dest) 경로의 길이 계산
            	// distFromS[g]: 출발지 s에서 g까지의 최단 거리
            	// distFromG[h]: 정점(도시) g에서 h까지의 최단 거리
            	// distFromH[dest]: 정점(도시) h에서 목적지 후보(dest)까지의 최단 거리
                int distViaGH = distFromS[g] + distFromG[h] + distFromH[dest];
                
                // s -> h -> g -> 목적지 후보 (dest) 경로의 길이 계산
                // distFromS[h]: 출발지 s에서 h까지의 최단 거리
            	// distFromH[g]: 정점(도시) h에서 g까지의 최단 거리
            	// distFromG[dest]: 정점(도시) g에서 목적지 후보(dest)까지의 최단 거리
                int distViaHG = distFromS[h] + distFromH[g] + distFromG[dest];
                
                // 출발지 s에서 목적지 후보 dest까지의 최단 거리가 s -> g -> h -> 목적지 후보 (dest) 경로의 길이와 같거나 또는
                // 출발지 s에서 목적지 후보 dest까지의 최단 거리가 s -> h -> g -> 목적지 후보 (dest) 경로의 길이와 같은 경우 
                if (distFromS[dest] == distViaGH || distFromS[dest] == distViaHG) {
                    validDestinations.add(dest);	// 유효한 목적지 후보임으로 추가
                }
            }

            Collections.sort(validDestinations);	// 유효한 목적지 후보 리스트 오름차순 정렬
            for (int res : validDestinations) {
            	sb.append(res).append(" ");
            }
            sb.append("\n");
		}
		
		System.out.print(sb);
		
	}
	
	// 출발지에서 도착지까지 최소 거리를 계산해줄 다익스트라 메서드
	public static void dijkstra(int vertex, int[] dist) {
		// 다익스트라 알고리즘을 사용하기 위해 우선순위 큐 선언 및 생성
		// 각 간선들 가중치를 기준으로 오름차순 정렬
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.add(new Edge(vertex, 0));	// 우선순위큐에 출발 도시 정보 저장
		Arrays.fill(dist, INF);	// 해당 도시(정점)에서 부터 출발하여 각 정점까지들의 최단 거리를 저장할 배열 무한대로 초기화
		Arrays.fill(visited, false);	// 각 도시(정점)들의 방문 여부를 나타낼 배열 초기화
		dist[vertex] = 0;	// 출발 도시 최소 거리 0으로 초기화
		
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 해당 도시 정보 뽑아냄
			Edge now = pq.poll();
			int nowVertex = now.toVertex;
			int nowWeight = now.weight;
			
			// 해당 도시 방문하지 않은 경우
			if (!visited[nowVertex]) {
				visited[nowVertex] = true;	// 해당 도시 방문 처리
				
				// 해당 됫와 연결된 도로들(간선들) 탐색
				for (Edge next: graph.get(nowVertex)) {
					int cost = nowWeight + next.weight;	// 해당 도시와 연결된 도시로 가는데 드는 거리 계산
					
					// 해당 거리가 해당 도시의 최소 거리보다 작은 경우
					if (cost < dist[next.toVertex]) {
						dist[next.toVertex] = cost;	// 해당 도시의 최소 거리 갱신
						// 우선순위 큐에 해당 도시 정보 저장
						pq.add(new Edge(next.toVertex, dist[next.toVertex]));
					}
				}
			}
		}
	}

}