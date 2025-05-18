import java.util.*;

class Solution {
    
    // 간선 정보를 담은 내부 클래스
    static class Edge {
        int fromVertex; // 시작 정점
        int toVertex; // 도착 정점
        int weight; // 가중치
        
        public Edge(int fromVertex, int toVertex, int weight) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }
    
    static List<Edge> edgeList; // 간선들을 저장한 리스트
    static int[] parents; // 각 정점들의 부모 정점을 저장하는 배열 (유니온 파인드) 
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        edgeList = new ArrayList<>(); // 리스트 초기화
        parents = new int[n]; // [0] ~ [n-1]
        
        // 1. 각 정점들 초기 부모 정점은 자기 자신으로 초기화
        for (int i=0; i<n; i++) {
            parents[i] = i;
        }
        
        // 2. 정점들간의 연결 (간선)
        for (int i=0; i<costs.length; i++) {
            int fromVertex = costs[i][0];
            int toVertex = costs[i][1];
            int weight = costs[i][2];
            
            edgeList.add(new Edge(fromVertex, toVertex, weight));
        }
        
        // 3. 크루스칼 알고리즘을 이용한 최소 신장 트리 만들기
        Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
        
        int totalMinWeight = 0; // MST 비용
        for (Edge edge: edgeList) {
            // 두 정점의 부모가 다른 경우
            if (find(edge.fromVertex) != find(edge.toVertex)) {
                union(edge.fromVertex, edge.toVertex); // 두 정점 합치기
                totalMinWeight += edge.weight; // 최소 신장 트리 만드는데 드는 최소 비용 해당 간선의 가중치로 누적
            }
        }
        
        answer = totalMinWeight; // 결과값
        
        return answer;
    }
    
    // 유니온 파인드에서 파인드를 담당하는 메서드
    public int find(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
    
    // 유니온 파인드에서 유니온을 담당하는 메서드
    public void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        
        if (aRoot != bRoot) {
            parents[aRoot] = bRoot;
        }
    }
}