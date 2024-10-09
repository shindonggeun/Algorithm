import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int work: works) {
            pq.add(work);
        }
        
        while (n > 0 && !pq.isEmpty()) {
            int maxWork = pq.poll();
            if (maxWork > 0) {
                maxWork--;
                n--;
                pq.add(maxWork);
            }
        }
        
        while (!pq.isEmpty()) {
            int work = pq.poll();
            answer += (long) work * work;
        }
        
        
        return answer;
    }
}