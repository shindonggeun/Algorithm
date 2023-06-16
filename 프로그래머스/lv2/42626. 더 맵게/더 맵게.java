import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 작은값이 우선순위 높음
        
        // 스코빌지수 우선순위 큐에 저장
        for(int i=0; i<scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        int count = 0;
        while(true) { 
            int hot1 = pq.poll();   // 가장 맵지 않은 음식의 스코빌 지수
            // 가장 맵지않음 음식의 스코빌 지수가 K 이상인 경우 무한반복 빠져나옴
            // 즉 모든 음식의 스코빌 지수가 K 이상이 된거임
            if(hot1 >= K) {
                break;
            }
            // 우선순위 큐 크기가 0인 경우
            if(pq.size() == 0) {
                // 모든 음식의 스코빌 지수를 K이상으로 만들 수 없는 경우
                // 즉 가장 맵지 않은 음식의 스코빌 지수가 K 미만인 경우 -1 리턴할 수 있게끔
                if(hot1 < K) {
                    count = -1;
                    break;
                }
            }
            
            int hot2 = pq.poll();   // 두번째로 맵지 않은 음식의 스코빌 지수
            int sum = hot1 + (hot2 * 2);
            pq.add(sum);
            count++;    // 섞은 횟수 증가
        }
        
        answer = count;
        
        return answer;
    }
}