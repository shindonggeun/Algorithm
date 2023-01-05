import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        //s = s.replace(" ", ",");
        //System.out.println(s);
        s = s.toLowerCase();
    	String[] strArr = s.split(" ");
        
        for(String str: strArr) {
            System.out.println(str);
            // 빈 문자열인 경우 ()
            if(str.equals("")) {
                answer+=" ";
            }
            else {
                answer+=Character.toUpperCase(str.charAt(0)) + str.substring(1, str.length());
                answer+=" ";
            }
        }
        
        //원래 문자열 마지막이 공백이 아닐 경우
        if(s.substring(s.length() -1, s.length()).equals(" ") == false) {
            answer = answer.substring(0, answer.length() - 1);
        }
        
        return answer;
    }
}