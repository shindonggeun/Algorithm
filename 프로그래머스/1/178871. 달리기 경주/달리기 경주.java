import java.util.*;

class Solution {
    
    static int n; // 선수 명수를 저장하는 변수
    static Map<String, Integer> nameMap; // key: 이름, value: 순위
    static Map<Integer, String> rankingMap; // key: 순위, value: 이름
    
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        
        n = players.length;
        nameMap = new HashMap<>();
        rankingMap = new HashMap<>();
        
        // 1. 해시맵에 저장하는 과정
        for (int i=0; i<n; i++) {
            String name = players[i];
            
            nameMap.put(name, i + 1);
            rankingMap.put(i + 1, name);
        }
        
        // 2. 해설진들이 추월한 선수들을 부르는 과정
        for (String call: callings) {
            int originalRanking = nameMap.get(call); // 원래 부른 선수의 순위
            String highName = rankingMap.get(originalRanking - 1); // 부른 선수보다 한단계 높은 순위를 가진 선수의 이름
            
            // 해설진이 부른 선수 순위 변동 (순위 앞지름)
            nameMap.put(call, originalRanking - 1);
            rankingMap.put(originalRanking - 1, call); 
            
            // 부른 선수보다 한단계 높은 순위를 가진 선수는 원래 부른 선수의 순위로 됨 (순위 뒤쳐짐)
            nameMap.put(highName, originalRanking);
            rankingMap.put(originalRanking, highName);
        }
        
        answer = new String[n]; // 결과 배열
        
        // 3. 최종 순위에 따른 선수 이름들 결과 배열에 저장하는 과정
        for (int i=0; i<n; i++) {
            answer[i] = rankingMap.get(i + 1);
        }
        
        return answer;
    }
}