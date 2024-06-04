import java.util.*;

class Solution {
    
    static class Position {
        int x;
        int y;
        int time;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public Position(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    
    static int n;
    static int m;
    static char[][] map;
    static boolean[][] visited;
    // 4가지 방향 배열 (하, 상, 좌, 우)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        int answer = -1;
        
        n = maps.length;
        m = maps[0].length();
        
        map = new char[n][m];
        visited = new boolean[n][m];
        
        Position leverPos = null;
        Position startPos = null;
        Position endPos = null;
        
        for (int i=0; i<n; i++) {
            String input = maps[i];
            for (int j=0; j<m ;j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'S') {
                    startPos = new Position(i, j);
                }
                else if (map[i][j] == 'L') {
                    leverPos = new Position(i, j);
                }
                else if (map[i][j] == 'E') {
                    endPos = new Position(i, j);
                }
            }
        }
        
        // 시작 지점에서부터 레버가 있는 지점까지 너비우선탐색 실시
        int distance1 = bfs(startPos.x, startPos.y, leverPos.x, leverPos.y);
        
        if (distance1 != -1) {
            visited = new boolean[n][m];
            
            int distance2 = bfs(leverPos.x, leverPos.y, endPos.x, endPos.y);
            
            if (distance2 != -1) {
                answer = distance1 + distance2;
            }
        }
        
        return answer;
    }
    
    public int bfs(int startX, int startY, int endX, int endY) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY, 0));
        visited[startX][startY] = true;
        
        while (!queue.isEmpty()) {
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowTime = now.time;
            
            if (nowX == endX && nowY == endY) {
                return nowTime;
            }
            
            // 4가지 방향 탐색
            for (int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                // 탐색한 좌표가 [0][0] ~ [n-1][m-1] 이외의 좌표인 경우
                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) {
                    continue; // 다음 방향 탐색하도록 넘어감
                }
                
                // 탐색한 좌표가 이미 방문했거나 또는 벽('X')인 경우
                if (visited[nextX][nextY] || map[nextX][nextY] == 'X') {
                    continue; // 다음 방향 탐색하도록 넘어감
                }
                
                queue.add(new Position(nextX, nextY, nowTime + 1));
                visited[nextX][nextY] = true;
            }
        }
        
        return -1;
    }
}