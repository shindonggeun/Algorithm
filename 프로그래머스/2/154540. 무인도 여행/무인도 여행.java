import java.util.*;

class Solution {
    
    static class Island {
        int x;
        int y;
        
        public Island(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int n;
    static int m;
    static char[][] map;
    static boolean[][] visited;
    static List<Integer> dayList;
    // 4가지 방향 배열 (하, 상, 좌, 우)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        
        n = maps.length;
        m = maps[0].length();
        
        map = new char[n][m]; // [0][0] ~ [n-1][m-1]
        visited = new boolean[n][m];
        dayList = new ArrayList<>();
        
        for (int i=0; i<n; i++) {
            String input = maps[i];
            for (int j=0; j<m; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (!visited[i][j] && map[i][j] != 'X') {
                    int day = bfs(i, j);
                    dayList.add(day);
                }
            }
        }
        
        if (dayList.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
        }
        else {
            Collections.sort(dayList);
            answer = new int[dayList.size()];
            
            for (int i=0; i<dayList.size(); i++) {
                answer[i] = dayList.get(i);
            }    
        }
        
        return answer;
    }
    
    public static int bfs(int startX, int startY) {
        Queue<Island> queue = new LinkedList<>();
        queue.add(new Island(startX, startY));
        visited[startX][startY] = true;
        int totalDay = map[startX][startY] - '0';
        
        while (!queue.isEmpty()) {
            Island now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            for (int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) {
                    continue;
                }
                
                if (visited[nextX][nextY] || map[nextX][nextY] == 'X') {
                    continue;
                }
                
                queue.add(new Island(nextX, nextY));
                visited[nextX][nextY] = true;
                totalDay += map[nextX][nextY] - '0';
            }
        }
        
        return totalDay;
    }
}