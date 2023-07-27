import java.util.*;

class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        
        for(int i=0; i<quiz.length; i++) {
            // 문자열 공백단위로 쪼개서 저장
            // 문자열.split("쪼갤 문자열 단위"); => return값은 문자열 배열로 리턴
            String[] quizSplit = quiz[i].split(" ");    
            // System.out.println(Arrays.toString(quizSplit));
            
            // [0] -> 숫자1, [1] -> 연산자, [2] -> 숫자2, [3] -> "=", [4] -> 연산해서 나온 결과값
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