import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        s = s.toLowerCase();
    	String[] strArr = s.split(" ");
        
        for(String str: strArr) {
            // split해서 저장한 문자열 배열에서 각 인덱스에 있는 문자열값이 빈 문자열인 경우 -> ex) " for the last week " 
            // 여기서 strArr[0] 값은 빈 문자열(null)이 들어간다. -> strArr[0] = "" -> 이때 문자열 길이는 0임!!(중요)
            if(str.equals("")) {
                //System.out.println(str.length());
                answer+=" ";
            }
            // 문자열 배열에서 저장된 문자열값이 빈 문자열이 아닌 경우
            else {
                answer+=Character.toUpperCase(str.charAt(0)) + str.substring(1, str.length());  // 첫 글자 대문자로 변환 + 문자열의 1번 인덱스부터 문자열의 길이 전 인덱스까지 추출한거 더해서 answer에 저장
                answer+=" ";    // 그다음 다시 띄어쓰기 
            }
        }
        
        //원래 문자열 마지막이 공백이 아닐 경우 answer에 저장된 문자열에서 공백 제거할 수 있도록 substring
        if(s.substring(s.length() -1, s.length()).equals(" ") == false) {
            answer = answer.substring(0, answer.length() - 1);
        }
        
        return answer;
    }
}