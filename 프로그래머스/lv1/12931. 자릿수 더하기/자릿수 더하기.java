import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        String result = Integer.toString(n);
        
        for(int i=0; i<result.length(); i++) {
            int num = Character.getNumericValue(result.charAt(i));
            answer+=num;
        }

        return answer;
    }
}