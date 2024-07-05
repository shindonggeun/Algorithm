import java.util.*;

class Solution {
    
    static class Edge {
        int toVertex;
        int weight;
        
        public Edge(int toVertex, int weight) {
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }
    
    static ArrayList<ArrayList<Edge>> graph;
    static final int INF = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        graph = new ArrayList<>();
        
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] fare: fares) {
            int fromVertex = fare[0];
            int toVertex = fare[1];
            int weight = fare[2];
            
            graph.get(fromVertex).add(new Edge(toVertex, weight));
            graph.get(toVertex).add(new Edge(fromVertex, weight));
        }
        
        // s, a, b각각에 대해 다익스트라 알고리즘 수행
        int[] distFromS = dijkstra(s, n);
        int[] distFromA = dijkstra(a, n);
        int[] distFromB = dijkstra(b, n);
        
        int minTotalCost = Integer.MAX_VALUE;
        
        // 모든 지점을 환승 지점으로 고려하여 최소 비용 계산하기
        for (int i=1; i<=n; i++) {
            if (distFromS[i] != INF && distFromA[i] != INF && distFromB[i] != INF) {
                int totalCost = distFromS[i] + distFromA[i] + distFromB[i];
                minTotalCost = Math.min(minTotalCost, totalCost);
            }
        }
        
        answer = minTotalCost;
        
        return answer;
    }
    
    public int[] dijkstra(int vertex, int n) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(vertex, 0));
        
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);
        
        dist[vertex] = 0;
        
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int nowVertex = now.toVertex;
            int nowWeight = now.weight;
            
            if (!visited[nowVertex]) {
                visited[nowVertex] = true;
                
                for (Edge next: graph.get(nowVertex)) {
                    int cost = nowWeight + next.weight;
                    if (cost < dist[next.toVertex]) {
                        dist[next.toVertex] = cost;
                        pq.add(new Edge(next.toVertex, cost));
                    }
                }
            }
        }
        
        return dist;
    }
}