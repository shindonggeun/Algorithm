import java.util.*;

class Solution {
    
    // 간선 정보를 담고 있는 내부 클래스
    static class Edge {
        int toVertex;
        int weight;
        
        public Edge(int toVertex, int weight) {
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }
    
    // 각 정점마다 간선 리스트를 저장한 그래프
    static ArrayList<ArrayList<Edge>> graph;
    static int[] dist;  // 시작점에서 각 노드까지의 최소 비용 (최소 배달 시간)
    static boolean[] visited;   // 방문 여부를 저장할 배열
    static final int INF = Integer.MAX_VALUE;   // 초기 거리를 무한대로 설정
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        dist = new int[N+1];    // [1] ~ [N]
        visited = new boolean[N+1];
        graph = new ArrayList<>();  // 그래프 초기화
        
        for (int i=0; i<=N; i++) {
            dist[i] = INF;  // 각 노드의 최소 비용을 무한대로 초기화
            graph.add(new ArrayList<>());       // 각 노드별로 인접 리스트 초기화
        }
        
        for (int i=0; i<road.length; i++) {
            int fromVertex = road[i][0];    // 시작 정점 (마을)
            int toVertex = road[i][1];  // 도착 정점 (마을)
            int weight = road[i][2];    // 가중치 (배달하는데 걸리는 시간)
            
            // 양방향 간선 연결
            graph.get(fromVertex).add(new Edge(toVertex, weight));
            graph.get(toVertex).add(new Edge(fromVertex, weight));
        }
        
        dijkstra(1);    // 1번 마을에서부터 다익스트라 시작

        // 시작정점에서 부터 각 정점까지의 최소 비용 탐색
        for (int cost: dist) {
            // 최소 비용이 (배달하는데 걸리는 최소 시간) 배달 가능한 시간 이하인 경우
            if (cost <= K) {
                answer++;   // 음식 주문 받을 수 있는 마을의 개수 증가
            }
        }
        
        return answer;
    }
    
    // 다익스트라 메서드
    public void dijkstra(int vertex) {
        // 다익스트라 알고리즘을 사용하기 위해 우선순위 큐 선언 및 생성
        // 간선의 가중치를 기준으로 오름차순 정렬
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(vertex, 0));    // 시작 노드 큐에 저장
        dist[vertex] = 0;   // 시작 노드의 최소 비용 0
        
        while (!pq.isEmpty()) {
            // 현재 우선순위큐에 저장된 노드 꺼냄
            Edge now = pq.poll();
            int nowVertex = now.toVertex;
            int nowWeight = now.weight;
            
            // 방문하지 않은 정점인 경우
            if (!visited[nowVertex]) {
                // 해당 정점 방문 처리
                visited[nowVertex] = true;
                
                // 해당 정점에 연결된 인접 노드들 탐색
                for (Edge next: graph.get(nowVertex)) {
                    int cost = nowWeight + next.weight; // 현재 노드를 거쳐가는 비용 계산
                    
                    // 현재 비용이 연결된 노드의 최소 비용보다 작은 경우
                    if (cost < dist[next.toVertex]) {
                        dist[next.toVertex] = cost; // 최소 비용 갱신
                        pq.add(new Edge(next.toVertex, dist[next.toVertex]));
                    }
                }
            }
        }
    }
}