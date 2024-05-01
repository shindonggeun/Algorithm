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
        int[] answer = new int[sources.length];
        
        graph = new ArrayList<>();
        dist = new int[n+1];    // [1] ~ [n]
        visited = new boolean[n+1];
        
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
            dist[i] = INF;
        }
        
        for (int i=0; i<roads.length; i++) {
            int fromVertex = roads[i][0];
            int toVertex = roads[i][1];
            
            graph.get(fromVertex).add(new Edge(toVertex, 1));
            graph.get(toVertex).add(new Edge(fromVertex, 1));
        }
        
        dijkstra(destination);
        
        for (int i=0; i<sources.length; i++) {
            int minDistance = dist[sources[i]];
            
            if (minDistance == INF) {
                answer[i] = -1;
            }
            else {
                answer[i] = minDistance;
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