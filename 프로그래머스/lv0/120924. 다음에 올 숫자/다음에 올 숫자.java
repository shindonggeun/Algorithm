class Solution {
    public int solution(int[] common) {
        int answer = 0;
        int result = 0;
        int length = common.length;
        
        // 등차수열인 경우
        if(common[1] - common[0] == common[2] - common[1]) {
            result = common[1] - common[0];
            answer = common[common.length - 1] + result;
        } 
        // 등비수열인 경우
        else {
            result = common[1] / common[0];
            answer = common[common.length - 1] * result;
        }
        
         
        return answer;
    }
}