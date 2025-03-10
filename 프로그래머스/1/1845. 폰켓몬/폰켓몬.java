import java.util.*;

class Solution {
    
    // 중복을 제거하여 폰켓몬 종류를 저장할 Set 선언
    static Set<Integer> poketmonSet;
    
    public int solution(int[] nums) {
        int answer = 0;
        
        poketmonSet = new HashSet<>(); // 해시셋 초기화 (중복 제거)
        
        // 1. 해당 폰켓몬 종류들 해시셋에 저장하는 과정
        for (int num: nums) {
            poketmonSet.add(num);
        }
        
        // 최대 선택 가능한 폰켓몬 개수 (N/2)와 실제 폰켓몬 종류 수 중 최소값 비교하여 결과값에 저장
        answer = Math.min(nums.length / 2, poketmonSet.size());
        
        return answer;
    }
}