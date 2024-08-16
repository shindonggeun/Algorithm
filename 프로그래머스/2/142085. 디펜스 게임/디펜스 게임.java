import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        // 적의 수를 저장할 우선순위 큐 선언 및 생성
        // 내림차순 정렬 (최대힙)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int usedTotalSoilder = 0; // 현재까지 매 라운드를 막기 위해 사용한 병사 수
        boolean allRoundDefense = true; // 모든 라운드를 막았는지 여부를 체크하는 변수
        
        for (int i=0; i<enemy.length; i++) {
            pq.add(enemy[i]); // 우선순위 큐에 현재 라운드의 적의 수 저장
            usedTotalSoilder += enemy[i]; // 현재까지 라운드를 막기 위해 사용한 병사 수에 적의 수 누적
            
            // 현재까지 라운드를 막기 위해 사용한 병사 수가 초기 병사수(n)보다 큰 경우
            if (usedTotalSoilder > n) {
                // 무적권(k)의 수가 0보다 큰 경우 (즉, 무적권을 사용할 수 있는 경우)
                if (k > 0) {
                    // 무적권을 사용하여 우선순위 큐에 저장된 가장 높은 병사 수를 현재까지 사용한 병사수에 빼줌
                    usedTotalSoilder -= pq.poll(); 
                    k--; // 무적권 사용한 횟수 감소
                }
                // 무적권(k)의 수가 0보다 작거나 같은 경우
                else {
                    allRoundDefense = false; // 모든 라운드 막을 수 없음
                    answer = i; // 해당 라운드의 수 결과값에 저장
                    break; // 더이상 라운드마다 적의 수 탐색할 필요 없이 반복문 빠져나옴
                }
            }
        }
        
        // 모든 라운드를 막을 수 있는 경우
        if (allRoundDefense) {
            answer = enemy.length; // 적의 수가 담긴 배열의 길이 결과값에 저장
        }
        
        return answer;
    }
}