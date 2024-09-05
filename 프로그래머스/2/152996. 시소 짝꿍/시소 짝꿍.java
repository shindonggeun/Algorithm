import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Map<Double, Integer> countMap = new HashMap<>();
        
        Arrays.sort(weights);
        
        for (int weight: weights) {
            double a = weight * 1.0;
            double b = (weight * 2.0) / 3.0;
            double c = (weight * 1.0) / 2.0; // 2 : 4 -> 1 : 2
            double d = (weight * 3.0) / 4.0;
            
            if (countMap.containsKey(a)) {
                answer += countMap.get(a);
            }
            
            if (countMap.containsKey(b)) {
                answer += countMap.get(b);
            }
            
            if (countMap.containsKey(c)) {
                answer += countMap.get(c);
            }
            
            if (countMap.containsKey(d)) {
                answer += countMap.get(d);
            }
            
            countMap.put((weight * 1.0), countMap.getOrDefault(weight * 1.0, 0) + 1);
        }
        
        return answer;
    }
}