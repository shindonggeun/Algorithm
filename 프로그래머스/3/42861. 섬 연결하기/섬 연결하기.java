import java.util.*;

class Solution {
    
    static class Edge {
        int fromVertex;
        int toVertex;
        int weight;
        
        public Edge(int fromVertex, int toVertex, int weight) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }
    
    static List<Edge> edgeList;
    static int[] parents;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        edgeList = new ArrayList<>();
        parents = new int[n]; // [0] ~ [n-1]
        
        for (int i=0; i<n; i++) {
            parents[i] = i;
        }
        
        for (int i=0; i<costs.length; i++) {
            int fromVertex = costs[i][0];
            int toVertex = costs[i][1];
            int weight = costs[i][2];
            
            edgeList.add(new Edge(fromVertex, toVertex, weight));
        }
        
        Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
        
        int totalMinWeight = 0;
        for (Edge edge: edgeList) {
            if (find(edge.fromVertex) != find(edge.toVertex)) {
                union(edge.fromVertex, edge.toVertex);
                totalMinWeight += edge.weight;
            }
        }
        
        answer = totalMinWeight;
        
        return answer;
    }
    
    public int find(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
    
    public void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        
        if (aRoot != bRoot) {
            parents[aRoot] = bRoot;
        }
    }
}