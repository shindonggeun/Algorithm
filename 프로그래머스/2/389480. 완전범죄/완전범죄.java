class Solution {
    public int solution(int[][] info, int n, int m) {
        int itemCount = info.length;
        
        // dp[a][b] = A의 누적 흔적이 a, B의 누적 흔적이 b일 때 가능한지 여부
        boolean[][] dp = new boolean[n][m];
        boolean[][] nextDp = new boolean[n][m];
        
        // 초기 상태: 아무것도 훔치지 않은 상태
        dp[0][0] = true;
        
        // 모든 물건에 대해 처리
        for (int i = 0; i < itemCount; i++) {
            int aTrace = info[i][0];  // A가 훔칠 때 흔적
            int bTrace = info[i][1];  // B가 훔칠 때 흔적
            
            // nextDp 초기화
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m; b++) {
                    nextDp[a][b] = false;
                }
            }
            
            // 이전 상태에서 전이
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m; b++) {
                    if (!dp[a][b]) continue;
                    
                    // A가 이 물건을 훔치는 경우
                    if (a + aTrace < n) {
                        nextDp[a + aTrace][b] = true;
                    }
                    
                    // B가 이 물건을 훔치는 경우
                    if (b + bTrace < m) {
                        nextDp[a][b + bTrace] = true;
                    }
                }
            }
            
            // dp 업데이트
            boolean[][] temp = dp;
            dp = nextDp;
            nextDp = temp;
        }
        
        // A의 누적 흔적 최솟값 찾기 (핵심 변경!)
        int answer = -1;
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                if (dp[a][b]) {
                    answer = a;  // 가장 작은 a부터 찾으므로 첫 발견이 최솟값
                    return answer;
                }
            }
        }
        
        return answer;
    }
}