import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();   // number index 정보를 담을 stack 생성
        
        stack.push(0);
        
        // 두번째 원소부터 탐색
        for(int i=1; i<numbers.length; i++) {
            // 스택이 비어있지 않으면서 현재 스택이 바라보고 있는 값보다 number의 값이 큰 경우까지 반복
            while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.pop()] = numbers[i];   // 뒤에 있는 큰 수에 해당한다
            }
            
            stack.push(i);  // 현재 인덱스를 스택에 집어넣음
        }
        
        // 모든 index를 다 탐색한 후 뒤에 있는 큰 수가 없는 경우 -1임
        // 스택이 비어질때까지 반복함
        while(!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }
        
        return answer;
    }
}