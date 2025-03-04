import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int start = 1;
        int end = distance;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            
            if (checkRemoveRocks(rocks, distance, mid, n)) {
                answer = mid;
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        
        return answer;
    }
    
    public boolean checkRemoveRocks(int[] rocks, int distance, int mid, int n) {
        int removeCount = 0; // 제거한 바위 개수
        int prevPos = 0; // 이전 위치 (출발 지점)
        
        for (int rock: rocks) {
            if (rock - prevPos < mid) {
                removeCount++;
            }
            else {
                prevPos = rock;
            }
            
            if (removeCount > n) {
                return false;
            }
        }
        
        if (distance - prevPos < mid) {
            removeCount++;
        }
        
        return removeCount <= n;
    }
    
}