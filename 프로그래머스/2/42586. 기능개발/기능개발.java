import java.util.*;

class Solution {
    
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        
        List<Integer> deployCountList = new ArrayList<>(); // 각 배포마다 몇 개의 기능이 배포될지 저장할 리스트
        Queue<Integer> queue = new LinkedList<>(); // 기능이 완료되기 까지 걸리는 일수를 저장할 큐
        
        for (int i=0; i<progresses.length; i++) {
            // 각 기능의 남은 작업 일수 계산
            // 100.0으로 double형으로 계산한 뒤 개발 속도로 나눠줌 -> 그 다음 올림 연산을 한 뒤 정수로 변환
            // 제대로 된 남은 작업 일수를 계산하기 위해
            int remainDay = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            
            queue.add(remainDay); // 큐에 남은 작업 일수 저장
        }
        
        // 큐 빌 때까지 반복
        while (!queue.isEmpty()) {
            int deployDay = queue.poll(); // 큐에서 현재 남은 작업 일수 (배포 일수) 뽑기
            int count = 1; // 현재 배포에 포함할 기능 수 1로 초기화
            
            // 큐에 남아있는 기능 중 함께 배포될 수 있는 기능 찾는 과정
            // 큐가 비어 있지 않으면서 동시에 현재 큐에 남은 작업 일수가 현재 배포 일수보다 작거나 같은 경우
            while (!queue.isEmpty() && queue.peek() <= deployDay) {
                queue.poll(); // 큐에서 함께 배포될 기능 뽑아내기
                count++; // 배포에 포함될 기능 수 증가
            }
            
            deployCountList.add(count); // 이번 배포에 포함된 기능 수 리스트에 추가
        }
        
        // 결과값 리스트에서 배열로 변환 과정
        answer = new int[deployCountList.size()];
        
        for (int i=0; i<deployCountList.size(); i++) {
            answer[i] = deployCountList.get(i);
        }
        
        return answer;
    }
}