import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        HashMap<String, Integer> playerMap = new HashMap<>();   // key: 이름, value: 순위
        HashMap<Integer, String> rankMap = new HashMap<>();     // key: 순위, value: 이름
        for(int i=0; i<players.length; i++) {
            playerMap.put(players[i], i+1);
            rankMap.put(i+1, players[i]);
        }
        
        for(int i=0; i<callings.length; i++) {
            int rank = playerMap.get(callings[i]);  // 해당 이름을 가진 플레이어의 원래 순위 뽑아내기
            playerMap.put(callings[i], rank - 1);   // 순위 변동(앞지름)
            String name = rankMap.get(rank-1);  // 원래 순위에서 앞에 있던 사람 추출
            rankMap.put(rank-1, callings[i]);   // 순위 변동(앞지름)
            rankMap.put(rank, name);            // 순위 변동(뒤로 밀림) (원래 순위에서 앞에 있던 사람)
            playerMap.put(name, rank);          // 순위 변동(뒤로 밀림)
            //System.out.println(rankMap);
        }
        for(int i=1; i<=rankMap.size(); i++) {
            answer[i-1] = rankMap.get(i);
        }
        
        return answer;
    }
}