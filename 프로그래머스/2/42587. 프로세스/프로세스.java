import java.util.*;

class Solution {
    
    // 프로세서의 정보를 담고있는 내부 클래스
    static class Process {
        int priority;   // 우선순위
        int location;   // 위치
        
        public Process(int priority, int location) {
            this.priority = priority;
            this.location = location;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Process> queue = new LinkedList<>();
        
        // 큐에 우선순위 및 위치를 저장하는 과정
        for (int i=0; i<priorities.length; i++) {
            queue.add(new Process(priorities[i], i));
        }
        
        while (!queue.isEmpty()) {
            Process current = queue.poll(); // 큐에서 프로세스 꺼냄 (현재 프로세스)
            boolean isHigh = false;  // 우선순위 높은 프로세스인지 여부 확인하는 변수
            
            for (Process p: queue) {
                // 큐에서 순차적으로 탐색해서 꺼낸 프로세스의 우선순위가 현재 우선순위보다 큰 경우 
                if (p.priority > current.priority) {
                    isHigh = true; // 우선순위 높은 프로세스 찾음
                    break;  // 반복문 빠져나옴
                }
            }
            
            // 우선순위 높은 프로세스를 찾은 경우
            if (isHigh) {
                queue.add(current); // 꺼낸 프로세스를 큐의 뒤에 다시 넣음
            }
            else {
                answer++;   // 프로세스 실행한 횟수 증가 (실행 순서 기록)
                // 꺼낸 프로세스의 위치가 찾으려는 위치인 경우
                if (current.location == location) {
                    break;  // 더이상 큐 탐색 하지 않아도 되므로 반복문 종료
                }
            }
        }
        
        
        return answer;
    }
}