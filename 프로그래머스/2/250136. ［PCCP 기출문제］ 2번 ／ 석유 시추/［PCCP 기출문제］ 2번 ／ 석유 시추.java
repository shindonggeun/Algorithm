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
    
    static int N; // 행의 개수
    static int M; // 열의 개수
    static boolean[][] visited; // 각 좌표들의 방문 여부를 담고 있는 배열
    // 해당 땅의 번호 및 그에 따른 석유량을 저장할 해시맵
    static Map<Integer, Integer> oilMap; // key: 땅의 번호, value: 땅의 석유량
    // 4가지 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] land) {
        int answer = 0;
        
        N = land.length; // 행의 개수 저장
        M = land[0].length; // 열의 개수 저장
        
        visited = new boolean[N][M]; // [0][0] ~ [N-1][M-1]
        oilMap = new HashMap<>(); // 해시맵 생성
        
        int landNum = 1; // 땅의 번호 1번부터 시작
        
        // 1. 땅들 번호 기입하는 과정
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                // 해당 좌표가 방문처리 되지 않았으면서 동시에 빈 땅(0)이 아닌 경우 
                if (!visited[i][j] && land[i][j] != 0) {
                    // 해당 땅 번호 기입 및 해당 땅의 석유량 확인할 너비우선탐색 실시
                    int oilCount = bfs(i, j, land, landNum);
                    oilMap.put(landNum, oilCount); // 해당 땅 번호에 따른 석유량 해시맵에 저장
                    landNum++; // 땅의 번호 증가
                }
            }
        }
        
        int maxOilSum = 0; // 최대 석유량 0으로 초기화
        
        // 2. 시추관을 설치해서 뽑을 수 있는 최대 석유량 계산하는 과정 (0번 열부터 탐색)
        for (int j=0; j<M; j++) {
            int oilSum = calculateOil(j, land); // 해당 열에 시추관 설치해서 석유량 계산하는 메서드 호출
            maxOilSum = Math.max(maxOilSum, oilSum); // 최대 석유량 갱신
        }
        
        answer = maxOilSum; // 결과값에 최대 석유량 저장
        
        return answer;
    }
    
    // 해당 좌표에서부터 시작하여 땅들의 번호를 기입하는 메서드 (너비우선탐색)
    public int bfs(int startX, int startY, int[][] land, int landNum) {
        // 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY)); // 해당 시작좌표 큐에 저장
        visited[startX][startY] = true; // 시작좌표 방문처리
        land[startX][startY] = landNum; // 시작좌표에 해당 땅의 번호 저장
        int oilCount = 1; // 해당 땅의 석유량 1로 초기화
        
        while (!queue.isEmpty()) {
            // 현재 큐에 저장된 좌표 정보 뽑아냄
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            // 4가지 방향 탐색
            for (int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                // 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue; // 다음 방향 탐색하도록 넘어감
                }
                
                // 탐색한 좌표가 방문처리 됐거나 또는 탐색한 좌표가 빈 땅(0)인 경우
                if (visited[nextX][nextY] || land[nextX][nextY] == 0) {
                    continue; // 다음 방향 탐색하도록 넘어감
                }
                
                visited[nextX][nextY] = true; // 탐색한 좌표 방문 처리
                land[nextX][nextY] = landNum; // 탐색한 좌표에 해당 땅의 번호 기입
                queue.add(new Position(nextX, nextY)); // 탐색한 좌표 정보 큐에 저장
                oilCount++; // 석유량 증가
            }
        }
        
        // 석유량 반환
        return oilCount;
    }
    
    // 해당 열에 시추관 설치해서 석유량 계산하는 메서드
    public int calculateOil(int startY, int[][] land) {
        int oilSum = 0; // 총 석유량
        boolean[] oilCheck = new boolean[oilMap.size()+1]; // 해당 땅들을 시추했는지 나타내는 방문 배열 
        
        // 0번 행부터 탐색 시작
        for (int i=0; i<N; i++) {
            int landNum = land[i][startY]; // 해당 땅의 번호 추출
            
            // 땅 번호가 0이 아니면서 (즉, 빈 땅이 아니면서) 동시에 해당 땅을 시추하지 않은 경우 (석유 뽑지 않은 경우)
            if (landNum != 0 && !oilCheck[landNum]) {
                oilSum += oilMap.get(landNum); // 해당 땅의 석유 추출해서 총 석유량에 누적
                oilCheck[landNum] = true; // 해당 땅 시추했다고 표시
            }
        }
        
        // 총 석유량 반환
        return oilSum;
    }
}