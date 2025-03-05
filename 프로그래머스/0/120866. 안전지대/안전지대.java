import java.util.*;

class Solution {
    
    static class Position {
        int x;
        int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int n;
    // 상, 상 + 우, 우, 하 + 우, 하, 하 + 좌, 좌, 상 + 좌
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static boolean[][] visited;
    static List<Position> positionList;
    
    public int solution(int[][] board) {
        int answer = 0;
        
        n = board.length;
        visited = new boolean[n][n]; // [0][0] ~ [n-1][n-1]
        positionList = new ArrayList<>();
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == 1) {
                    positionList.add(new Position(i, j));
                    visited[i][j] = true;
                }
            }
        }
        
        for (Position pos: positionList) {
            mineCheck(pos.x, pos.y);
        }
        
        answer = dangerCountCheck();
        
        return answer;
    }
    
    public void mineCheck(int x, int y) {
        for (int i=0; i<8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            
            if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) {
                continue;
            }
            
            if (visited[nextX][nextY]) {
                continue;
            }
            
            visited[nextX][nextY] = true;
        }
    }
    
    public int dangerCountCheck() {
        int count = 0;
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (!visited[i][j]) {
                    count++;
                }
            }
        }
        
        return count;
    }
}