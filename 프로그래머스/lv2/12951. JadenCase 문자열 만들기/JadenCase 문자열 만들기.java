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
            // 빈 문자열인 경우
            if(str.equals("")) {
                answer+=" ";
            }
            else {
                answer+=Character.toUpperCase(str.charAt(0)) + str.substring(1, str.length());
                answer+=" ";
            }
        }
        
        //원래 문자열 마지막이 공백일 경우 그대로 answer 반환
        if(s.substring(s.length() -1, s.length()).equals(" ")) return answer;
        //마지막에 공백이 더해져서 그 공백을 제외한 answer값 반환
        return answer.substring(0, answer.length() - 1);
        
        //return answer;
    }
}