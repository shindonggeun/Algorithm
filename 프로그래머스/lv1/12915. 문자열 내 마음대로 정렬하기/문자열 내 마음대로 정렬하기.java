import java.util.*;

class Solution {
    public List<String> solution(String[] strings, int n) {
        //String[] answer = new String[strings.length];
        Map<String, Character> map = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        
        for(String s: strings) {
            map.put(s, s.charAt(n));
        }
        
        List<String> keyList = new ArrayList<>(map.keySet());
        keyList.sort((s1, s2) -> s1.compareTo(s2)); // key값 오름차순 정렬
        
        Set<Character> valueSet = new HashSet<>(map.values());
        List<Character> valueList = new ArrayList<>(valueSet);
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