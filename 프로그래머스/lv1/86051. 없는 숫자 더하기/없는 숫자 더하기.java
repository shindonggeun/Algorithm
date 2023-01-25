import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        List<Integer> list = new ArrayList<>();
        
        // 0 ~ 9까지 list에 숫자 추가
        for(int i=0; i<=9; i++) {
            list.add(i);
        }
        
        Arrays.sort(numbers);   // 리스트 정렬
        
        for(int num: numbers) {
            // 리스트에 해당 숫자가 있으면
            if(list.contains(num)) {
                // Integer.valueOf 안쓰고 그냥 num만 쓰면 num에 해당하는 인덱스값 삭제
                list.remove(Integer.valueOf(num));  // 리스트에 숫자에 해당하는 값 삭제 
            }
        }
        
        for(int i=0; i<list.size(); i++) {
            answer+=list.get(i);
        }
        
        return answer;
    }
}