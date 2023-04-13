import java.util.*;

class Solution {
    public long solution(int price, int money, int count) {
        long answer = 0;
        long sum = 0;
        
        // 놀이기구 count번 만큼 타기
        for(int i=1; i<=count; i++) {
            sum+=price * i;
        }
        if(money > sum) {
            answer = 0;
        }
        else {
            answer = sum - money;
        }
        
        return answer;
    }
}