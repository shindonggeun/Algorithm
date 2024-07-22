import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        int currentTime = 0;
        int totalWaitTime = 0;
        int idx = 0;
        int jobCount = jobs.length;
        
        while (idx < jobCount || !pq.isEmpty()) {
            while (idx < jobCount && jobs[idx][0] <= currentTime) {
                pq.add(jobs[idx++]);
            }
            
            if (pq.isEmpty()) {
                currentTime = jobs[idx][0];
            }
            else {
                int[] nowJob = pq.poll();
                currentTime += nowJob[1];
                totalWaitTime += currentTime - nowJob[0];
            }
        }
        
        answer = totalWaitTime / jobCount;
        return answer;
    }
}