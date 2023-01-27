import java.time.*;
import java.time.format.TextStyle;
import java.util.*;

class Solution {
    public String solution(int a, int b) {
        String answer = "";
        LocalDate date = LocalDate.of(2016, a, b);
        //System.out.println(date); 
 
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        answer = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US);
        answer = answer.toUpperCase();
        
        
        return answer;
    }
}