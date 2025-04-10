class Solution {
    public long solution(long n) {
        long answer = 0;
        
        long sqrt = (long) Math.sqrt(n); // n의 제곱근 구하기(정수로 나타나게끔)
        
        if(sqrt * sqrt == n) {
            answer = (sqrt+1) * (sqrt+1);
        }
        else {
            answer = -1;
        }
        
        return answer;
    }
}