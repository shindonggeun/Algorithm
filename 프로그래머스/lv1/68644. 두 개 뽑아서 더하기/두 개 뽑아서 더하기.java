import java.util.*;

class Solution {
    public List<Integer> solution(int[] numbers) {
        //int[] answer = {};
        Set<Integer> set = new HashSet<>();
        
        for(int i=0; i<numbers.length-1; i++) {
            for(int j=i+1; j<numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);   // HashSet이므로 중복 제외
            }
        }
        
        List<Integer> list = new ArrayList<>(set);  // HashSet -> List로 변환
        
        Collections.sort(list);
        System.out.println(list);
        
        return list;
    }
}