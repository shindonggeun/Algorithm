class Solution {
    
    static final int MOD = 1_000_000_007;
    
    public int solution(int n, int[] money) {
        int answer = 0;
        
        // 해당 돈(원)을 만드는 방법의 수를 저장할 배열
        // dp[i]는 i원을 만드는 방법의 수
        int[] dp = new int[n+1]; // [0] ~ [n]
        dp[0] = 1;  // 0원을 만드는 방법의 수는 아무것도 사용하지 않은 경우 즉, 공집합인 경우이므로 1가지
        
        // money 배열에 있는 동전들 탐색
        for (int coin: money) {
            // 해당 동전부터 사용해서 n원까지 1원씩 늘려가며 탐색
            for (int i=coin; i<=n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % MOD;
            }
        }
        
        answer = dp[n];
        
        return answer;
    }
}