import java.util.*;

class Solution {
    
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        
        List<Integer> deployCountList = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i=0; i<progresses.length; i++) {
            int remainDay = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            
            queue.add(remainDay);
        }
        
        while (!queue.isEmpty()) {
            int deployDay = queue.poll();
            int count = 1;
            
            while (!queue.isEmpty() && queue.peek() <= deployDay) {
                queue.poll();
                count++;
            }
            
            deployCountList.add(count);
        }
        
        answer = new int[deployCountList.size()];
        
        for (int i=0; i<deployCountList.size(); i++) {
            answer[i] = deployCountList.get(i);
        }
        
        return answer;
    }
}