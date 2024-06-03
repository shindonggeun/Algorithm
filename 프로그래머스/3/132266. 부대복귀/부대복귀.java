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
    
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;
    static boolean[] visited;
    static ArrayList<ArrayList<Edge>> graph;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};
        
        dist = new int[n+1];
        visited = new boolean[n+1];
        graph = new ArrayList<>();
        
        for (int i=0; i<=n; i++) {
            dist[i] = INF;
            graph.add(new ArrayList<>());
        }
        
        for (int[] road: roads) {
            int fromVertex = road[0];
            int toVertex = road[1];
            int weight = 1;
            
            graph.get(fromVertex).add(new Edge(toVertex, weight));
            graph.get(toVertex).add(new Edge(fromVertex, weight));
        }
        
        dijkstra(destination);
        
        answer = new int[sources.length];
        
        for (int i=0; i<sources.length; i++) {
            int vertex = sources[i];
            if (dist[vertex] == INF) {
                dist[vertex] = -1;
            }
            answer[i] = dist[vertex];
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
                        pq.add(new Edge(next.toVertex, cost));
                    }
                }
            }
        }
    }
}