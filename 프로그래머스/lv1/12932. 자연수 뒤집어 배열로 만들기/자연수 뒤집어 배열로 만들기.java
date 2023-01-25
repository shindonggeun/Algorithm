import java.util.*;

class Solution {
    public List<Integer> solution(long n) {
        //int[] answer = {};
        String num = Long.toString(n);
        //answer = new int[num.length()];
        List<Integer> list = new ArrayList<>();
        
        for(int i=num.length()-1; i>=0; i--) {
            list.add(Character.getNumericValue(num.charAt(i)));
        }
        
        return list;
    }
}