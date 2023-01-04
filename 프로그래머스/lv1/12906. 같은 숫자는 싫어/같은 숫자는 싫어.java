import java.util.*;

public class Solution {
    public Stack<Integer> solution(int []arr) {
        int[] answer = {};
        Stack<Integer> stack = new Stack<>();
        stack.add(arr[0]);
        
        for(int i=1; i<arr.length; i++) {
            if(stack.peek() != arr[i]) {
                stack.add(arr[i]);
            }
        }
        

        return stack;
    }
}