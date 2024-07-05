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
    
    static boolean[][] visited;
    // 4가지 방향 배열 (하, 상, 좌, 우)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int numberOfArea;
    static int maxSizeOfOneArea;
    
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        
        visited = new boolean[m][n]; // [0][0] ~ [m-1][n-1]
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    bfs(m, n, picture, i, j);
                    numberOfArea++;
                }
            }
        }
        
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public void bfs(int m, int n, int[][] picture, int startX, int startY) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY));
        visited[startX][startY] = true;
        
        int color = picture[startX][startY];
        int count = 1;
        
        while (!queue.isEmpty()) {
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            for (int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                if (nextX < 0 || nextY < 0 || nextX >= m || nextY >= n) {
                    continue;
                }
                
                if (visited[nextX][nextY] || picture[nextX][nextY] == 0) {
                    continue;
                }
                
                if (picture[nextX][nextY] == color) {
                    queue.add(new Position(nextX, nextY));
                    visited[nextX][nextY] = true;
                    count++;
                }
            }
        }
        
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, count);
    }
}