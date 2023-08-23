import java.util.*;

class Solution {
    
    static PriorityQueue<Integer> pq;
    
    public int solution(int[] cards) {
        int answer = 0;
        boolean[] visited = new boolean[cards.length];
        pq = new PriorityQueue<>(Collections.reverseOrder());    // 우선순위 큐 선언 및 생성 (우선순위값은 숫자 높은게 높음)
        
        for(int i=0; i<cards.length; i++) {
            if(!visited[i]) {
                dfs(cards, i, visited, 0);
            }
        }
        
        // 우선순위큐의 크기가 1이 아닌 경우 상자 그룹이 여러개 있음
        if(pq.size() != 1) {
            answer = pq.poll() * pq.poll();
        }
        // 그 이외의 경우는 0점임
        return answer;
    }
    
    // 깊이우선탐색 메서드
    public static void dfs(int[] cards, int num, boolean[] visited, int count) {
        // 이미 방문했던 숫자이면(즉, 이미 담았던 카드번호인 경우) (종료조건)
        if(visited[num]) {
            pq.add(count);  // 우선순위큐에 해당 그룹에 속한 상자 수 집어넣기
            return; // 메서드 종료
        }
        
        visited[num] = true;    // 카드 번호 상자에 담음
        // 재귀호출
        // 처음에 문제에서 2 ~ 100 이하의 자연수 하나 정해 그 수보다 작거나 같은 숫자 카드들을 준비한다 했으므로
        // 해당 카드번호 - 1해서 깊이우선탐색을 실시 해준다
        dfs(cards, cards[num] - 1, visited, count+1);
    }
}