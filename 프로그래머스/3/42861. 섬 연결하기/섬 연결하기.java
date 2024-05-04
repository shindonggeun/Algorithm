import java.util.*;

class Solution {
    
    // 간선의 정보를 담은 내부 클래스
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
    
    static int[] parents;   // 크루스칼 알고리즘(유니온 파인드 응용)을 이용하기 위한 부모 배열
    static ArrayList<Edge> edgeList;    // 간선 목록를 저장하는 리스트
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents = new int[n+1]; // [1] ~ [n]
        edgeList = new ArrayList<>();
        
        for (int i=1; i<=n; i++) {
            parents[i] = i; // 각 정점의 부모는 자기 자신을 가리키도록 설정
        }
        
        for (int[] cost: costs) {
            int fromVertex = cost[0];
            int toVertex = cost[1];
            int weight = cost[2];
            
            // 간선 정보를 간선 리스트에 저장
            edgeList.add(new Edge(fromVertex, toVertex, weight));
        }
        
        // 크루스칼 알고리즘을 이용하기 위해 (최소신장트리) 간선의 가중치 기준으로 오름차순 정렬
        Collections.sort(edgeList, (a, b) -> a.weight - b.weight);
        
        int totalWeight = 0;    // 최소신장트리를 형성할 때 드는 비용
        
        // 모든 간선에 대해서 순회
        for (Edge edge: edgeList) {
            int root1 = find(edge.fromVertex);  // 시작 정점의 루트
            int root2 = find(edge.toVertex);    // 도착 정점의 루트
            
            // 두 정점의 루트가 다른 경우 
            if (root1 != root2) {
                union(edge.fromVertex, edge.toVertex);  // 두 트리를 합치게끔 유니온 연산 수행
                totalWeight += edge.weight; // 최소신장트리를 형성할 때 드는 최소 비용에 해당 간선의 가중치 누적
            }
        }
        
        answer = totalWeight;
        
        return answer;
    }
    
    // 유니온 파인드에서의 파인드 메서드
    public int find(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
    
    // 유니온 파인드에서의 유니온 메서드
    public void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        
        if (aRoot == bRoot) {
            return;
        }
        else if(aRoot > bRoot) {
            parents[aRoot] = bRoot;
        }
        else {
            parents[bRoot] = aRoot;
        }
    }
}