class Solution {
    
    // 사전순으로 만들어야 하므로 사전순으로 빠른 방향
    static char[] dir = {'d', 'l', 'r', 'u'}; // 'd' = 하, 'l' = 좌, 'r' = 우, 'u' = 상
    // 4가지 방향 배열 (하, 좌, 우, 상)
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    
    static int endX; // 목표 지점의 x 좌표
    static int endY; // 목표 지점의 y 좌표
    
    static StringBuilder path; // 현재까지의 경로를 저장하는 StringBuilder
    static boolean found; // 경로를 찾았는지 여부를 나타내는 변수
    static String answer; // 최종 결과를 반활할 정답 (경로 문자열)
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = "impossible"; // 정답 기본값 미로 탈출할 수 없는 경우("impossible")로 초기화
        found = false; // 경로 찾았는지 여부 false로 초기화
        
        int distance = calculateDistance(x, y, r, c); // 시작점과 목표 지점 사이의 맨해튼 거리 계산
        
        // 시작점과 목표 지점 사이의 거리(최소 거리)가 k보다 크거나 또는 k에서 최소 거리를 뺀 값이 홀수인 경우
        // (k - distance)는 목표 지점까지 이동하고 남은 여분의 이동거리인데
        // 이때 남은 이동 횟수가 짝수여야만 목표 지점에서 다시 자기 위치로 되돌아오면서 움직임을 상쇄시킬 수 있음
        if (distance > k || (k - distance) % 2 != 0) {
            return "impossible"; // 탈출이 불가능하므로 "impossible" 반환
        }
        
        path = new StringBuilder(); // 경로를 저장할 StringBuilder 초기화
        dfs(n, m, x, y, r, c, 0, k); // dfs 탐색 시작 (백트래킹)
        
        return answer;
    }
    
    // 두 좌표 사이의 맨해튼 거리를 계산해주는 메서드
    public int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.abs(x1 - x2) + (int) Math.abs(y1 - y2);
    }
    
    // 백트래킹 알고리즘을 이용하여 미로를 탈출하기 위한 경로 찾는 메서드
    // n: 맵의 (격자) 크기 (행)
    // m: 맵의 (격자) 크기 (열)
    // nowX: 현재 x 좌표 (행)
    // nowY: 현재 y 좌표 (열)
    // endX: 목표 x 좌표
    // endY: 목표 y 좌표
    // depth: 현재 깊이 (현재까지 이동한 거리)
    // k: 미로 탈출하기 위한 총 이동 거리
    public void dfs(int n, int m, int nowX, int nowY, int endX, int endY, int depth, int k) {
        // 현재 위치에서 목표 지점까지의 남은 거리와 현재까지 이동한 거리를 더한 값이 k보다 큰 경우
        // 즉, k 이동거리로 미로 탈출 불가능한 경우 (기저 조건 1)
        if (depth + calculateDistance(nowX, nowY, endX, endY) > k) {
            return; // 메서드 종료
        }
        
        // 현재까지 이동한 거리가 k이면서 동시에 목표 지점에 도달한 경우 (기저 조건 2)
        if (depth == k && nowX == endX && nowY == endY) {
            answer = path.toString(); // 현재까지의 경로를 정답으로 저장
            found = true; // 경로를 찾았다는 표시
            return; // 메서드 종료
        }
        
        // 경로를 이미 찾은 경우 (기저 조건 3)
        if (found) {
            return; // 메서드 종료
        }
        
        // 사전순으로 이동 방향 탐색 (4가지 방향 탐색)
        for (int i=0; i<4; i++) {
            int nextX = nowX + dx[i];
            int nextY = nowY + dy[i];
            
            // 탐색한 좌표가 미로 범위 내에 있는 경우
            if (nextX > 0 && nextY > 0 && nextX <= n && nextY <= m) {
                path.append(dir[i]); // 해당 방향을 경로에 추가
                dfs(n, m, nextX, nextY, endX, endY, depth + 1, k); // 재귀적으로 dfs 탐색 진행
                path.deleteCharAt(path.length() - 1); // 마지막에 추가된 방향 제거 (경로 복원, 즉 백트래킹)
            }
            
        }
    }
    
}