import java.util.*;

class Solution {
    
    // 좌표 정보 및 시간 정보를 담은 내부 클래스
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
    
    static int n; // 행의 개수
    static int m; // 열의 개수
    static char[][] map;
    static boolean[][] visited; // 방문 배열
    // 4가지 방향 배열 (하, 상, 좌, 우)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        int answer = -1;
        
        n = maps.length;
        m = maps[0].length();
        
        map = new char[n][m]; // [0][0] ~ [n-1][m-1]
        visited = new boolean[n][m];
        
        Position startPos = null; // 시작 위치 정보를 담은 변수
        Position leverPos = null; // 레버의 위치 정보를 담은 변수
        Position endPos = null; // 도착지 위치 정보를 담은 변수
        
        for (int i=0; i<n; i++) {
            String input = maps[i];
            for (int j=0; j<m ;j++) {
                map[i][j] = input.charAt(j);
                // 해당 좌표가 시작 지점('S')인 경우
                if (map[i][j] == 'S') {
                    startPos = new Position(i, j);
                }
                // 해당 좌표가 레버('L')가 있는 좌표인 경우
                else if (map[i][j] == 'L') {
                    leverPos = new Position(i, j);
                }
                // 해당 좌표가 출구('E')인 경우
                else if (map[i][j] == 'E') {
                    endPos = new Position(i, j);
                }
            }
        }
        
        // 시작 지점에서부터 레버가 있는 지점까지 너비우선탐색 실시
        int distance1 = bfs(startPos.x, startPos.y, leverPos.x, leverPos.y);
        
        // 시작 지점에서 부터 레버가 있는 지점까지의 거리가 -1이 아닌 경우 (즉, 레버까지는 갈 수 있는 경우)
        if (distance1 != -1) {
            visited = new boolean[n][m];
            
            // 레버가 있는 지점에서부터 출구까지 너비우선탐색 실시
            int distance2 = bfs(leverPos.x, leverPos.y, endPos.x, endPos.y);
            
            // 레버가 있는 지점에서부터 출구까지의 거리가 -1이 아닌 경우 (즉, 레버에서부터 통로까지 갈 수 있는 경우)
            // 최종적으로 미로에서 탈출 가능한 경우
            if (distance2 != -1) {
                // 미로에서 탈출하는데 걸리는 시간들 다 더해줌
                answer = distance1 + distance2;
            }
        }
        
        // 최종 결과 반환
        return answer;
    }
    
    // 변수로 넘겨받은 시작 좌표에서부터 도착 좌표까지의 가는데 걸리는 시간을 구해줄 너비우선탐색 메서드 
    public int bfs(int startX, int startY, int endX, int endY) {
        // 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY, 0)); // 시작좌표정보 및 시간 큐에 저장
        visited[startX][startY] = true; // 시작좌표 방문처리
        
        while (!queue.isEmpty()) {
            // 큐에 저장된 현재 좌표 및 시간 정보 뽑아냄
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowTime = now.time;
            
            // 현재 좌표가 도착 좌표까지 도달한 경우
            if (nowX == endX && nowY == endY) {
                return nowTime; // 현재까지 걸린 시간 반환
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
                
                // 큐에 탐색한 좌표 정보 및 현재 시간 + 1 해서 저장
                queue.add(new Position(nextX, nextY, nowTime + 1));
                visited[nextX][nextY] = true; // 탐색한 좌표 방문처리
            }
        }
        
        // 위의 너비우선탐색 실시했는데도 도착지까지 도달하지 못한 경우 -1 반환
        return -1;
    }
}