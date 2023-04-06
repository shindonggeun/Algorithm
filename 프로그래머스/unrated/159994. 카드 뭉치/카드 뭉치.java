import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        
        int start1 = 0;
        int start2 = 0;
        for(int i=0; i<goal.length; i++) {
            if(start1 < cards1.length) {
                if(goal[i].equals(cards1[start1])) {
                    start1++;
                }
            }
            
            if(start2 < cards2.length) {
                if(goal[i].equals(cards2[start2])) {
                    start2++;
                }
            }
        }
        
        if(start1 + start2 == goal.length) {
            answer = "Yes";
        }
        else {
            answer = "No";
        }
        
        return answer;
    }
}