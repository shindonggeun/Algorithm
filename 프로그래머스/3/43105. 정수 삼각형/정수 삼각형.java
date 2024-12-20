class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length; // 삼각형의 높이 (행의 개수)
        
        // 삼각형의 두 번째 줄(행)부터 마지막 줄까지 계산하는 과정 (맨 위의 첫 줄은 이미 시작점이므로)
        for (int i=1; i<n; i++) {
            // 각 줄의 각 요소를 차례대로 계산
            for (int j=0; j<triangle[i].length; j++) {
                // 각 줄의 첫 번째 요소인 경우 (맨 왼쪽)
                if (j == 0) {
                    // 맨 왼쪽에 있는 숫자에 그 위에 있는 숫자를 더해줌 (누적)
                    triangle[i][j] += triangle[i-1][j];
                }
                // 각 줄의 마지막 요소인 경우 (맨 오른쪽)
                else if (j == i) {
                    // 맨 오른쪽에 있는 숫자에 바로 위의 왼쪽 숫자를 더해줌 (누적)
                    triangle[i][j] += triangle[i-1][j-1];
                }
                // 각 줄의 중간에 위치한 요소인 경우
                else {
                    // 위의 두 숫자 중 더 큰 값을 선택해서 더해줌 (경로 최대값 선정을 위해)
                    triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j-1]);
                }
                
                // 지금까지 계산한 값 중 최대값 갱신 (삼각형의 맨 마지막 행에서 최대값이 나옴)
                answer = Math.max(answer, triangle[i][j]);
            }
        }
        
        return answer;
    }
}