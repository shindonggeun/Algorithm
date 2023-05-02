import java.util.*;

class Solution {
    static boolean[][] visited;
    static int N;
    static int M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};    // x방향 배열 (상하)
    static int[] dy = {0, 0, -1, 1};    // y방향 배열 (좌우)
    
    
    public int solution(int[][] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N][M];
        map = maps;
        
        bfs(0, 0);  // 좌표 (0, 0)에서부터 탐색 시작
        if(map[N-1][M-1] == 1) {
            answer = -1;
        }
        else {
            answer = map[N-1][M-1];
        }
        return answer;
    }
    
    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            
            // 상, 하, 좌, 우 이렇게 네가지 방향 탐색
            for(int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }
                
                if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
                    continue;
                }
                
                queue.add(new int[] {nextX, nextY});
                map[nextX][nextY] = map[nowX][nowY] + 1;
                visited[nextX][nextY] = true;
            }
        }
    }
}