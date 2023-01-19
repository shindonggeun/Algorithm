class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int cnt = 0;
        
        // 선택하는 수가 4개이상부터는 조합을 직접 구현해서 풀자!
        
        // nums배열에서 3개 조합 선택하기
        for(int i=0; i<nums.length-2; i++) {
            for(int j=i+1; j<nums.length; j++) {
                for(int k=j+1; k<nums.length; k++) {
                    cnt = divisorCount(nums[i] + nums[j] + nums[k]);    // 3개 수 선택한 것을 더해서 약수 개수 구하기
                    // 약수의 개수가 2개인 경우(소수인 경우)
                    if(cnt == 2) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
    
    // 약수 개수 구하는 함수
    public int divisorCount(int num) {
        int cnt = 0;
        // 속도측면에서 훨씬 빠르다
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