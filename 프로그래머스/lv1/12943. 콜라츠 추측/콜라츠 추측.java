class Solution {
    public int solution(int num) {
        int answer = 0;
        long number = num;
        // 입력된 수가 1이 아닐때까지 반복(1이 될떄까지 반복)
        while(number!=1) {
            
            // 짝수인 경우
            if(number%2 == 0) {
                number/=2;
            }
            // 홀수인 경우
            else {
                number = number*3 + 1;
            }
            answer++;
            
            if(answer > 500) {
                answer = -1;
                break;
            }
        }
        
        return answer;
    }
}