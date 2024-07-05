import java.util.*;

class Solution {
    
    // 간선의 정보를 담고 있는 내부 클래스
    static class Edge {
        int toVertex; // 도착지 (도착정점)
        int weight; // 택시요금 (가중치)
        
        public Edge(int toVertex, int weight) {
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }
    
    static ArrayList<ArrayList<Edge>> graph; // 각 정점마다 간선들을 연결한 것들을 표현하기 위한 그래프 (인접 리스트)
    static final int INF = Integer.MAX_VALUE; // 다익스트라 알고리즘에서 사용하는 무한대를 나타내는 상수
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        graph = new ArrayList<>(); // 그래프 생성
        
        // 각 정점마다 인접 리스트 생성
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] fare: fares) {
            int fromVertex = fare[0]; // 시작지 (시작정점)
            int toVertex = fare[1]; // 도착지 (도착정점)
            int weight = fare[2]; // 택시비 (가중치)
            
            // 양방향 간선 연결
            graph.get(fromVertex).add(new Edge(toVertex, weight));
            graph.get(toVertex).add(new Edge(fromVertex, weight));
        }
        
        // s, a, b각각에 대해 다익스트라 알고리즘 수행
        // s(두 사람이 같이 동시에 출발하는 지점)에서부터 각 정점까지의 최단거리 구하기
        int[] distFromS = dijkstra(s, n); 
        // a(A의 도착지점)에서부터 각 정점까지의 최단거리 구하기
        int[] distFromA = dijkstra(a, n);
        // b(B의 도착지점)에서부터 각 정점까지의 최단거리 구하기
        int[] distFromB = dijkstra(b, n);
        
        // 두 사람이 s에서 출발하여 각각의 도착지점까지 갈 때 최저 예상 택시요금
        int minTotalCost = Integer.MAX_VALUE; // 최저 예상 택시요금 최대값으로 초기화
        
        // 모든 지점을 환승 지점으로 고려하여 최소 비용 계산하기
        for (int i=1; i<=n; i++) {
            // 각 출발지(s, a, b)에서부터 시작하여 각 정점까지의 최단거리가 무한대(갈 수 없는 경로)가 아닌 경우
            if (distFromS[i] != INF && distFromA[i] != INF && distFromB[i] != INF) {
                int totalCost = distFromS[i] + distFromA[i] + distFromB[i]; // 해당 정점까지의 택시요금 계산
                minTotalCost = Math.min(minTotalCost, totalCost); // 최저 예상 택시요금 갱신
            }
        }
        
        answer = minTotalCost; // 결과값에 최저 예상 택시요금 저장
        
        return answer;
    }
    
    // 다익스트라 알고리즘을 이용하여 시작 정점에서부터 다른 모든 정점까지의 최단거리를 계산하는 메서드
    public int[] dijkstra(int vertex, int n) {
        // 다익스트라 알고리즘을 이용하기 위해 우선순위 큐 선언 및 생성
        // 간선의 가중치를 기준으로 오름차순 정렬
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(vertex, 0)); // 시작정점 정보 큐에 저장
        
        int[] dist = new int[n+1]; // 시작정점에서부터 각 정점까지의 최단거리를 저장한 배열 [1] ~ [n]
        boolean[] visited = new boolean[n+1]; // 각 정점마다 방문 여부를 나타내는 배열
        Arrays.fill(dist, INF); // 최단거리 배열 무한대로 초기화
        
        dist[vertex] = 0; // 시작 정점 최단 거리 0으로 초기화
        
        while (!pq.isEmpty()) {
            // 우선순위 큐에서 해당 간선 정보 뽑아냄
            Edge now = pq.poll();
            int nowVertex = now.toVertex;
            int nowWeight = now.weight;
            
            // 해당 정점이 방문하지 않은 경우
            if (!visited[nowVertex]) {
                visited[nowVertex] = true; // 해당 정점 방문 처리
                
                // 해당 정점과 연결된 간선들 탐색
                for (Edge next: graph.get(nowVertex)) {
                    int cost = nowWeight + next.weight; // 현재까지의 택시비(가중치)에서 탐색한 정점까지의 택시비 계산
                    // 탐색한 정점까지의 비용이 탐색한 정점의 최소비용(최단거리)보다 작은 경우
                    if (cost < dist[next.toVertex]) {
                        dist[next.toVertex] = cost; // 최단거리 갱신
                        pq.add(new Edge(next.toVertex, cost)); // 우선순위 큐에 탐색한 정정 및 비용 저장
                    }
                }
            }
        }
        
        // 최단거리 다 탐색 완료했으면 해당 최단거리 배열 반환
        return dist;
    }
}