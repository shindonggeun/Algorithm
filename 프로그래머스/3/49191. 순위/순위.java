import java.util.*;

class Solution {
    
    // 각 선수들마다 해당 선수에게 이긴 선수들의 목록을 저장할 인접 리스트 (즉, 자기 자신은 지고, 상대방은 이김)
    // 즉, 각 선수가 누구에게 졌는지 기록하는 인접 리스트 (나를 이긴 선수들 리스트)
    static ArrayList<ArrayList<Integer>> winerList;
    // 각 선수들마다 해당 선수에게 진 선수들의 목록을 저장할 인접 리스트 (즉, 자기 자신은 이기고, 상대방은 짐)
    // 즉, 각 선수가 누구에게 이겼는지 기록하는 인접 리스트 (나에게 진 선수들 리스트)
    static ArrayList<ArrayList<Integer>> loserList;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        winerList = new ArrayList<>(); // 인접 리스트 초기화
        loserList = new ArrayList<>(); // 인접 리스트 초기화 
        
        // 1번부터 n번까지의 선수들 리스트 초기화 과정
        for (int i=0; i<=n; i++) {
            winerList.add(new ArrayList<>()); // i번 선수가 누구에게 졌는지 저장할 선수 리스트
            loserList.add(new ArrayList<>()); // i번 선수가 누구에게 이겼는지 저장할 선수 리스트
        }
        
        for (int[] result: results) {
            int winer = result[0]; // 이긴 선수 번호
            int loser = result[1]; // 진 선수 번호
            
            winerList.get(loser).add(winer); // 해당 진 선수의 winerList(승자 리스트)에 이긴 선수 번호 추가
            loserList.get(winer).add(loser); // 해당 이긴 선수의 loserList(패자 리스트)에 진 선수 번호 추가
        }
        
        int count = 0; // 정확한 순위를 매길 수 있는 선수의 수를 계산하기 위한 변수
        // 1번 선수부터 n번 선수까지 탐색
        for (int i=1; i<=n; i++) {
            // 해당 선수의 순위를 명확히 확인할 수 있는 경우
            if (bfs(i, n)) {
                count++; // 정확한 순위를 매길 수 있는 선수의 수 증가
            }
        }
        
        answer = count; // 결과값에 저장
        
        return answer; // 결과값 반환
    }
    
    // 너비우선탐색 알고리즘을 이용하여 해당 선수의 순위를 정확히 알 수 있는지 확인하는 메서드
    public boolean bfs(int player, int n) {
        // 너비우선탐색 알고리즘을 사용하기 위해 큐 선언 및 초기화
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1]; // 방문배열 선언 및 초기화, [1] ~ [n]
        
        // 1. 해당 선수를 이긴 선수들을 찾는 과정
        queue.add(player); // 큐에 해당 선수 번호 저장
        visited[player] = true; // 해당 선수 방문 처리
        
        while (!queue.isEmpty()) {
            int now = queue.poll(); // 큐에 저장된 현재 선수 번호 뽑기
            
            // 현재 선수에게 이긴 선수들 리스트 탐색 
            for (int next: winerList.get(now)) {
                // 해당 선수가 방문처리 안된 경우
                if (!visited[next]) {
                    visited[next] = true; // 해당 선수 방문 처리
                    queue.add(next); // 해당 선수 큐에 저장
                }
            }
        }
        
        // 2. 해당 선수에게 진 선수들 찾는 과정 
        queue.add(player); // 큐에 해당 선수 번호 저장
        
        while (!queue.isEmpty()) {
            int now = queue.poll(); // 큐에 저장된 현재 선수 번호 뽑기
            
            // 현재 선수에게 진 선수들 리스트 탐색 
            for (int next: loserList.get(now)) {
                // 해당 선수가 방문처리 안된 경우
                if (!visited[next]) {
                    visited[next] = true; // 해당 선수 방문 처리
                    queue.add(next); // 해당 선수 큐에 저장
                }
            }
        }
        
        // 3. 1번 선수부터 n번 선수까지 모든 선수 탐색하는 과정
        for (int i=1; i<=n; i++) {
            // 해당 선수가 방문처리 되지 않은 경우
            if (!visited[i]) {
                return false; // 해당 선수는 정확한 순위를 매길 수 없다는 표시인 false 반환
            }
        }
        
        // 모든 선수 탐색했는지 다 방문처리가 됐으므로 해당 선수는 정확한 순위를 매길 수 있다는 표시인 true 반환
        return true;
    }
}