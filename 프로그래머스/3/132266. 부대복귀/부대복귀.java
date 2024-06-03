import java.util.*;

class Solution {
    
    // 간선의 정보를 저장할 내부 클래스
    static class Edge {
        int toVertex; // 도착 지역 (정점)
        int weight; // 도착하는데 걸리는 시간 (가중치)
        
        public Edge(int toVertex, int weight) {
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }
    
    static int[] dist; // 시작 지역번호(정점)에서 부터 시작하여 각 지역(정점)까지의 최단거리를 저장할 배열
    static final int INF = Integer.MAX_VALUE; // 다익스트라 알고리즘에서 사용할 무한대를 나타내는 상수
    static boolean[] visited; // 각 정점마다 방문여부를 나타내는 배열
    static ArrayList<ArrayList<Edge>> graph; // 각 정점마다 연결된 간선들을 저장할 그래프 (인접 리스트)
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};
        
        dist = new int[n+1]; // [1] ~ [n]
        visited = new boolean[n+1];
        graph = new ArrayList<>();
        
        for (int i=0; i<=n; i++) {
            dist[i] = INF; // 각 정점들의 최단거리 무한대로 초기화
            graph.add(new ArrayList<>());
        }
        
        for (int[] road: roads) {
            int fromVertex = road[0]; // 출발 지역 (출발 정점)
            int toVertex = road[1]; // 도착 지역 (도착 정점)
            int weight = 1; // 도착하는데 걸리는 시간 (가중치)
            
            // 양방향 간선 연결
            graph.get(fromVertex).add(new Edge(toVertex, weight));
            graph.get(toVertex).add(new Edge(fromVertex, weight));
        }
        
        // 도착지에서부터 출발하여 다익스트라 알고리즘 실행
        dijkstra(destination);
        
        answer = new int[sources.length];
        
        for (int i=0; i<sources.length; i++) {
            int vertex = sources[i];
            // 해당 지역(정점)의 최단거리가 무한대인 경우 (즉, 복귀 불가능)
            if (dist[vertex] == INF) {
                answer[i] = -1;
            }
            else {
                answer[i] = dist[vertex];    
            }
        }
        
        return answer;
    }
    
    // 출발지역에서 부터 각 지역(정점)마다 최단거리를 구해줄 다익스트라 메서드
    public void dijkstra(int vertex) {
        // 다익스트라 알고리즘을 이용하기 위해 우선순위 큐 선언 및 생성
        // 가중치를 기준으로 오름차순 정렬
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(vertex, 0)); // 우선순위 큐에 해당 시작 간선 정보 저장
        dist[vertex] = 0; // 시작 정점의 최단거리 0으로 초기화
        
        while (!pq.isEmpty()) {
            // 우선순위 큐에서 해당 간선 정보 뽑아냄
            Edge now = pq.poll();
            int nowVertex = now.toVertex;
            int nowWeight = now.weight;
            
            // 해당 정점을 방문하지 않은 경우
            if (!visited[nowVertex]) {
                visited[nowVertex] = true; // 해당 정점 방문 처리
                
                // 해당 정점에 연결된 간선들 탐색
                for (Edge next: graph.get(nowVertex)) {
                    // 해당 지역(정점)과 연결된 지역(정점)까지로 가는데 드는 시간(거리) 계산
                    int cost = nowWeight + next.weight;
                    // 해당 시간(거리_이 해당 지역(정점)의 최소 비용(최단 거리)보다 작은 경우
                    if (cost < dist[next.toVertex]) {
                        dist[next.toVertex] = cost; // 최단 거리 갱신
                        // 우선순위 큐에 해당 간선 정보 저장
                        pq.add(new Edge(next.toVertex, cost));
                    }
                }
            }
        }
    }
}