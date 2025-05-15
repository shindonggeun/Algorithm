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
    // 4가지 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        visited = new boolean[m][n];
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    int areaSize = bfs(m, n, i, j, picture);
                    maxSizeOfOneArea = Math.max(areaSize, maxSizeOfOneArea);
                    
                    numberOfArea++;
                }
            }
        }
 
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int bfs(int m, int n, int startX, int startY, int[][] picture) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY));
        visited[startX][startY] = true;
        int color = picture[startX][startY];
        
        int areaCount = 1;
        
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
                
                if (picture[nextX][nextY] != color || visited[nextX][nextY]) {
                    continue;
                }
                
                visited[nextX][nextY] = true;
                queue.add(new Position(nextX, nextY));
                areaCount++;
            }
        }
        
        return areaCount;
    }
}