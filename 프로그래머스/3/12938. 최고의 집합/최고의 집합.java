import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if (s < n) {
            return new int[] {-1};
        }
        
        int baseValue = s / n;
        int remainder = s % n;
        
        int[] answer = new int[n];
        
        Arrays.fill(answer, baseValue);
        
        for (int i=0; i<remainder; i++) {
            answer[n-1-i]++;
        }
        
        return answer;
    }
}