import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        
        Map<Character, Integer> map = new HashMap<>();
        
        for(String key: keymap) {
            int length = key.length();
            for(int i=0; i<length; i++) {
                char ch = key.charAt(i);
                
                map.put(ch, Math.min(i+1, map.getOrDefault(ch, i+1)));
            }
        }
        //System.out.println(map);
        
        for(int i=0; i<targets.length; i++) {
            for(int j=0; j<targets[i].length(); j++) {
                char ch = targets[i].charAt(j);
                
                if(map.containsKey(ch)) {
                    answer[i]+=map.get(ch);
                }
                else {
                    answer[i] = -1;
                    break;
                }
            }
        }
        
        
        return answer;
    }
}