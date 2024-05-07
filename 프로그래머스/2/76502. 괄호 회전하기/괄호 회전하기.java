import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        int n = s.length();
        
        // 문자열의 길이가 짝수인 경우
        if (n % 2 == 0) {
            for (int i=0; i<n; i++) {
                Stack<Character> stack = new Stack<>();
                // 회전시킨 문자열 만들기
                String str = s.substring(i, n) + s.substring(0, i);
                
                // 회전시킨 문자열에서 문자 하나씩 탐색하는 과정
                for (int j=0; j<n; j++) {
                    char ch = str.charAt(j);
                    
                    // 스택이 비어있는 경우
                    if (stack.isEmpty()) {
                        stack.push(ch); // 문자열에서 뽑은 문자 집어넣음
                    }
                    // 회전시킨 문자열에서 뽑은 문자가 닫는 괄호면서 스택에서 뽑은 문자가 여는 괄호인 경우
                    else if (ch == ')' && stack.peek() == '(') {
                        stack.pop();
                    }
                    else if (ch == '}' && stack.peek() == '{') {
                        stack.pop();
                    }
                    else if (ch == ']' && stack.peek() == '[') {
                        stack.pop();
                    }
                    // 그 이외의 경우는 (ex. 둘다 닫는 괄호거나 여는 괄호인 경우)
                    else {
                        stack.push(ch); // 회전시킨 문자열에서 뽑은 문자 스택에 집어넣음
                    }
                }
                
                // 스택이 비어있는 경우
                if (stack.isEmpty()) {
                    answer++;   // 해당 회전시킨 문자열은 올바른 괄호 문자열임
                }
            }
            
        }
        
        
        
        return answer;
    }
    

}