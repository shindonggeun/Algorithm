class Solution {
    public int solution(int[] common) {
        int answer = 0;
        
        // common의 길이가 3부터 시작!
        // 등차수열인 경우 
        if(common[1] - common[0] == common[2] - common[1]) {
            // common 배열의 맨 마지막 원소에서 공차만큼 더해주면 된다
            answer = common[common.length - 1] + (common[1] - common[0]);
        }
        // 등비수열인 경우
        else {
            // common 배열의 맨 마지막 원소에서 공비만큼 곱해주면 된다 
            answer = common[common.length - 1] * (common[1] / common[0]);
        }
        
         
        return answer;
    }
}