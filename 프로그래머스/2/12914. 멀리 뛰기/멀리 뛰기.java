import java.util.*;

class Solution {
    public long solution(int n) {
        long answer = 0;
        // 칸에 도달하는 경우의 수를 저장하는 dp 배열
        // dp[i] => i번째 칸에 도달하는 경우의 수
        long[] dp = new long[n+1];  // [1] ~ [n]
        
        // 초기값 세팅
        dp[0] = 1;
        dp[1] = 1;
        
        // 상향식으로 풀이
        for (int i=2; i<=n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1234567;
        }
        
        answer = dp[n];
        return answer;
    }
    
}