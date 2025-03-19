class Solution {
    
    // 4가지 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n; // 행의 개수
    static int m; // 열의 개수
    
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        n = board.length; // 행의 개수 저장
        m = board[0].length; // 열의 개수 저장
        
        // 해당 시작 칸에서부터 같은 색이 몇 칸 있는지 구하기
        answer = getSameColorCount(h, w, board);
        
        return answer;
    }
    
    // 해당 시작 칸에서부터 같은 색이 몇 칸 있는지 구하는 메서드
    public int getSameColorCount(int nowX, int nowY, String[][] board) {
        String nowColor = board[nowX][nowY]; // 해당 시작 칸의 색상
        int count = 0; // 해당 시작 칸의 색상과 같은 색의 개수
        
        // 4가지 방향 탐색
        for (int i=0; i<4; i++) {
            int nextX = nowX + dx[i];
            int nextY = nowY + dy[i];
            
            // 탐색한 좌표가 [0][0] ~ [n-1][m-1] 이외의 좌표인 경우
            if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) {
                continue; // 다음 방향 탐색하도록 넘어감
            }
            
            // 탐색한 좌표의 색상이 시작 칸의 색상과 같은 경우
            if (board[nextX][nextY].equals(nowColor)) {
                count++; // 같은 색의 개수 증가
            }
        }
        
        return count;
    }
}