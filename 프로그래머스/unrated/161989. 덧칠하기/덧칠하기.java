import java.util.*;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        
        boolean[] paint = new boolean[n+1];
        Arrays.fill(paint,true);    // paint 전체 칠해졌다고 가정(초기값을 전부 true로 설정)
        
        for(int i=0; i<section.length; i++) {
            paint[section[i]] = false;  // 페인트 안칠해진 곳 설정 (false)
        }
        
        for(int i=0; i<section.length; i++) {
            if(paint[section[i]] != true) {
                int count = 0;
                while(count < m) {
                    if(section[i] + count < paint.length) {
                        paint[section[i] + count] = true;
                    }
                    count++;
                }
                answer++;
            }
        }
        
        return answer;
    }
    
}