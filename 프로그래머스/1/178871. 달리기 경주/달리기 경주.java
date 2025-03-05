import java.util.*;

class Solution {
    
    static int n;
    static Map<String, Integer> nameMap; // key: 이름, value: 순위
    static Map<Integer, String> rankingMap; // key: 순위, value: 이름
    
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        
        n = players.length;
        nameMap = new HashMap<>();
        rankingMap = new HashMap<>();
        
        for (int i=0; i<n; i++) {
            String name = players[i];
            
            nameMap.put(name, i + 1);
            rankingMap.put(i + 1, name);
        }
        
        for (String call: callings) {
            int originalRanking = nameMap.get(call);
            String highName = rankingMap.get(originalRanking - 1);
            
            nameMap.put(call, originalRanking - 1);
            rankingMap.put(originalRanking - 1, call);
            
            nameMap.put(highName, originalRanking);
            rankingMap.put(originalRanking, highName);
        }
        
        answer = new String[n];
        
        for (int i=0; i<n; i++) {
            answer[i] = rankingMap.get(i + 1);
        }
        
        return answer;
    }
}