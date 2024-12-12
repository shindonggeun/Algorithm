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
    static Map<Integer, Integer> oilMap;
    // 4가지 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] land) {
        int answer = 0;
        
        N = land.length;
        M = land[0].length;
        
        visited = new boolean[N][M];
        oilMap = new HashMap<>();
        
        int landNum = 1;
        
        // 1. 
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (!visited[i][j] && land[i][j] != 0) {
                    int oilCount = bfs(i, j, land, landNum);
                    oilMap.put(landNum, oilCount);
                    landNum++;
                }
            }
        }
        
        int maxOilSum = 0;
        // 2.
        for (int j=0; j<M; j++) {
            int oilSum = calculateOil(j, land);
            maxOilSum = Math.max(maxOilSum, oilSum);
        }
        
        answer = maxOilSum;
        
        return answer;
    }
    
    public int bfs(int startX, int startY, int[][] land, int landNum) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY));
        visited[startX][startY] = true;
        land[startX][startY] = landNum;
        int oilCount = 1;
        
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
                
                visited[nextX][nextY] = true;
                land[nextX][nextY] = landNum;
                queue.add(new Position(nextX, nextY));
                oilCount++;
            }
        }
        
        return oilCount;
    }
    
    public int calculateOil(int startY, int[][] land) {
        int oilSum = 0;
        boolean[] oilCheck = new boolean[oilMap.size()+1];
        
        for (int i=0; i<N; i++) {
            int landNum = land[i][startY];
            
            if (landNum != 0 && !oilCheck[landNum]) {
                oilSum += oilMap.get(landNum);
                oilCheck[landNum] = true;
            }
        }
        
        return oilSum;
    }
}