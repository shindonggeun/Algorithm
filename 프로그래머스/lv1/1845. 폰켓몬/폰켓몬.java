import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int[] resultArr = Arrays.stream(nums).distinct().toArray();
        
        answer = nums.length / 2;
        if(resultArr.length < answer) {
            answer = resultArr.length;
        }
        
        return answer;
    }
}