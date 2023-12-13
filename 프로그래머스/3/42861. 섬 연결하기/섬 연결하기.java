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
    static boolean[] visited;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        graph = new ArrayList<>();
        visited = new boolean[n];
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] cost: costs) {
            int fromVertex = cost[0];
            int toVertex = cost[1];
            int weight = cost[2];
            
            graph.get(fromVertex).add(new Edge(toVertex, weight));
            graph.get(toVertex).add(new Edge(fromVertex, weight));
        }
        
        answer = prim(0);
        return answer;
    }
    
    public static int prim(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(start, 0));
        int totalWeight = 0;
        
        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            int nowVertex = now.toVertex;
            int nowWeight = now.weight;
            if(!visited[nowVertex]) {
                visited[nowVertex] = true;
                totalWeight += nowWeight;
                
                for(Edge next: graph.get(nowVertex)) {
                    if(!visited[next.toVertex]) {
                        pq.add(next);
                    }
                }
                
            }
        }
        
        return totalWeight;
    }
}