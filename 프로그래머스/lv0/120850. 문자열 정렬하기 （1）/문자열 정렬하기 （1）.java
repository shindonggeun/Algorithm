import java.util.*;

class Solution {
    public int[] solution(String my_string) {
        int[] answer = {};
        my_string = my_string.replaceAll("[a-z]", "");
        answer = new int[my_string.length()];
        
        for(int i=0; i<my_string.length(); i++) {
            answer[i] = Character.getNumericValue(my_string.charAt(i));
        }
        Arrays.sort(answer);
        //System.out.println(my_string);
        
        
        return answer;
    }
}