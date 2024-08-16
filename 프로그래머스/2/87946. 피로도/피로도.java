class Solution {
    
    static boolean[] visited; // 던전들 방문 여부를 체크하는 배열
    static int maxDungeonCount; // 탐헐할 수 있는 최대 던전 수
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        visited = new boolean[dungeons.length]; // 던전 개수만큼 방문 여부 체크할 배열의 방 만들어줌
        maxDungeonCount = Integer.MIN_VALUE; // 탐험할 수 있는 최대 던전 수 최소값으로 초기화
        dfs(0, dungeons, k); // 깊이우선탐색 실시
        
        answer = maxDungeonCount; // 결과값 갱신
        
        return answer;
    }
    
    // 깊이 우선 탐색 메서드 (백트래킹)
    // count: 현재까지 탐험한 던전 수
    // dungeons: 던전의 정보를 담고 있는 2차원 배열
    // current: 현재까지 던전을 탐험하고 남은 피로도
    public void dfs(int count, int[][] dungeons, int current) {
        // 탐험할 수 있는 최대 던전 수 갱신
        maxDungeonCount = Math.max(maxDungeonCount, count);
        
        // 모든 던전을 순회
        for (int i=0; i<dungeons.length; i++) {
            // 해당 던전이 방문하지 않았으면서 동시에 현재 피로도가 해당 던전의 최소 필요 피로도보다 크거나 같은 경우
            // 즉, 던전 탐험 가능한 경우
            if (!visited[i] && dungeons[i][0] <= current) {
                visited[i] = true; // 해당 던전 방문 처리
                // 탐험한 던전 수 + 1하고, 현재 남은 피로도 갱신처리 한 상태로 다음 던전 탐험 실시 (재귀 호출)
                dfs(count + 1, dungeons, current - dungeons[i][1]);
                visited[i] = false; // 해당 던전 방문 처리 해제 (백트래킹)
            }
        }
    }
}