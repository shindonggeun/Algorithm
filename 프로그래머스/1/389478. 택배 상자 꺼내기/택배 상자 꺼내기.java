class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        
        // 1. num의 좌표 찾기
        int row = (num - 1) / w;
        int col = 0;
        
        if (row % 2 == 0) {
            // 짝수 행: 왼쪽에서 오른쪽으로 진행 (왼 -> 오)
            col = (num - 1) % w;
        } else {
            // 홀수 행: 오른쪽에서 왼쪽으로 진행 (오 -> 왼) 
            col = w - 1 - ((num - 1) % w); 
        }
        
        // 2. 위에 있는 상자들 제거
        int count = 1; // num 자기 자신
        int totalRows = (n + w - 1) / w;
        
        for (int r = row + 1; r < totalRows; r++) {
            int boxNum = 0;
            if (r % 2 == 0) {
                boxNum = r * w + col + 1;
            } else {
                boxNum = r * w + (w - 1 - col) + 1;
            }
            
            if (boxNum <= n) {
                count++;
            }
        }
        
        answer = count;
        return answer;
    }
}