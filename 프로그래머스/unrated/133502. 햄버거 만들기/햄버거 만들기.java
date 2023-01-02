import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        
        for(int i: ingredient) {
            if(i == 1 && stack.size() >= 3 && stack.lastIndexOf(3) == stack.size()-1 && stack.lastIndexOf(2) == stack.size() - 2 && stack.lastIndexOf(1) == stack.size() - 3) {
                answer++;
                stack.pop();    
                stack.pop();
                stack.pop();
            }
            else {
                stack.push(i);
            }
            //System.out.println(stack);
        }
        
        return answer;
    }
}