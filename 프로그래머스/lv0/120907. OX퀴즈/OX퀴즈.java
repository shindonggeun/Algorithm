import java.util.*;

class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        
        for(int i=0; i<quiz.length; i++) {
            String[] quizSplit = quiz[i].split(" "); // 문자열 공백단위로 쪼개서 저장
            // System.out.println(Arrays.toString(quizSplit));
            
            int num1 = Integer.parseInt(quizSplit[0]);
            int num2 = Integer.parseInt(quizSplit[2]);
            int result = Integer.parseInt(quizSplit[4]);
            int temp = 0;
            
            if(quizSplit[1].equals("+")) {
                temp = num1 + num2;    
            }
            else if(quizSplit[1].equals("-")) {
                temp = num1 - num2;
            }
            // 계산이 옳은 수식이면 "O"
            if(result == temp) {
                answer[i] = "O";
            }
            // 계산이 틀린 수식이면 "X"
            else {
                answer[i] = "X";
            }
        }
        
        
        return answer;
    }
}