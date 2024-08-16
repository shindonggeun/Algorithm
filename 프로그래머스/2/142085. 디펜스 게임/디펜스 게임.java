import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int usedTotalSoilder = 0;
        boolean allRoundDefense = true;
        
        for (int i=0; i<enemy.length; i++) {
            pq.add(enemy[i]);
            usedTotalSoilder += enemy[i];
            
            if (usedTotalSoilder > n) {
                if (k > 0) {
                    usedTotalSoilder -= pq.poll();
                    k--;
                }
                else {
                    allRoundDefense = false;
                    answer = i;
                    break;
                }
            }
        }
        
        if (allRoundDefense) {
            answer = enemy.length;
        }
        
        return answer;
    }
}