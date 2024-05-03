import java.util.*;

class Solution {
    
    // 좌표 정보를 담고있는 내부 클래스
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
    
    static int N;   // 행의 크기
    static int M;   // 열의 크기
    static boolean[][] visited; // 방문배열
    // 4가지 방향 배열 (하, 상, 좌, 우)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length;
        
        visited = new boolean[N][M];    // [0][0] ~ [N-1][M-1]
        
        answer = bfs(0, 0, maps);   // 시작위치에서부터 너비우선탐색 실시
        
        return answer;
    }
    
    // 너비우선탐색 메서드
    public int bfs(int startX, int startY, int[][] map) {
        // 너비우선탐색을 실시하기 위해 큐 선언 및 생성
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY, 1)); // 시작위치 및 거리 정보 큐에 저장
        visited[startX][startY] = true; // 시작위치 방문처리
        
        // 너비우선탐색 실시
        while (!queue.isEmpty()) {
            // 현재 좌표 정보 뽑기
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowDistance = now.distance;
            
            // 현재 좌표가 도착지점에 도달한 경우
            if (nowX == N-1 && nowY == M-1) {
                return nowDistance; // 현재까지의 거리 반환
            }
            
            // 4가지 방향 탐색 (하, 상, 좌, 우)
            for (int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                // 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;   // 다음 방향 탐색하도록 넘어감
                }
                
                // 탐색한 좌표가 이미 방문했거나 또는 벽이 있는 자리(0)인 경우
                if (visited[nextX][nextY] || map[nextX][nextY] == 0) {
                    continue;   // 다음 방향 탐색하도록 넘어감
                }
                
                // 탐색한 좌표 정보 큐에 저장
                queue.add(new Position(nextX, nextY, nowDistance + 1));
                visited[nextX][nextY] = true;   // 탐색한 좌표 방문처리
            }
        }
        
        // 너비우선탐색 실시했는데도 도착지점에 도달하지 못한 경우 -1 반환
        return -1;
    }
}