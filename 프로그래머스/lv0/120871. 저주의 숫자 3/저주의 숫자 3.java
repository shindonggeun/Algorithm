class Solution {
    public int solution(int n) {
        int answer = 0;
        for(int i=1; i<=n; i++) {
            answer++;
            // 해당 수가 3의 배수이거나 또는 해당 수를 문자열로 변환해서 "3"이 포함되어 있는 경우
            while(answer % 3 ==0 || String.valueOf(answer).contains("3")) {
                answer++;   
            }
        }
        
        return answer;
    }
}