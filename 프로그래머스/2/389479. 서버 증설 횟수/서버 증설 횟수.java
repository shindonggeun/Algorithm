class Solution {
    
    static int currentServerCount; // 현재 운영 중인 서버의 개수 (시간이 지나면서 감소)
    static int[] expires = new int[24]; // 각 시간별 서버 만료 수 [0] ~ [23]
    
    public int solution(int[] players, int m, int k) {
        int answer = 0; // 결과값 (서버 증설 횟수)
        
        // 24시간 동안 시뮬레이션 진행
        for (int time=0; time<players.length; time++) {
            currentServerCount -= expires[time]; // 시간별 만료된 서버 제거
            
            int needServerCount = players[time] / m; // 현재 필요한 서버 수 계산
            
            // 현재 운영 중인 서버보다 더 많은 서버가 필요한 경우 증설해야 함
            if (currentServerCount < needServerCount) {
                // 부족한 서버 개수 계산
                int addServerCount = needServerCount - currentServerCount;
                answer += addServerCount; // 총 증설 횟수 갱신 (새로운 서버를 추가한 횟수 더해줌)
                currentServerCount += addServerCount; // 현재 운영 중인 서버 개수 갱신
                
                // 추가한 서버는 k 시간동안 유지 됨 (k 시간 뒤에는 반납해야함)
                if (time + k < 24) {
                    expires[time + k] += addServerCount;
                }
            }
        }
        
        return answer;
    }
}