class Solution {
    public String solution(String code) {
        String answer = "";
        boolean mode = false;   // 일단 mode 0부터 시작
        for(int i=0; i<code.length(); i++) {
            char ch = code.charAt(i); // 한글자씩 뽑기
            // mode가 true(1)인 경우
            if(mode) {
                // ch가 '1'이 아닌 경우
                if(ch != '1') {
                    // index가 홀수 인 경우
                    if(i % 2 != 0) {
                        answer += ch;
                    }
                }
                // ch가 '1'인 경우
                else {
                    mode = !mode;   // mode값 반대로 바꿔줌 (0일 경우 1로, 1일 경우 0으로)
                }
            }
            // mode가 false(0)인 경우
            else {
                // ch가 '1'이 아닌 경우
                if(ch != '1') {
                    // index가 짝수 인 경우
                    if(i % 2 == 0) {
                        answer += ch;
                    }
                }
                // ch가 '1'인 경우
                else {
                    mode = !mode;   // mode값 반대로 바꿔줌 (0일 경우 1로, 1일 경우 0으로)
                }
            }
        }
        
        // 최종 결과 문자열이 빈 문자열인 경우
        if(answer.equals("")) {
            answer = "EMPTY";   // "EMPTY" 문자열 출력할 수 있게끔
        }
        return answer;
    }
}