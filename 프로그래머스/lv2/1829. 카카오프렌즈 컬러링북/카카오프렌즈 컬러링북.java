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
    static int[] dx = {1, -1, 0, 0};   // x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
    static int[] dy = {0, 0, -1, 1};   // y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
    static int maxArea;
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];
        
        // (0, 0) ~ (n-1, m-1)
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(!visited[i][j] && picture[i][j] != 0) {
                    maxArea = 0;
                    bfs(i, j, m, n, picture ,picture[i][j]);
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, maxArea);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public static void bfs(int startX, int startY, int m, int n, int[][] picture, int value) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY));
        visited[startX][startY] = true;
        maxArea++;
        
        while(!queue.isEmpty()) {
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            // 4가지 방향 탐색(상, 하, 좌, 우)
            for(int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                // 탐색한 좌표가 (0, 0) ~ (n-1, m-1) 이외의 좌표인 경우
                if(nextX < 0 || nextY < 0 || nextX >= m || nextY >= n) {
                    continue;
                }
                
                // 탐색한 좌표가 이미 방문처리 되어있거나 같은 영역값이 아닌 경우
                if(visited[nextX][nextY] || picture[nextX][nextY] != value) {
                    continue;
                }
                
                queue.add(new Position(nextX, nextY));
                visited[nextX][nextY] = true;
                maxArea++;
            }
        }
    }
}