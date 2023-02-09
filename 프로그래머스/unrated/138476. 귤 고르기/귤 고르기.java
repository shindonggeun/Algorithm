import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();    // key: 귤 숫자(크기), value: 해당 귤 개수
        
        for(int i: tangerine) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        List<Integer> list=new ArrayList<>(map.values());   // 귤의 개수를 담은 list
        Collections.sort(list, Collections.reverseOrder()); // 귤의 개수 내림차순 정렬
        
        for(int i: list) {
            k-=i;   // 귤의 개수 많은것부터 순회해서 귤 개수 차감
            answer++;   // 종류수 증가
            
            // 귤의 개수가 1보다 작아지면 반복문 빠져나옴
            if(k<1) {
                break;
            }
        }
        
        
        return answer;
    }
}