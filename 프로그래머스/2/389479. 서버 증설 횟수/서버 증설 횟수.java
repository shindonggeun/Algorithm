class Solution {
    
    static int currentServerCount; // 현재 증설된 서버의 수
    static int[] expires = new int[24]; // 각 시간별 서버 만료 수 [0] ~ [23]
    
    public int solution(int[] players, int m, int k) {
        int answer = 0; // 결과값 (증설 횟수)
        
        for (int time=0; time<players.length; time++) {
            currentServerCount -= expires[time]; // 시간별 만료된 서버 제거
            
            // 현재 필요한 서버 수 계산
            int needServerCount = players[time] / m; // 올림 연산으로 필요 서버 수 계산
            
            // 현재 증설된 서버 수가 현재 필요한 서버 수보다 작은 경우
            if (currentServerCount < needServerCount) {
                int addServerCount = needServerCount - currentServerCount;
                answer += addServerCount;
                currentServerCount += addServerCount;
                
                // k시간 뒤에 서버 만료 예정
                if (time + k < 24) {
                    expires[time + k] += addServerCount;
                }
            }
        }
        
        return answer;
    }
}