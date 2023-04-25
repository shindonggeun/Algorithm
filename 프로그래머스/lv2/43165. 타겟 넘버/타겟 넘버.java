import java.util.*;

class Solution {
    static int answer;
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        dfs(numbers, 0, target, 0);
        
        return answer;
    }
    
    public void dfs(int[] numbers, int depth, int target, int sum) {
        // 마지막 노드까지 탐색한 경우 (종료조건)
        if(depth == numbers.length) {
            // 숫자를 적절히 더하고 뺀 것중에 타켓넘버인 경우
            if(target == sum) {
                answer++;   // 방법의 수 증가
            }
            return; 
        }
        else {
            dfs(numbers, depth+1, target, sum + numbers[depth]);    // 해당 노드의 값을 더하고 다음 깊이 탐색
            dfs(numbers, depth+1, target, sum - numbers[depth]);    // 해당 노드의 값을 빼고 다음 깊이 탐색
        }
    }
}