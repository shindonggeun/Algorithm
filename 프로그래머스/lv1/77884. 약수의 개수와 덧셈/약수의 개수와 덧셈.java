class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for(int i=left; i<=right; i++) {
            int cnt = countNumber(i);   // 약수 개수 구하기
            
            // 약수개수가 짝수이면 더함
            if(cnt % 2 == 0) {
                answer+=i;
            }
            // 약수개수가 홀수이면 빼버림
            else {
                answer-=i;
            }
        }
        
        return answer;
    }
    
    public int countNumber(int num) {
        int count = 0;
        
        for(int i=1; i*i<=num; i++) {
            if(i*i == num) {
                count++;
            }
            else if(num % i == 0){
                count+=2;
            }
        }
        return count;
    }
}