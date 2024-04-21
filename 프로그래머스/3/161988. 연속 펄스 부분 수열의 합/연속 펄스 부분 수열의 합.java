class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        boolean change = true;  // 현재 원소에 적용할 펄스 패턴의 부호를 결정하는 변수 (true면 1, false면 -1)
        
        long pulse1 = 0;    // 1부터 시작하는 펄스 수열에 연속 부분수열 곱한 값을 더한 결과값 [1, -1, 1, ...]
        long pulse2 = 0;    // -1부터 시작하는 펄스 수열에 연속 부분수열 곱한 값을 더한 결과값 [-1, 1, -1, ...]
        
        for (int num: sequence) {
            pulse1 += change ? num : -num;
            pulse2 += change ? -num : num;
            
            // 부분 수열의 합이 절대 0보다 작을 수 없다는 것을 이용
            // 음수가 되는 경우 합을 0으로 리셋
            // 카데인 알고리즘의 변형으로, 음수가 되면 그 부분을 포기하고 새로운 시작을 모색
            pulse1 = Math.max(0, pulse1);
            pulse2 = Math.max(0, pulse2);
            
            answer = Math.max(answer, Math.max(pulse1, pulse2));
            change = !change;
        }
        
        return answer;
    }
}