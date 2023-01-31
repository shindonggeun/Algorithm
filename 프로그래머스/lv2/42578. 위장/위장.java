import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>(); // key: 의상 타입, 의상 개수 + 1
        
        for(int i=0; i<clothes.length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 1) + 1);
        }
        
        // 모든 경우의 수 곱해주기(옷 입을수 있는 경우의 수)
        for(String key: map.keySet()) {
            answer *= map.get(key);
        }
        answer = answer - 1;    // 맨몸일때의 경우 빼주기(아무 의상도 안입을때의 경우의 수 빼주기)
        
        return answer;
    }
}