import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();    // key: 귤 숫자(크기), value: 해당 귤 개수
        
        for(int i: tangerine) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        List<Integer> list=new ArrayList<>(map.keySet());   // 귤의 번호를 담은 list
        
        // 귤 번호에 따른 개수 내림차순 정렬
        Collections.sort(list, (o1, o2) -> map.get(o2) - map.get(o1)); 
        
        for(int key: list) {
            k-=map.get(key);   // 귤의 개수 많은것부터 순회해서 귤 개수 차감
            answer++;   // 종류수 증가
            
            // 귤의 개수가 0이하인 경우 반복문 빠져나옴
            if(k<=0) {
                break;
            }
        }
        
        
        return answer;
    }
}