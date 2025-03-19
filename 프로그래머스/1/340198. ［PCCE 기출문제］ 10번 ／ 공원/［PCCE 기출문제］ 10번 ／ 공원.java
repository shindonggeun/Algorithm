import java.util.*;

class Solution {
    
    static int n;
    static int m;
    static int[][] dp;
    
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        
        n = park.length;
        m = park[0].length;
        
        dp = new int[n+1][m+1];
        
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (park[i-1][j-1].equals("-1")) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
            }
        }
        
        int maxSize = 0;
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                maxSize = Math.max(maxSize, dp[i][j]);
            }
        }
        
        Arrays.sort(mats);
        
        for (int i=mats.length-1; i>=0; i--) {
            if (mats[i] <= maxSize) {
                answer = mats[i];
                break;
            }
        }
        
        return answer;
    }
}