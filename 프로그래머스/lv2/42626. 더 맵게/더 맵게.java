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
            int hot1 = pq.poll();
            if(hot1 >= K) {
                break;
            }
            
            if(pq.size() == 0) {
                if(hot1 < K) {
                    count = -1;
                    break;
                }
            }
            
            int hot2 = pq.poll();
            int sum = hot1 + (hot2 * 2);
            pq.add(sum);
            count++;
        }
        
        answer = count;
        
        return answer;
    }
}