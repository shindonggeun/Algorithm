import java.util.*;

class Solution {
    public long solution(int price, int money, int count) {
        long answer = 0;
        long sum = 0;
        
        // 놀이기구 count번 만큼 타기
        for(int i=1; i<=count; i++) {
            sum+=price * i;
        }
        
        // 자신이 가지고 있는 금액이 부족하지 않은 경우(놀이기구 이용료 다 내도 남는 경우)
        if(money > sum) {
            answer = 0; // 0으로 초기화
        }
        // 금액이 부족한 경우
        else {
            answer = sum - money;   // 부족한 금액 계산
        }
        
        return answer;
    }
}