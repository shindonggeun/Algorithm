class Solution {
    
    // 4가지 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n;
    static int m;
    
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        n = board.length;
        m = board[0].length;
        
        answer = getSameColorCount(h, w, board);
        
        return answer;
    }
    
    public int getSameColorCount(int nowX, int nowY, String[][] board) {
        String nowColor = board[nowX][nowY];
        int count = 0;
        
        for (int i=0; i<4; i++) {
            int nextX = nowX + dx[i];
            int nextY = nowY + dy[i];
            
            if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) {
                continue;
            }
            
            if (board[nextX][nextY].equals(nowColor)) {
                count++;
            }
        }
        
        return count;
    }
}