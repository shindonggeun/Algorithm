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
    static int[] dist;
    static boolean[] visited;
    static final int INF = Integer.MAX_VALUE;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        dist = new int[N+1];    // [1] ~ [N]
        visited = new boolean[N+1];
        graph = new ArrayList<>();
        
        for (int i=0; i<=N; i++) {
            dist[i] = INF;
            graph.add(new ArrayList<>());    
        }
        
        for (int i=0; i<road.length; i++) {
            int fromVertex = road[i][0];
            int toVertex = road[i][1];
            int weight = road[i][2];
            
            graph.get(fromVertex).add(new Edge(toVertex, weight));
            graph.get(toVertex).add(new Edge(fromVertex, weight));
        }
        
        dijkstra(1);

        for (int cost: dist) {
            if (cost <= K) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public void dijkstra(int vertex) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(vertex, 0));
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
                        pq.add(new Edge(next.toVertex, dist[next.toVertex]));
                    }
                }
            }
        }
    }
}