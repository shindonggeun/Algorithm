import java.util.*;

class Solution {
    
    static class Block {
        int x;
        int y;
        
        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static char[][] map;
    static boolean[][] visited;
    static int bombCount;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        map = new char[m][n];
        
        for (int i=0; i<m; i++) {
            String input = board[i];
            for (int j=0; j<n; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        
        bombCount = 0;
        
        while (true) {
            boolean bomb = false;
            visited = new boolean[m][n];
            
            for (int i=0; i<m-1; i++) {
                for (int j=0; j<n-1; j++) {
                    if (blockRemoveCheck(i, j)) {
                        bomb = true;
                    }
                }
            }
            
            if (!bomb) {
                break;
            }
            
            
            int removeCount = blockRemove(m, n);
            answer += removeCount;
            blockDown(m, n);
        }
        
        return answer;
    }
    
    public boolean blockRemoveCheck(int nowX, int nowY) {
        char block = map[nowX][nowY];
        
        if (block != '.' && map[nowX][nowY+1] == block && map[nowX+1][nowY+1] == block && map[nowX+1][nowY] == block) {
            visited[nowX][nowY] = true;
            visited[nowX][nowY+1] = true;
            visited[nowX+1][nowY+1] = true;
            visited[nowX+1][nowY] = true;
            return true;
        }
        
        return false;
    }
    
    public int blockRemove(int m, int n) {
        int count = 0;
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (visited[i][j]) {
                    map[i][j] = '.';
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public void blockDown(int m, int n) {
        for (int j=0; j<n; j++) {
            Stack<Character> stack = new Stack<>();
            for (int i=0; i<m; i++) {
                if (map[i][j] != '.') {
                    stack.push(map[i][j]);
                }
            }
            
            for (int i=m-1; i>=0; i--) {
                if (!stack.isEmpty()) {
                    map[i][j] = stack.pop();
                }
                else {
                    map[i][j] = '.';
                }
            }
        }
    }
}