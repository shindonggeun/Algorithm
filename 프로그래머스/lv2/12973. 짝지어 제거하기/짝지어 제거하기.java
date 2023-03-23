import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = -1;
        Stack<Character> stack = new Stack<>();
        
        stack.push(s.charAt(0));    // 문자열 s에서 첫번째 글자 스택에 push
        
        // 문자열 인덱스 1번부터 끝까지 탐색
        for(int i=1; i<s.length(); i++) {
            // 스택이 비어있지 않은 경우
            if(!stack.empty()) {
                // 스택의 최상단 값이 비교할 문자열의 문자와 같은 경우(짝지어 진 경우)
                if(stack.peek() == s.charAt(i)) {
                    stack.pop();    // 스택에서 빼냄
                }
                // 짝지어 지지 않은 경우
                else {
                    stack.push(s.charAt(i));    // 스택에 집어넣음
                }
            }
            // 스택이 비어있는 경우
            else {
                stack.push(s.charAt(i));    // 스택에 집어넣음
            }
        }
        
        // 스택이 비어있으면 문자열 모두 짝지어 제거하기 성공
        if(stack.size() == 0) {
            answer = 1;
        }
        // 스택이 비어있지 않으면 문자열 짝지어서 제거할 수 없음
        else {
            answer = 0;
        }
        
        return answer;
    }
}