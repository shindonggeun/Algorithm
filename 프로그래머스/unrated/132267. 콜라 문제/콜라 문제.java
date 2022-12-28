class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        // 보유중인 빈병이 2개 미만이면 콜라 받을 수 없음
        // 마트에 빈병 a개 주면 콜라 b병 줌, 보유중인 빈병 n개
        int remain = b;
        while(n>=a) {
            //System.out.println("보유중인 빈병 수: " + n);
            
            b = (n / a)*remain;  // 마트에 빈병 줘서 콜라 받음
            n = b + n%a;  // 콜라 다 마셔서 다시 빈병 + 마트에 주고 남은 빈병
            answer+=b;
            //System.out.println("마신콜라수: " + answer + ", 빈병수: " + n);
        }
        return answer;
    }
}