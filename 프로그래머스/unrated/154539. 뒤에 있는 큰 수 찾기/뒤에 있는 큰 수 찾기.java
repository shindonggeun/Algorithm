import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();   // number index 정보를 담을 stack 생성
        
        stack.push(0);
        
        // 두번째 원소부터 탐색
        for(int i=1; i<numbers.length; i++) {
            // 스택이 텅빌때까지 반복(비어지면 while문 탈출)
            while(!stack.isEmpty()) {
                int idx = stack.pop();  // 스택에서 담겨진 인덱스 뽑음
                // 뒤의 수가 더 큰 경우
                if(numbers[i] > numbers[idx]) {
                    answer[idx] = numbers[i];
                }
                // 앞이 더 크거나 같은 경우
                else {
                    stack.push(idx);
                    break;
                }
            }
            stack.push(i);
        }
        
        // 모든 index를 다 탐색한 후 뒤에 있는 큰 수가 없는 경우 -1임
        // 스택이 비어질때까지 반복함
        while(!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }
        
        return answer;
    }
}