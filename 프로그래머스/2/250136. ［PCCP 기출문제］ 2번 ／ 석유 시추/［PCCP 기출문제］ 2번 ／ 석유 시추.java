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
    
    static int N;
    static int M;
    static boolean[][] visited;
    // 4가지 방향 배열 (하, 상, 좌, 우)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Map<Integer, Integer> map;
    static int maxOil;
    
    public int solution(int[][] land) {
        int answer = 0;
        
        N = land.length;
        M = land[0].length;
        visited = new boolean[N][M];
        map = new HashMap<>();
        int landNum = 1;
        maxOil = 0;
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (!visited[i][j] && land[i][j] == 1) {
                    int landCount = bfs(i, j, landNum, land);
                    map.put(landNum, landCount);
                    landNum++;
                }
            }
        }
        
        for (int i=0; i<M; i++) {
            int totalOil = oilCheck(i, land);
            maxOil = Math.max(maxOil, totalOil);
        }
        
        answer = maxOil;
        return answer;
    }
    
    public static int bfs(int startX, int startY, int landNum, int[][] land) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY));
        visited[startX][startY] = true;
        land[startX][startY] = landNum;
        int landCount = 1;
        
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
                
                if (visited[nextX][nextY] || land[nextX][nextY] == 0) {
                    continue;
                }
                
                queue.add(new Position(nextX, nextY));
                visited[nextX][nextY] = true;
                land[nextX][nextY] = landNum;
                landCount++;
            }
        }
        
        return landCount;
    }
    
    public static int oilCheck(int y, int[][] land) {
        int nowX = 0;
        boolean[] landCheck = new boolean[map.size()+1];
        int tempOil = 0;
        while (true) {
            if (nowX >= N) {
                break;
            }
            
            int landNum = land[nowX][y];
            if (landNum != 0 && !landCheck[landNum]) {
                tempOil += map.get(landNum);
                landCheck[landNum] = true;
            }
            
            nowX++;
        }
        
        return tempOil;
    }
}