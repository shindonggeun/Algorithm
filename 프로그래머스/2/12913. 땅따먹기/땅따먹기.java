class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int n = land.length;    // 행의 개수
        int m = land[0].length; // 열의 개수
        
        // i번째 행에서 j번째 열을 밟았을 때 얻을 수 있는 최대 점수
        int[][] dp = new int[n][m];
        
        // 첫번째 행에서의 시작 점수는 각 열의 주어진 점수와 동일하므로 해당 작업 실시
        // dp 배열의 첫번째 행 초기화
        for (int j=0; j<m; j++) {
            dp[0][j] = land[0][j];
        }
        
        // 두번째 행부터 실시
        for (int i=1; i<n; i++) {
            // 각 열을 탐색
            for (int j=0; j<m; j++) {
                int max = 0;
                for (int k=0; k<m; k++) {
                    // 같은 열이 아닌 경우 (즉, 같은 열은 제외하게끔)
                    if (j != k) {
                        // 이전 행에서 같은 열을 제외하고 가장 높은 점수 뽑아내기
                        max = Math.max(max, dp[i-1][k]);
                    }
                }
                
                dp[i][j] = land[i][j] + max;    // 현재 행의 점수와 이전 행에서 구한 최대 점수 더해서 dp배열에 저장
            }
        }
        
        // 마지막 행에서의 최대값 찾는 과정
        for (int j=0; j<m; j++) {
            answer = Math.max(answer, dp[n-1][j]);
        }

        return answer;
    }
}