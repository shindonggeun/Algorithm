import java.util.*;

class Solution {
    
    static Set<Integer> poketmonSet;
    
    public int solution(int[] nums) {
        int answer = 0;
        
        poketmonSet = new HashSet<>();
        
        for (int num: nums) {
            poketmonSet.add(num);
        }
        
        answer = nums.length / 2;
        if (poketmonSet.size() < answer) {
            answer = poketmonSet.size();
        }
        
        return answer;
    }
}