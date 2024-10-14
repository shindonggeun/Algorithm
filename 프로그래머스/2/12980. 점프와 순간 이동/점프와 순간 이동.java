import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int batteryUseCount = 0; // 건전지 사용량
        
        // 도착위치부터 탐색 (거꾸로 탐색) -> 도착위치에서 시작 위치까지 반복
        while (n > 0) {
            // n이 짝수가 아닌 경우 (즉, 홀수인 경우)
            if (n % 2 != 0) {
                n--; // 해당 도착 위치 감소시킨 뒤
                // 도착 위치(n)가 홀수였으므로 점프해서 가야함 (건전지 사용)
                batteryUseCount++; // 건전지 사용량 증가
            }
            // n이 짝수인 경우 순간이동 가능 (건전지 사용 안함)
            n /= 2;
        }
        
        answer = batteryUseCount; // 결과값에 건전지 사용량 저장

        return answer;
    }
    
}