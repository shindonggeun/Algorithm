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
    
    static int N;   // 세로 길이
    static int M;   // 가로 길이
    static boolean[][] visited; // 방문배열
    // 4가지 방향 배열 (하, 상, 좌, 우)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    // key: 석유량 덩어리 번호, value: 해당 석유량 크기
    static Map<Integer, Integer> map;   
    static int maxOil;  // 시추관 하나를 설치해 뽑을 수 있는 가장 많은 석유량
    
    public int solution(int[][] land) {
        int answer = 0;
        
        N = land.length;    // 세로 길이
        M = land[0].length; // 가로 길이 
        visited = new boolean[N][M];    // [0][0] ~ [N-1][M-1]
        map = new HashMap<>();  
        int landNum = 1;    // 석유량 덩어리 번호 1로 초기화
        maxOil = 0; // 가장 많은 석유량 0으로 초기화
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                // 해당 좌표가 아직 방문하지 않았으면서 동시에 석유가 있는 땅(1)인 경우
                if (!visited[i][j] && land[i][j] == 1) {
                    int landCount = bfs(i, j, landNum, land);   // 해당 좌표부터 너비우선탐색 실시
                    map.put(landNum, landCount);    // 해당 석유량 덩어리 번호와 석유량 크기 hashmap에 저장
                    landNum++;  // 석유량 덩어리 번호 증가
                }
            }
        }
        
        for (int i=0; i<M; i++) {
            int totalOil = oilCheck(i, land);   // 해당 열의 총 석유량 계산
            maxOil = Math.max(maxOil, totalOil);    // 가장 많은 총 석유량 갱신
        }
        
        answer = maxOil; // 최종 결과값 저장
        return answer;
    }
    
    // 석유 덩어리 크기 계산 및 해당 석유 덩어리에 번호를 저장할 너비우선탐색 메서드
    public static int bfs(int startX, int startY, int landNum, int[][] land) {
        // 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY));    // 시작좌표 큐에 저장
        visited[startX][startY] = true; // 시작좌표 방문처리
        land[startX][startY] = landNum; // 시작좌표로 시작하는 석유 덩어리에 해당 번호 기입
        int landCount = 1;  // 석유량 덩어리 크기 1부터 시작
        
        while (!queue.isEmpty()) {
            // 현재 좌표 정보 큐에서 뽑아내기
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            // 4가지 방향 탐색 (하, 상, 좌, 우)
            for (int i=0; i<4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                
                // 탐색한 좌표가 [0][0] ~ [N-1][M-1] 이외의 좌표인 경우
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;   // 다음 방향 탐색하도록 넘어감
                }
                
                // 탐색한 좌표가 이미 방문처리 됐거나 또는 빈 땅(0)인 경우
                if (visited[nextX][nextY] || land[nextX][nextY] == 0) {
                    continue;   // 다음 방향 탐색하도록 넘어감
                }
                
                // 탐색한 좌표 큐에 저장
                queue.add(new Position(nextX, nextY));
                visited[nextX][nextY] = true;   // 탐색한 좌표 방문처리
                land[nextX][nextY] = landNum;   // 탐색한 좌표에 해당하는 석유 땅에 해당 번호 기입
                landCount++;    // 석유 덩어리 크기 증가
            }
        }
        
        // 위의 너비우선탐색 과정 완료된 경우 해당 석유 덩어리 반환
        return landCount;
    }
    
    // 해당 열에서부터 시추관을 뚫어서 현재 열에서 뽑을 수 있는 총 석유량을 크기를 계산하는 메서드
    public static int oilCheck(int y, int[][] land) {
        int nowX = 0;   // 현재 행의 번호
        // 해당 석유량 덩어리 번호 체크하는 배열
        boolean[] landCheck = new boolean[map.size()+1];    // [1] ~ [석유량 덩어리 번호 끝]
        int tempOil = 0;    // 계산할 총 석유량
        
        while (true) {
            // 현재 행이 세로 길이보다 크거나 같은 경우 (즉, 행의 끝을 넘은 경우)
            if (nowX >= N) {
                break;  // 무한반복 빠져나옴
            }
            
            int landNum = land[nowX][y];    // 석유량 덩어리 번호 추출
            
            // 석유량 덩어리 번호가 0이 아니면서 동시에 석유량 덩어리 번호를 체크하지 않은 경우
            if (landNum != 0 && !landCheck[landNum]) {
                tempOil += map.get(landNum);    // 총 석유량 갱신 (해당 번호의 석유량 덩어리 누적)
                landCheck[landNum] = true;  // 석유량 덩어리 번호 체크 표시 (방문 처리)
            }
            
            nowX++; // 현재 행의 번호 증가
        }
        
        return tempOil; // 계산된 총 석유량 반환
    }
}