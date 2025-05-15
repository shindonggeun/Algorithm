import java.util.*;

class Solution {
    
    static class Position {
        int x;
        int y;
        int move;
        
        public Position(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
    
    static int N;
    static int M;
    static char[][] board;
    // 4가지 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        int answer = 0;
        
        N = maps.length;
        M = maps[0].length();
        
        board = new char[N][M];
        
        int startX = 0;
        int startY = 0;
        int leverX = 0;
        int leverY = 0;
        int endX = 0;
        int endY = 0;
        
        for (int i=0; i<N; i++) {
            String miro = maps[i];
            for (int j=0; j<M; j++) {
                board[i][j] = miro.charAt(j);
                
                if (board[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
                else if (board[i][j] == 'L') {
                    leverX = i;
                    leverY = j;
                }
                else if (board[i][j] == 'E') {
                    endX = i;
                    endY = j;
                }
            }
        }
        
        int startToLever = bfs(startX, startY, leverX, leverY);
        int leverToEnd = bfs(leverX, leverY, endX, endY);
        
        if (startToLever == -1 || leverToEnd == -1) {
            answer = -1;
        }
        else {
            answer = startToLever + leverToEnd;
        }
        
        return answer;
    }
    
    public int bfs(int startX, int startY, int endX, int endY) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY, 0));
        boolean[][] visited = new boolean[N][M];
        visited[startX][startY] = true;
        
        while (!queue.isEmpty()) {
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowMove = now.move;
            
            if (nowX == endX && nowY == endY) {
                return nowMove;
            }
            
            for (int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }
                
                if (board[nextX][nextY] == 'X' || visited[nextX][nextY]) {
                    continue;
                }
                
                visited[nextX][nextY] = true;
                queue.add(new Position(nextX, nextY, nowMove + 1));
            }
        }
        
        return -1;
    }
}