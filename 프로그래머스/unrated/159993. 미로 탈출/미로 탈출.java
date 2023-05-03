import java.util.*;

class Solution {
    static class Position {
        int x, y, distance;
        
        public Position(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};    // x방향 배열(상, 하)
    static int[] dy = {0, 0, -1, 1};    // y방향 배열(좌, 우)
    
    public int solution(String[] maps) {
        int answer = 0;
        int lengthX = maps.length;
        int lengthY = maps[0].length();
        
        map = new char[lengthX][lengthY];
        visited = new boolean[lengthX][lengthY];
        Position startPos = null;   // 시작지점 위치
        Position leverPos = null;   // 레버가 있는 위치
        Position endPos = null;     // 통로지점 위치
        
        for(int i=0; i<lengthX; i++) {
            for(int j=0; j<lengthY; j++) {
                if(maps[i].charAt(j) == 'S') {
                    startPos = new Position(i, j, 0);
                }
                else if(maps[i].charAt(j) == 'L') {
                    leverPos = new Position(i, j, 0);
                }
                else if(maps[i].charAt(j) == 'E') {
                    endPos = new Position(i, j, 0);
                }
                map[i][j] = maps[i].charAt(j);
            } 
        }
        
        // 먼저 레버까지의 최소시간(최단거리) 찾아주기
        answer = bfs(startPos.x, startPos.y, lengthX, lengthY, leverPos.x, leverPos.y); 
        
        // 레버까지의 최소시간 찾은 경우
        if(answer > -1) {
            visited = new boolean[lengthX][lengthY];    // 다시 맵에서 방문여부 판단해주는 배열 초기화
            int temp = bfs(leverPos.x, leverPos.y, lengthX, lengthY, endPos.x, endPos.y);   // 레버에서부터 통로지점까지의 최단거리 탐색
            
            // 레버에서부터 통로지점까지의 최단거리 찾지 못한 경우(탈출하지 못하는 경우)
            if(temp == -1) {
                answer = -1;    
            }
            // 레버에서부터 통로지점까지의 최단거리 찾은 경우(탈출 가능한 경우)
            else {
                answer += temp; // 시작지점에서 레버까지의 최단거리 탐색한거 + 레버지점부터 통로지점까지의 최단거리 탐색한거
            }
        }
        return answer;
    }
    // 너비우선탐색 이용
    public static int bfs(int startX, int startY, int lengthX, int lengthY, int endX, int endY) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY, 0));
        visited[startX][startY] = true;
        
        while(!queue.isEmpty()) {
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowDistance = now.distance;
            
            // 목표지점 좌표에 도달한 경우 최소시간(최단거리) 반환하기
            if(nowX == endX && nowY == endY) {
                return nowDistance;
            }
            // 상하좌우 4가지 방향 탐색하기
            for(int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                // 이동한 x, y좌표가 범위를 벗어난 경우 (음수좌표 또는 (x끝 좌표-1, y끝 좌표-1) 좌표 넘어간 경우)
				// 맵의 좌표는 (0,0) ~ (lengthX-1, lengthY-1) 까지이다
                if(nextX < 0 || nextY < 0 || nextX >= lengthX || nextY >= lengthY) {
                    continue;
                }
                // 방향 탐색한 좌표가 이미 방문했거나 또는 벽으로 막힌 경우
                if(visited[nextX][nextY] || map[nextX][nextY] == 'X') {
                    continue;
                }
                // 원래 좌표값((0,0) 좌표에서 탐색한 좌표까지 거리)에서 +1 하여 방향 탐색한 좌표에 저장
                queue.add(new Position(nextX, nextY, nowDistance + 1)); 
                visited[nextX][nextY] = true;   // 해당좌표 방문처리
            }
        }
        // 위의 너비우선탐색을 다 했는데도 못찾는 경우면 -1 리턴
        return -1;
    }
}