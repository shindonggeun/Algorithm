import java.util.*;

class Solution {
    public List<String> solution(String[] strings, int n) {
        //String[] answer = new String[strings.length];
        Map<String, Character> map = new LinkedHashMap<>(); // key: 문자열, value: n번째 문자
        List<String> list = new ArrayList<>();  // 정렬할때 반환할 list
        
        // 문자열과 n번째 문자 map에 저장
        for(String s: strings) {
            map.put(s, s.charAt(n));
        }
        
        List<String> keyList = new ArrayList<>(map.keySet());   // key값들을 저장한 list
        keyList.sort((s1, s2) -> s1.compareTo(s2)); // key값 오름차순 정렬
        
        Set<Character> valueSet = new HashSet<>(map.values());  // HashSet을 이용하여 중복문자 제거
        List<Character> valueList = new ArrayList<>(valueSet);  // 중복문자 제거한 value값들을 저장한 list
        Collections.sort(valueList);                // value값 오름차순 정렬
        
        for(Character c: valueList) {
            for(String s: keyList) {
                if(c == map.get(s)) {
                    list.add(s);
                }
            }
        }
        //System.out.println(list);
        
        
        
        //return answer;
        return list;
    }
}