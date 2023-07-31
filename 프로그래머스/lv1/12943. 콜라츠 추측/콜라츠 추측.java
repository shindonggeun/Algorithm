class Solution {
    public int solution(int num) {
        int answer = 0;
        long number = num;
        // 입력된 수가 1이 아닐때까지 반복(1이 될 때까지 반복)
        while(number!=1) {
            // 짝수인 경우
            if(number%2 == 0) {
                number/=2;  // 2로 나눠줌
            }
            // 홀수인 경우
            else {
                number = number*3 + 1;  // 3을 곱하고 1을 더해줌
            }
            answer++;   // 해당 횟수 증가
            
            // 해당 횟수가 500번이 넘어가면
            if(answer > 500) {
                answer = -1;    // -1 저장
                break;  // while문 빠져나옴
            }
        }
        
        return answer;
    }
}