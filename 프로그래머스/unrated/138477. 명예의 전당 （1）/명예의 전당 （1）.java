import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for(int i = 0; i < score.length; i++) {

            priorityQueue.add(score[i]);
            
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
            answer[i] = priorityQueue.peek();   // 우선순위 가장 높은 값을 저장
        }
        
        return answer;
    }
}