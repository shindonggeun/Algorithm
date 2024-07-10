import java.util.*;

class Solution {
    
    // 좌표 정보를 저장하는 내부 클래스 (블록)
    static class Block {
        int x;
        int y;
        
        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static char[][] map; // 블록들을 저장하는 게임지도 배열
    static boolean[][] visited; // 제거해야할 블록들의 방문 여부를 나타내는 배열
   
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        map = new char[m][n]; // [0][0] ~ [m-1][n-1]
        
        // 2차원 게임지도 배열에 블록들 저장하는 과정
        for (int i=0; i<m; i++) {
            String input = board[i];
            for (int j=0; j<n; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        
        // 제거할 블록이 없을때까지 무한반복
        while (true) {
            boolean bomb = false; // 제거할 2x2 블록이 있는지 여부를 나타내는 변수
            visited = new boolean[m][n]; // [0][0] ~ [m-1][n-1]
            
            // [0][0] ~ [m-2][n-2]까지 순회
            // 2x2 블록인지 확인하는 과정때문에 범위 넘어가지 않도록
            for (int i=0; i<m-1; i++) {
                for (int j=0; j<n-1; j++) {
                    // 해당 좌표에서부터 제거할 2x2 블록이 있는지 체크
                    if (blockRemoveCheck(i, j)) {
                        bomb = true; // 제거할 블록 있음
                    }
                }
            }
            
            // 제거할 블록이 없는 경우
            if (!bomb) {
                break; // 무한반복 빠져나옴 (게임 종료)
            }
            
            
            int removeCount = blockRemove(m, n); // 2x2 블록들 제거한 뒤 제거한 개수 저장
            answer += removeCount; // 총 제거한 블록 수 누적
            blockDown(m, n); // 블록을 아래로 떨어뜨리기
        }
        
        return answer;
    }
    
    // 해당 좌표에서부터 제거할 2x2 블록이 있는지 체크하는 메서드
    public boolean blockRemoveCheck(int nowX, int nowY) {
        char block = map[nowX][nowY]; // 해당 좌표의 블록의 문자
        
        // 해당 블록이 빈 블록('.')이 아니면서 동시에 해당 좌표에서부터 2x2 블록으로 이루어진 경우
        // 해당 좌표에서부터 우, 우+하, 하 좌표 체크
        if (block != '.' && map[nowX][nowY+1] == block && map[nowX+1][nowY+1] == block && map[nowX+1][nowY] == block) {
            visited[nowX][nowY] = true; // 해당 좌표 제거 가능
            visited[nowX][nowY+1] = true; // 해당 좌표에서 오른쪽으로 이동한 좌표 제거 가능 (우)
            visited[nowX+1][nowY+1] = true; // 해당 좌표에서 오른쪽 + 아래로 이동한 좌표 제거 가능 (우 + 하)
            visited[nowX+1][nowY] = true; // 해당 좌표에서 아래로 이동한 좌표 제거 가능 (하)
            return true; // 제거할 2x2 블록 있다는 여부 반환
        }
        
        // 제거할 2x2 블록이 없다는 여부 반환
        return false;
    }
    
    // 블록들을 제거하는 메서드
    public int blockRemove(int m, int n) {
        int count = 0; // 제거한 개수
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                // 해당 좌표가 제거가능한 블록인 경우
                if (visited[i][j]) {
                    map[i][j] = '.'; // 해당 좌표의 블록 제거 표시
                    count++; // 제거한 개수 증가
                }
            }
        }
        
        // 제거한 개수 반환
        return count;
    }
    
    // 블록을 아래로 떨어뜨리는 메서드
    public void blockDown(int m, int n) {
        // 각 열에 대해서 탐색
        for (int j=0; j<n; j++) {
            Stack<Character> stack = new Stack<>(); // 각 열에 대한 스택 생성
            
            // 각 행에 대해서 위에서부터 탐색 
            for (int i=0; i<m; i++) {
                // 해당 좌표가 제거되지 않은 블록('.')인 경우 (즉, 아래로 떨어뜨릴 블록인 경우)
                if (map[i][j] != '.') {
                    stack.push(map[i][j]); // 해당 블록을 스택에 저장
                }
            }
            
            // 각 행에 대해서 아래에서부터 탐색
            for (int i=m-1; i>=0; i--) {
                // 스택이 비어있지 않은 경우
                if (!stack.isEmpty()) {
                    map[i][j] = stack.pop(); // 스택에서 블록을 꺼내 아래에서부터 채움
                }
                // 스택이 비어있는 경우
                else {
                    map[i][j] = '.'; // 해당 좌표는 빈 공간으로 채움
                }
            }
        }
    }
}