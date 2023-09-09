import java.util.*;

class Solution {
    
    // 좌표 정보를 저장해주는 내부 클래스
    static class Position {
        int x;  
        int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    // 4가지 방향 배열 (상, 하, 좌, 우) -> 좌표평면상에서 표현한 값이므로 배열에서는 하, 상, 좌, 우 라고 표현할 수 있다
    static int[] dx = {1, -1, 0, 0};   // x축이 고정되어 있을 때 y좌표가 움직이는 방향 배열
    static int[] dy = {0, 0, -1, 1};   // y축이 고정되어 있을 때 x좌표가 움직이는 방향 배열
    static boolean[][] visited;     // 맵 좌표 방문여부를 나타내는 2차원 배열
    static int areaCount;  // 그림에서 몇 개의 영역이 있는 지를 나타내는 변수
    static int maxArea;    //  그림에서 가장 큰 영역을 나타내는 변수
    
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];  // [0] => 그림에 몇개의 영역이 있는지, [1] => 가장 큰 영역의 칸 수   
        visited = new boolean[m][n];    // (0, 0) ~ (n-1, m-1)
        areaCount = 0;
        maxArea = Integer.MIN_VALUE;
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                // 방문하지 않은 좌표면서 동시에 색칠이 되어 있는 좌표(영역)인 경우
                if(!visited[i][j] && picture[i][j] != 0) {
                    // 너비우선탐색 실시 -> (x시작좌표, y시작좌표, 행의 개수, 열의 개수, 해당 좌표의 색칠값)
                    bfs(i, j, m, n, picture, picture[i][j]);
                    areaCount++;    // 그림 영역 개수 증가
                    
                }
            }
        }
        
        answer[0] = areaCount;  // 그림 영역 저장
        answer[1] = maxArea;    // 가장 큰 영역
        return answer;
    }
    
    // 너비우선탐색 메서드
    public static void bfs(int startX, int startY, int m, int n, int[][] picture, int value) {
        Queue<Position> queue = new LinkedList<>(); // 큐 이용
        visited[startX][startY] = true; // 시작좌표 방문처리
        queue.add(new Position(startX, startY));    // 큐에 해당 좌표 집어넣어줌
        int tempArea = 1;   // 탐색하려는 색칠 영역 크기 1부터 시작
        
        while(!queue.isEmpty()) {
            Position now = queue.poll();
            int nowX = now.x;   // 시작좌표 X
            int nowY = now.y;   // 시작좌표 y
            
            // 4가지 방향 탐색(상, 하, 좌, 우) 
            for(int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                // 탐색한 좌표가 (0, 0) ~ (n-1, m-1) 이외의 경우
                if(nextX < 0 || nextY < 0 || nextX >= m || nextY >= n) {
                    continue;   // 건너뜀
                }
                
                // 탐색한 좌표가 이미 방문한 좌표거나 같은 영역의 값이 아닌 경우
                if(visited[nextX][nextY] || picture[nextX][nextY] != value) {
                    continue;   // 건너뜀
                }
                
                queue.add(new Position(nextX, nextY));  // 큐에 탐색한 좌표 저장
                visited[nextX][nextY] = true;   // 탐색한 좌표 방문처리
                tempArea++; // 색칠영역 크기 증가
            }
        }
        // 위의 너비우선탐색 실시해서 다 끝났으면 색칠한 가장 큰 영역 갱신하기
        maxArea = Math.max(maxArea, tempArea);  
    }
    
}