import java.util.*;

class Solution {
    
    static int n; // 공원의 행의 크기
    static int m; // 공원의 열의 크기
    static int[][] dp; // dp 배열 (dp[i][j] = (i, j) 위치에서 끝나는 가능한 최대 정사각형 한 변의 길이)
    
    public int solution(int[] mats, String[][] park) {
        int answer = -1; // 결과값 -1로 세팅 (돗자리 깔 수 없는 경우 -1 반환)
        
        n = park.length;
        m = park[0].length;
        
        dp = new int[n+1][m+1]; // [1][1] ~ [n][m]
        
        // dp 배열 채우는 과정 (즉, 최대 정사각형 크기 구하는 과정)
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                // 해당 좌표가 빈 공간(-1)인 경우
                if (park[i-1][j-1].equals("-1")) {
                    // 정사각형의 크기는 왼쪽, 위쪽, 대각선 위쪽 (왼 + 위) 값의 최소값 + 1
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                }
            }
        }
        
        // 2. 공원 내에서 가능한 최대 정사각형 크기 찾는 과정
        int maxSize = 0;
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                // 공원 내에서 가능한 최대 정사각형 크기 갱신 
                maxSize = Math.max(maxSize, dp[i][j]);
            }
        }
        
        // 3. 돗자리들의 크기 오름차순 정렬
        Arrays.sort(mats);
        
        // 4. 가장 큰 돗자리부터 배치 가능 여부 확인하는 과정
        // 내리차순 정렬같은 효과를 보기 위해 해당 배열 거꾸로 탐색
        for (int i=mats.length-1; i>=0; i--) {
            // 가장 큰 돗자리가 가능한 경우
            if (mats[i] <= maxSize) {
                answer = mats[i];
                break; // 더이상 탐색할 필요 없이 탐색 빠져나옴
            }
        }
        
        // 결과값 반환
        return answer;
    }
}