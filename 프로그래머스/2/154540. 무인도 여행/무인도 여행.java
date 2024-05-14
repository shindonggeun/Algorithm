import java.util.*;

class Solution {
    
    // 좌표 정보를 담고 있는 내부 클래스
    static class Position {
        int x;
        int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int N;
    static int M;
    static char[][] map;
    static boolean[][] visited;
    static List<Integer> islandList;
    // 4가지 방향 배열 (하, 상, 좌, 우)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        
        N = maps.length;
        M = maps[0].length();
        
        map = new char[N][M];   // [0][0] ~ [N-1][M-1];
        visited = new boolean[N][M];
        islandList = new ArrayList<>();
        
        for (int i=0; i<N; i++) {
            String input = maps[i];
            for (int j=0; j<M; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (!visited[i][j] && map[i][j] != 'X') {
                    bfs(i, j);
                }
            }
        }
        
        if (islandList.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
        }
        else {
            Collections.sort(islandList);
            answer = new int[islandList.size()];
            
            for (int i=0; i<islandList.size(); i++) {
                answer[i] = islandList.get(i);
            }
        }
        
        
        return answer;
    }
    
    public static void bfs(int startX, int startY) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY));
        visited[startX][startY] = true;
        int totalDay = map[startX][startY] - '0';
        
        while (!queue.isEmpty()) {
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            for (int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }
                
                if (visited[nextX][nextY] || map[nextX][nextY] == 'X') {
                    continue;
                }
                
                queue.add(new Position(nextX, nextY));
                visited[nextX][nextY] = true;
                totalDay += (map[nextX][nextY] - '0');
            }
        }
        
        islandList.add(totalDay);
    }
}