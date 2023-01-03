import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        HashSet<Integer> set = new HashSet<>();
        
        for(int i=0; i<nums.length; i++) {
            set.add(nums[i]);
        }
        answer = nums.length / 2;
        if(set.size() < answer) {
            answer = set.size();
        }
        /*int[] resultArr = Arrays.stream(nums).distinct().toArray();
        
        answer = nums.length / 2;
        if(resultArr.length < answer) {
            answer = resultArr.length;
        }*/
        
        return answer;
    }
}