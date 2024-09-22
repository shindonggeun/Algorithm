class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[n+1][m+1];
        
        for (int[] puddle: puddles) {
            dp[puddle[1]][puddle[0]] = -1; // 물에 잠긴곳은 -1로 표시
        }
        
        dp[1][1] = 1; // 시작점 초기화
        
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                // 물에 잠긴 지역(-1)인 경우
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                
                if (i > 1) {
                    dp[i][j] += dp[i-1][j];
                }
                
                if (j > 1) {
                    dp[i][j] += dp[i][j-1];
                }
                
                dp[i][j] %= 1_000_000_007;
            }
        }
        
        answer = dp[n][m];
        
        return answer;
    }
}