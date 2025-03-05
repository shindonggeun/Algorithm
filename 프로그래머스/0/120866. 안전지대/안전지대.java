import java.util.*;

class Solution {
    
    // 좌표 정보를 저장하기 위해 사용할 내부 클래스
    static class Position {
        int x;
        int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int n; // 지도의 크기
    // 8가지 방향 배열
    // 상, 상 + 우, 우, 하 + 우, 하, 하 + 좌, 좌, 상 + 좌
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static boolean[][] visited; // 해당 좌표의 방문 여부를 저장하는 배열 
    static List<Position> positionList; // 좌표 정보들을 저장할 리스트
    
    public int solution(int[][] board) {
        int answer = 0;
        
        n = board.length; // 지도의 크기 저장
        visited = new boolean[n][n]; // [0][0] ~ [n-1][n-1]
        positionList = new ArrayList<>(); // 좌표 정보들을 저장할 리스트 초기화
        
        // 1. 지도에서 지뢰 찾아서 좌표 정보 리스트에 저장하는 과정
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                // 해당 좌표가 지뢰(1)인 경우
                if (board[i][j] == 1) {
                    positionList.add(new Position(i, j));
                    visited[i][j] = true; // 해당 좌표 방문 처리
                }
            }
        }
        
        // 2. 좌표 정보들을 저장한 리스트에서 지뢰와 인접한 좌표들을 위험 지역으로 표시하는 과정
        for (Position pos: positionList) {
            mineCheck(pos.x, pos.y); // 위험 지역 체크
        }
        
        answer = safeAreaCalculate(); // 안전 지역 개수 구하기
        
        return answer;
    }
    
    // 지뢰와 인접한 좌표들을 위험 지역으로 표시하는 메서드
    public void mineCheck(int x, int y) {
        // 8가지 방향 체크
        for (int i=0; i<8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            
            // 탐색한 좌표가 [0][0] ~ [n-1][n-1] 이외의 좌표인 경우
            if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) {
                continue; // 다음 방향 탐색하게끔 넘어감
            }
            
            // 탐색한 좌표가 이미 방문한 경우
            if (visited[nextX][nextY]) {
                continue; // 다음 방향 탐색하게끔 넘어감
            }
            
            visited[nextX][nextY] = true; // 탐색한 좌표 방문 처리
        }
    }
    
    // 안전 지역의 개수 구하는 메서드
    public int safeAreaCalculate() {
        int count = 0;
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                // 해당 좌표가 방문처리 되지 않은 경우 (즉, 안전한 구역인 경우)
                if (!visited[i][j]) {
                    count++;
                }
            }
        }
        
        return count;
    }
}