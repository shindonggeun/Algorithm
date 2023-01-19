class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int cnt = 0;
        
        for(int i=0; i<nums.length-2; i++) {
            for(int j=i+1; j<nums.length; j++) {
                for(int k=j+1; k<nums.length; k++) {
                    cnt = divisorCount(nums[i] + nums[j] + nums[k]);
                    
                    if(cnt == 2) {
                        answer++;
                    }
                }
            }
        }
        

        return answer;
    }
    
    public int divisorCount(int num) {
        int cnt = 0;
        for(int i=1; i*i<=num; i++) {
            if(i*i == num) {
                cnt++;
            }
            else if(num%i == 0) {
                cnt+=2;
            }
        }
        return cnt;
    }
}