import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        int boxNumber = 1;
        
        int truckBoxCount = 0;
        
        while (boxNumber <= order.length) {
            if (boxNumber == order[idx]) {
                truckBoxCount++;
                idx++;
                boxNumber++;
            }
            else if (!stack.isEmpty() && stack.peek() == order[idx]) {
                stack.pop();
                truckBoxCount++;
                idx++;
            }
            else {
                stack.push(boxNumber);
                boxNumber++;
            }
        }
        
        while (!stack.isEmpty()) {
            if (stack.peek() == order[idx]) {
                stack.pop();
                truckBoxCount++;
                idx++;
            }
            else {
                break;
            }
        }
        
        answer = truckBoxCount;
        
        return answer;
    }
}