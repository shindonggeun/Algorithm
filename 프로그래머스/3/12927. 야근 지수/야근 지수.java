import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        // 작업량 중 최대값을 빠르게 찾기 위해 우선순위 큐 선언 및 생성 (최대힙 -> 큰 값이 우선순위 높음) 
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        // 작업들 탐색
        for (int work: works) {
            pq.add(work); // 주어진 작업들 우선순위 큐에 저장
        }
        
        // 1. 퇴근까지 남은 시간까지 남은 일들 작업하는 과정 (야근)
        // 퇴근까지 남은 시간(n)이 0보다 크면서 동시에 우선순위 큐가 비어 있지 않을때까지 반복
        while (n > 0 && !pq.isEmpty()) {
            int maxWork = pq.poll(); // 우선순위 큐에서 현재 가장 남은 일의 작업량이 높은 값을 뽑아냄
            // 우선순위 큐에서 뽑은 현재 가장 남은 일의 작업량이 0보다 큰 경우
            if (maxWork > 0) {
                maxWork--; // 작업량을 감소 (-1)
                n--; // 퇴근까지 남은 시간(n) 감소 (-1)
                pq.add(maxWork); // 작업량 감소한 것 다시 우선순위 큐에 저장
            }
        }
        
        // 2. 남은 작업량들의 피로도 계산하는 과정
        // 우선순위 큐가 비어 있지 않을때까지 반복
        while (!pq.isEmpty()) {
            int work = pq.poll(); // 우선순위 큐에 현재 남은 작업 뽑아냄 
            answer += (long) work * work; // 피로도 계산한 뒤 결과값에 누적
        }
        
        return answer;
    }
}