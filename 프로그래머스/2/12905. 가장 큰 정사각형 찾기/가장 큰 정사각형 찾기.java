class Solution {
    public int solution(int [][]board) {
        int answer = 0;
        
        int row = board.length; // 행의 개수
        int col = board[0].length; // 열의 개수
        int maxSide = 0; // 가장 큰 정사각형의 변의 길이
        
        // 행 또는 열이 1개인 경우
        if (row == 1 || col == 1) {
            return 1; // 정사각형의 최대 크기는 1이므로 1 반환
        }
        
        // 두번째 행부터 마지막 행까지 순회
        for (int i=1; i<row; i++) {
            // 두번째 열부터 마지막 열까지 순회
            for (int j=1; j<col; j++) {
                // 현재 좌표의 값이 1인 경우
                if (board[i][j] == 1) {
                    // 현재 위치에 해당하는 값에 정사각형의 왼족 상단, 왼쪽, 위쪽 중 최소값을 구해서 + 1 해줌 
                    board[i][j] = Math.min(board[i-1][j-1], Math.min(board[i-1][j], board[i][j-1])) + 1;
                    // 최대 변의 길이를 갱신
                    maxSide = Math.max(maxSide, board[i][j]);
                }
            }
        }
        
        // 가장 큰 정사각형의 넓이 계산
        answer = maxSide * maxSide;
            
        return answer;
    }
}