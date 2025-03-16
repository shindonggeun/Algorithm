import java.util.*;

public class Solution {
    
    static Stack<Integer> stack;
    
    public int[] solution(int[] arr) {
        int[] answer = {};
        
        stack = new Stack<>();
        stack.add(arr[0]);
        
        for (int i=1; i<arr.length; i++) {
            if (stack.peek() != arr[i]) {
                stack.add(arr[i]);
            }
        }
        
        answer = new int[stack.size()];
        
        for (int i=answer.length-1; i>=0; i--) {
            answer[i] = stack.pop();
        }

        return answer;
    }
}