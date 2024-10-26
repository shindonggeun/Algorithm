import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        
        if(s.charAt(0) == ')') {
            answer = false;
            return answer;
        }
        
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            // '(' 문자를 만나면 stack에 push
            if(ch == '(') {
                stack.push(ch);
            } 
            // ')' 문자를 만나는 경우
            else {
                // stack이 비어있지 않은 경우
                if (stack.isEmpty() == false) {
                    stack.pop();    // stack에서 최상단 값 빼냄
                }
            }
        }
        //System.out.println(stack);
        if (stack.size() > 0) {
            answer = false;
        }
        return answer;
    }
}