import java.util.*;

class Solution {
    public int solution(int[] array) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();    // key: 해당 숫자, value: 해당 숫자가 나온 횟수
        
        for(int i=0; i<array.length; i++) {
            // map.getOrDefault()메서드를 이용해 해당 숫자가 나올때마다 횟수를 늘려준다
            map.put(array[i], map.getOrDefault(array[i], 0) + 1);
        }
        
        List<Integer> list = new ArrayList<>(map.values()); // map에 저장된 value값들 리스트화
        //System.out.println(list);
        Collections.sort(list, Collections.reverseOrder()); // 내림차순 정렬
        
        List<Integer> resultList = new ArrayList<>();       // 최빈값들 저장한 리스트
        // 맵에 저장된 key값들을 이용하여 탐색
        for(int key: map.keySet()) {
            // key값(해당 숫자)에 해당하는 value값(해당 숫자가 나온 횟수)이 가장 많이 나온 것이면
            if(map.get(key) == list.get(0)) {
                resultList.add(key);    // 최빈값들 저장한 리스트에 추가
            }
        }
        
        // 최빈값들 저장한 리스트의 길이(사이즈)가 1보다 크면 (최빈값이 여러개라는 뜻)
        if(resultList.size() > 1) {
            answer = -1;    
        }
        // 최빈값을 저장한 리스트의 길이(사이즈)가 1보다 작거나 같은 경우 (즉, 최빈값 1개)
        else {
            answer = resultList.get(0);
        }
        
        return answer;
    }
}