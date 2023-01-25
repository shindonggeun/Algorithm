import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        List<Integer> list = new ArrayList<>();
        
        for(int i=0; i<=9; i++) {
            list.add(i);
        }
        
        Arrays.sort(numbers);
        
        for(int num: numbers) {
            if(list.contains(num)) {
                list.remove(Integer.valueOf(num));
            }
        }
        
        for(int i=0; i<list.size(); i++) {
            answer+=list.get(i);
        }
        
        return answer;
    }
}