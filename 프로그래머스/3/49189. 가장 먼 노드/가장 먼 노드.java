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
    static ArrayList<ArrayList<Edge>> graph;
    static boolean[] visited;
    static int maxDistance;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        dist = new int[n+1];
        visited = new boolean[n+1];
        graph = new ArrayList<>();
        
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
            dist[i] = INF;
        }
        
        for (int i=0; i<edge.length; i++) {
            int fromVertex = edge[i][0];
            int toVertex = edge[i][1];
            
            graph.get(fromVertex).add(new Edge(toVertex, 1));
            graph.get(toVertex).add(new Edge(fromVertex, 1));
        }
        
        dijkstra(1);
        
        maxDistance = Integer.MIN_VALUE;
        
        findMaxDistance(n);
        
        for (int i=1; i<=n; i++) {
            if (dist[i] == maxDistance) {
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
    
    public void findMaxDistance(int n) {
        for (int i=1; i<=n; i++) {
            if (dist[i] != INF) {
                maxDistance = Math.max(maxDistance, dist[i]);
            }
        }
    }
}