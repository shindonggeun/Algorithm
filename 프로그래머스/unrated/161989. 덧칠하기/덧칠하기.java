import java.util.*;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        
        boolean[] paint = new boolean[n+1];
        Arrays.fill(paint,true);    // paint 전체 칠해졌다고 가정(초기값을 전부 true로 설정)
        
        for(int i=0; i<section.length; i++) {
            paint[section[i]] = false;  // 페인트 안칠해진 곳 설정 (false)
        }
        
        // 페인트 안칠해진 구역 탐색
        for(int i=0; i<section.length; i++) {
            // 페인트칠 배열에서 페인트가 안칠해졌다면
            if(paint[section[i]] != true) {
                int count = 0;  // 롤러 길이(배열 한 방당 1씩 증가)
                // 정해진 롤러길이가 되기전까지 반복해줌
                while(count < m) {
                    // 페인트칠 배열길이 넘어가지 않도록 조건 걸어줌
                    if(section[i] + count < paint.length) {
                        paint[section[i] + count] = true;   // 페인트칠 완료
                    }
                    count++;    // 롤러 길이 증가(다음 배열 탐색하게끔)
                }
                answer++;   // 페인트칠 횟수 증가
            }
        }
        
        return answer;
    }
    
}