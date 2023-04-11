import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        
        Map<Character, Integer> map = new HashMap<>();  // key: 문자, value: 이 문자를 작성하기 위해 눌러야하는 키의 최소값
        
        // 해당 키값들 탐색
        for(String key: keymap) {
            int length = key.length();  // 해당 키의 길이 
            // 키의 문자열 전부 탐색(문자 하나씩)
            for(int i=0; i<length; i++) {
                char ch = key.charAt(i);    // 해당 키 추출
                
                map.put(ch, Math.min(i+1, map.getOrDefault(ch, i+1)));  // 해당 키와 그 키를 작성하기 위해 눌러야하는 최소값 저장
            }
        }
        //System.out.println(map);
        
        for(int i=0; i<targets.length; i++) {
            for(int j=0; j<targets[i].length(); j++) {
                char ch = targets[i].charAt(j);
                
                // 해당 키값이 있으면 더해줌
                if(map.containsKey(ch)) {
                    answer[i]+=map.get(ch);
                }
                // 해당 키값이 없는 경우 -1 (목표 문자열을 작성할 수 없을때임)
                else {
                    answer[i] = -1;
                    break;
                }
            }
        }
        
        
        return answer;
    }
}