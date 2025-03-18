import java.util.*;

class Solution {
    
    static Map<String, Integer> scoreMap; // key: 인물 이름, value: 추억 점수
    
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        scoreMap = new HashMap<>();
        
        for (int i=0; i<name.length; i++) {
            scoreMap.put(name[i], yearning[i]);
        }
        
        for (int i=0; i<photo.length; i++) {
            String[] pho = photo[i];
            int totalScore = 0;
            
            for (String s: pho) {
                totalScore += scoreMap.getOrDefault(s, 0); 
            }
            
            answer[i] = totalScore;
        }
        return answer;
    }
}