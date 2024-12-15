import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        int N = friends.length; // 친구의 수
        // 친구들의 이름에 따라 해당 이름을 인덱스로 매핑할 해시맵
        Map<String, Integer> friendIdxMap = new HashMap<>(); // key: 이름, value: 인덱스
        int[] giftDegree = new int[N]; // 각 친구의 선물 지수를 나타낼 배열 (준 선물 - 받은 선물)
        int[][] giftGraph = new int[N][N];  // 친구 간 주고 받은 선물 횟수를 기록하는 그래프 [준 사람][받은 사람]
        
        // 친구 이름을 인덱스로 매핑하는 과정
        for (int i=0; i<N; i++) {
            friendIdxMap.put(friends[i], i);
        }
        
        // 선물 주고받은 기록들을 처리하는 과정
        for (String gift: gifts) {
            String[] giftName = gift.split(" "); // [0] = 준 사람, [1] = 받은 사람
            int giverIdx = friendIdxMap.get(giftName[0]); // 선물을 준 사람의 인덱스
            int receiverIdx = friendIdxMap.get(giftName[1]); // 선물을 받은 사람의 인덱스
            
            giftDegree[giverIdx]++; // 준 사람의 선물 지수 증가
            giftDegree[receiverIdx]--; // 받은 사람의 선물 지수 감소
            giftGraph[giverIdx][receiverIdx]++; // 준 사람 -> 받은 사람 관계 기록
        }
        
        int maxGiftCount = 0; // 다음 달에 가장 많이 받은 선물 수를 저장할 변수
        
        // 모든 친구를 기준으로 다음 달에 받은 선물을 계산하는 과정
        for (int i=0; i<N; i++) {
            int giftCount = 0; // 현재 친구가 받은 선물 수
            for (int j=0; j<N; j++) {
                // 해당 친구의 인덱스가 같은 경우
                if (i == j) {
                    continue; // 해당 경우는 자기 자신에게 선물을 주고 받는 경우인데 이 경우는 없으므로 다음 사람 탐색
                }
                
                // 1. (giftGraph[i][j] > giftGraph[j][i]) => 두 사람이 주고 받은 선물 횟수를 비교하는 과정
                // i가 j에게 준 선물 횟수가 j가 i에게 준 횟수보다 많은 경우, 규칙에 따라서 i는 j에게 선물 받기 가능
                // 2. (giftGraph[i][j] == giftGraph[j][i] && giftDegree[i] > giftDegree[j])
                // => 선물을 주고 받은 관계가 없거나 횟수가 같은 경우, 선물 지수를 비교하는 과정
                // i와 j가 주고 받은 선물 횟수가 같거나 선물을 주고 받은 적이 없는 경우이면서 동시에 
                // i의 선물 지수가 j보다 큰 경우, i가 j에게 선물을 받을 수 있음
                // 즉, 1 또는 2의 경우 인 경우 선물 받기 가능
                if (giftGraph[i][j] > giftGraph[j][i] || 
                    (giftGraph[i][j] == giftGraph[j][i] && giftDegree[i] > giftDegree[j])) {
                    giftCount++;
                }
            }
            
            maxGiftCount = Math.max(maxGiftCount, giftCount); // 최대 선물 수 갱신
        }
        
        answer = maxGiftCount; // 결과값에 최대 선물 수 저장
        
        return answer;
    }
}