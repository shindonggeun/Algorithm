class Solution {
    public int solution(int n) {
        int[] answer = new int[n+1];
        // 0부터 n까지 순회 시작
        for(int i=0; i<=n; i++) {
            // 0인 경우
            if(i == 0) {
                answer[i] = 0;  // 0 저장
            }
            // 1인 경우
            else if(i == 1) {
                answer[i] = 1;  // 1 저장
            }
            // 그 이외의 수인 경우
            else {
                int sum = answer[i-2] + answer[i-1];    // 피보나치 수 적용한 뒤
                answer[i] = sum % 1234567;  // 해당 피보나치수를 1234567로 나눈 나머지 저장
            }
        }
        
        return answer[n];   // 해당 n번째 피보나치수를 1234567로 나눈 나머지 리턴해줌
    }
}