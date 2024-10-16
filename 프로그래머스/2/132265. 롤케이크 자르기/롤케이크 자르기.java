import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Set<Integer> leftSet = new HashSet<>();
        Map<Integer, Integer> rightMap = new HashMap<>();
        
        for (int to: topping) {
            rightMap.put(to, rightMap.getOrDefault(to, 0) + 1);
        }
        
        int cutCount = 0;
        
        for (int to: topping) {
            leftSet.add(to);
            
            rightMap.put(to, rightMap.get(to) - 1);
            if (rightMap.get(to) == 0) {
                rightMap.remove(to);
            }
            
            if (leftSet.size() == rightMap.size()) {
                cutCount++;
            }
        }
        
        answer = cutCount;
        
        return answer;
    }
}