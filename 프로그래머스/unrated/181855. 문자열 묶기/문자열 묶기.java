import java.util.*;

class Solution {
    public int solution(String[] strArr) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();    // key: 문자열 길이, value: 해당 문자열 길이를 가진 문자열 개수
        
        for(int i=0; i<strArr.length; i++) {
            int length = strArr[i].length();   // 문자열 길이 저장
            map.put(length, map.getOrDefault(length, 0) + 1);   // 해당 문자열 길이를 가진 횟수 +1 증가
        }
        List<Integer> list = new ArrayList<>(map.values()); // 맵에 저장된 value값들 list화
        Collections.sort(list, Collections.reverseOrder()); // 리스트 내림차순 정렬
        
        answer = list.get(0);   // 0번째 인덱스에 해당하는 리스트 원소 저장(가장 개수가 크므로)
        
        return answer;
    }
}