class Solution {
    public int solution(int n) {
        int answer = 0;
        // 각 가로길이가 설정될 때(1 ~ n) 직사각형을 채우는 방법의 수 
        int[] dp = new int[n+1];
        
        // 초기값 세팅
        dp[1] = 1; 
        dp[2] = 2;
        
        for (int i=3; i<=n; i++) {
            // 가로 길이가 i일 때, 타일링의 경우의 수는 i-1  경우의 수와 i-2 경우의 수를 합한 경우이다
            // i-1 경우의 수에서 세로 타일 붙임
            // i-2 경우의 수에서 가로 타일을 붙임
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        
        answer = dp[n];
        return answer;
    }
}