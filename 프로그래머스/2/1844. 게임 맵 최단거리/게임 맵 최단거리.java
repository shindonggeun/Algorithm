import java.util.*;

class Solution {
    
    static class Position {
        int x;
        int y;
        int distance;
        
        public Position(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    
    static int N;
    static int M;
    static boolean[][] visited;
    // 4가지 방향 배열 (하, 상, 좌, 우)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length;
        
        visited = new boolean[N][M];    // [0][0] ~ [N-1][M-1]
        
        answer = bfs(0, 0, maps);
        
        return answer;
    }
    
    public int bfs(int startX, int startY, int[][] map) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY, 1));
        visited[startX][startY] = true;
        
        while (!queue.isEmpty()) {
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowDistance = now.distance;
            
            if (nowX == N-1 && nowY == M-1) {
                return nowDistance;
            }
            
            // 4가지 방향 탐색 (하, 상, 좌, 우)
            for (int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }
                
                if (visited[nextX][nextY] || map[nextX][nextY] == 0) {
                    continue;
                }
                
                queue.add(new Position(nextX, nextY, nowDistance + 1));
                visited[nextX][nextY] = true;
            }
        }
        
        return -1;
    }
}