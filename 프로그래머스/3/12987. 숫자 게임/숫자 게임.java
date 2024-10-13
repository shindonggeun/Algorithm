import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = -1;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int n = A.length;
        int aIdx = 0;
        int bIdx = 0;
        int score = 0;
        
        for (int i=0; i<n; i++) {
            if (A[aIdx] > B[bIdx]) {
                bIdx++;
            }
            else if (A[aIdx] == B[bIdx]) {
                bIdx++;
            }
            else {
                aIdx++;
                bIdx++;
                score++;
            }
        }
        
        answer = score;
        return answer;
    }
    
}