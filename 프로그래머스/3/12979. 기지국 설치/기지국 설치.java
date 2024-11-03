import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int coverage = 2 * w + 1;
        int idx = 0;
        int count = 0;
        
        for (int station: stations) {
            int startOfCoverage = station - w;
            if (idx + 1 < startOfCoverage) {
                int length = startOfCoverage - (idx + 1);
                count += Math.ceil((double) length / coverage);
            }
            idx = station + w;
        }
        
        if (idx < n) {
            int length = n - idx;
            count += Math.ceil((double) length / coverage);
        }
        
        answer = count;
        
        return answer;
    }
}