import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        // 큐1, 2 선언 및 생성
        Queue<Integer> realQueue1 = new LinkedList<>();
        Queue<Integer> realQueue2 = new LinkedList<>();
        long queueSum1 = 0; // 큐1의 원소 합
        long queueSum2 = 0; // 큐2의 원소 합
        
        // 큐 저장하는 과정
        for(int i=0; i<queue1.length; i++) {
            realQueue1.add(queue1[i]);
            realQueue2.add(queue2[i]);
            queueSum1 += queue1[i];
            queueSum2 += queue2[i];
        }
        
        // 큐의 원소 합이 서로 같아질때 까지 반복
        // 큐1, 큐2중 큰쪽에서 작은쪽으로 숫자를 보낸다
        while(queueSum1 != queueSum2) {
            // 작업횟수가 큐의 길이의 4배한것을 넘어버리는 경우
            // 즉, 각 큐의 원소 합을 같게 만들 수 없는 경우
            if(answer > queue1.length * 4) {
                return -1;  // -1 반환
            }
            
            // 큐1의 원소 합이 큐2의 원소 합보다 큰 경우 (큐1합 > 큐2합 이므로 원소들 큐1 -> 큐2로 보내줘야함)
            if(queueSum1 > queueSum2) {
                int num1 = realQueue1.poll();  
                queueSum1 -= num1;  
                realQueue2.add(num1);   
                queueSum2 += num1;
            }
            // 큐2의 원소 합이 큐1의 원소 합보다 큰 경우 (큐1합 < 큐2합 이므로 원소들 큐2 -> 큐1로 보내줘야함)
            else if(queueSum1 < queueSum2){
                int num2 = realQueue2.poll();
                queueSum2 -= num2;
                realQueue1.add(num2);
                queueSum1 += num2;
            }
            answer++;   // 횟수 증가
        }
        
        return answer;
    }
    
}