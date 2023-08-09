import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        
        Map<String, Integer> nameMap = new HashMap<>(); // key: 이름, value: 순위
        Map<Integer, String> rankMap = new HashMap<>(); // key: 순위, value: 이름
        for(int i=0; i<players.length; i++) {
            nameMap.put(players[i], i+1);
            rankMap.put(i+1, players[i]);
        }
        
        for(int i=0; i<callings.length; i++) {
            int rank = nameMap.get(callings[i]); // 해당 이름에 따른 등수 뽑기
            nameMap.put(callings[i], rank-1);    // 순위 변동해줌 (이름 불렸으니 순위 앞으로)
            String name = rankMap.get(rank-1);  // 원래 해당 순위였던 사람의 이름 뽑기
            rankMap.put(rank-1, callings[i]);   // 순위 변동해줌 (앞지름)
            rankMap.put(rank, name);    // 순위변동 (뒤로 밀림) (원래 순위에서 앞에 있던 사람)
            nameMap.put(name, rank);    // 순위변동 (뒤로 밀림)
        }
        
        // rankMap에 저장된 사람들 수만큼 탐색하기
        for(int i=1; i<=rankMap.size(); i++) {
            answer[i-1] = rankMap.get(i);   // 1위부터 차례대로 해당 사람의 이름을 배열에 저장
        }
        return answer;
    }
}