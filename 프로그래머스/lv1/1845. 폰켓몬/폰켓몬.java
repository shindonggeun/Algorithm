import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        HashSet<Integer> set = new HashSet<>(); // HashSet을 이용하여 배열의 중복값 제거
        
        // nums배열의 길이만큼 반복문 돌려서 중복제거한 값을 HashSet에 넣는다
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