class Solution {
    
    static final int MOD = 1_000_000_007;
    
    public int solution(int n, int[] money) {
        int answer = 0;
        
        // 해당 돈(원)을 만드는 방법의 수를 저장할 배열
        // dp[i]는 i원을 만드는 방법의 수
        int[] dp = new int[n+1]; // [0] ~ [n]
        dp[0] = 1;
        
        for (int coin: money) {
            for (int i=coin; i<=n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % MOD;
            }
        }
        
        answer = dp[n];
        
        return answer;
    }
}