import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        String num = Long.toString(n);
        String result = "";
        List<Integer> list = new ArrayList<>();
        
        for(int i=0; i<num.length(); i++) {
            list.add(Character.getNumericValue(num.charAt(i)));
        }
        
        Collections.sort(list, Collections.reverseOrder());    // list 내림차순 정렬
        
        for(int i: list) {
            result+=Integer.toString(i);
        }
        answer = Long.parseLong(result);
        return answer;
    }
}