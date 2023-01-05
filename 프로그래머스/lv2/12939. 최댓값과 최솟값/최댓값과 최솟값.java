import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] numArr = s.split(" ");
        int max = Integer.parseInt(numArr[0]);
        int min = Integer.parseInt(numArr[0]);
        
        for(String number: numArr) {
            int num = Integer.parseInt(number);
            
            if(max < num) {
                max = num;
            }
            if(min > num) {
                min = num;
            }
        }
        answer = Integer.toString(min) + " " + Integer.toString(max);
        
        return answer;
    }
}