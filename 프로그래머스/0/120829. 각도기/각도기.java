class Solution {
    public int solution(int angle) {
        int answer = 0;
        // 예각일때는 1
        if(0<angle && angle<90) {
            answer = 1;
        }
        // 직각일때는 2
        if(angle == 90) {
            answer = 2;
        }
        // 둔각일때는 3
        if(90<angle && angle<180) {
            answer = 3;
        }
        // 평각일때는 4
        if(angle == 180) {
            answer = 4;
        }
        return answer;
    }
}