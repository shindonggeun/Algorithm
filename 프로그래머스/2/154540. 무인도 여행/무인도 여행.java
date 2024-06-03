import java.util.*;

class Solution {
    
    // 섬의 좌표 정보를 담고 있는 내부 클래스
    static class Island {
        int x;
        int y;
        
        public Island(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int n; // 지도의 세로 길이
    static int m; // 지도의 가로 길이
    static char[][] map; // 지도
    static boolean[][] visited; // 방문 배열
    static List<Integer> dayList; // 각 섬에 머무를 수 있는 일자를 저장할 리스트
    // 4가지 방향 배열 (하, 상, 좌, 우)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        
        n = maps.length; // 가로 길이
        m = maps[0].length(); // 세로 길이
        
        map = new char[n][m]; // [0][0] ~ [n-1][m-1]
        visited = new boolean[n][m];
        dayList = new ArrayList<>();
        
        // 2차원 배열 지도 초기화 하는 과정
        for (int i=0; i<n; i++) {
            String input = maps[i];
            for (int j=0; j<m; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                // 해당 좌표에 방문하지 않았으면서 동시에 머물 수 없는 경우('X')
                if (!visited[i][j] && map[i][j] != 'X') {
                    // 해당 좌표부터 너비우선탐색 실시해서 섬에 머무를 수 있는 최대 일자 계산
                    int day = bfs(i, j);
                    dayList.add(day); // 머무를 수 있는 일자 리스트에 저장
                }
            }
        }
        
        // 머무를 수 있는 리스트의 사이즈가 0인 경우 (즉, 지낼 수 없는 무인도가 없는 경우)
        if (dayList.size() == 0) {
            answer = new int[1]; 
            answer[0] = -1;
        }
        else {
            Collections.sort(dayList); // 리스트 오름차순 정렬
            answer = new int[dayList.size()];
            
            // List -> 배열에 옮기는 과정
            for (int i=0; i<dayList.size(); i++) {
                answer[i] = dayList.get(i);
            }    
        }
        
        return answer;
    }
    
    // 각 섬에 최대 며칠 씩 머무를 수 있는지 계산해주는 너비우선탐색 메서드
    public int bfs(int startX, int startY) {
        // 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
        Queue<Island> queue = new LinkedList<>();
        queue.add(new Island(startX, startY)); // 큐에 시작 좌표 저장
        visited[startX][startY] = true; // 해당 시작 좌표 방문 처리
        int totalDay = map[startX][startY] - '0'; // 섬에 머물 수 있는 일자 초기화
        
        while (!queue.isEmpty()) {
            // 큐에서 현재 섬의 좌표 정보 뽑아냄
            Island now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            // 4가지 방향 탐색
            for (int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                // 탐색한 좌표가 [0][0] ~ [n-1][m-1] 이외의 좌표인 경우
                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) {
                    continue; // 다음 방향 탐색하도록 넘어감
                }
                
                // 탐색한 좌표가 이미 방문했거나 또는 머물 수 없는('X') 경우
                if (visited[nextX][nextY] || map[nextX][nextY] == 'X') {
                    continue; // 다음 방향 탐색하도록 넘어감
                }
                
                // 큐에 탐색한 좌표 정보 저장
                queue.add(new Island(nextX, nextY));
                visited[nextX][nextY] = true; // 탐색한 좌표 방문처리
                totalDay += map[nextX][nextY] - '0'; // 섬에 머물수 있는 일자 더해줌
            }
        }
        
        return totalDay; // 섬에 머물 수 있는 일자 반환
    }
}