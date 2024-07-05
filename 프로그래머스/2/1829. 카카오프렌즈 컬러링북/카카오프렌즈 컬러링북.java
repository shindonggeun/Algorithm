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
    
    static boolean[][] visited; // 2차원 방문 배열
    // 4가지 방향 배열 (하, 상, 좌, 우)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int numberOfArea; // 그림에서 몇개의 영역이 있는지 나타내는 변수
    static int maxSizeOfOneArea; // 가장 큰 영역의 칸 수
    
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2]; // [0] = 그림에서 영역의 개수, [1] = 가장 큰 영역의 칸 수
        
        visited = new boolean[m][n]; // [0][0] ~ [m-1][n-1]
        numberOfArea = 0; // 그림에서 영역의 개수 0으로 초기화
        maxSizeOfOneArea = 0; // 그림에서 가장 큰 영역의 칸 수 0으로 초기화
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                // 해당 좌표가 방문하지 않았으면서 동시에 색칠하지 않은 영역(0)이 아닌 경우 (즉, 색칠한 영역인 경우)
                if (!visited[i][j] && picture[i][j] != 0) {
                    bfs(m, n, picture, i, j); // 해당 좌표에서부터 너비우선탐색 실시
                    numberOfArea++; // 그림에서 영역의 개수 증가
                }
            }
        }
        
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    // 가장 큰 영역을 구해주는 너비우선탐색 메서드
    public void bfs(int m, int n, int[][] picture, int startX, int startY) {
        // 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY)); // 큐에 시작 좌표 정보 저장
        visited[startX][startY] = true; // 해당 시작 좌표 방문 처리
        
        int color = picture[startX][startY]; // 현재 시작좌표의 그림 색상
        int count = 1; // 해당 색상의 칸 수
        
        while (!queue.isEmpty()) {
            // 큐에서 현재 좌표 정보 뽑아냄
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            // 4가지 방향 탐색
            for (int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                // 탐색한 좌표가 [0][0] ~ [m-1][n-1] 이외의 좌표인 경우 
                if (nextX < 0 || nextY < 0 || nextX >= m || nextY >= n) {
                    continue; // 다음 방향 탐색하도록 넘어감
                }
                
                // 탐색한 좌표가 이미 방문처리 됐거나 또는 색칠되지 않은 영역(0)인 경우
                if (visited[nextX][nextY] || picture[nextX][nextY] == 0) {
                    continue; // 다음 방향 탐색하도록 넘어감
                }
                
                // 탐색한 좌표의 색상이 현재 탐색한 그림영역의 시작좌표의 색상과 같은 경우 
                if (picture[nextX][nextY] == color) {
                    queue.add(new Position(nextX, nextY)); // 큐에 현재 탐색한 좌표 정보 저장
                    visited[nextX][nextY] = true; // 탐색한 좌표 방문 처리
                    count++; // 해당 그림 영역의 수 증가
                }
            }
        }
        
        // 위의 너비우선탐색 과정 다 끝났으면 가장 큰 그림 영역의 칸 수 갱신
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, count);
    }
}