class Solution {
    public int solution(String s) {
        int answer = 0;
        int x_count = 0;    // 문자열에서 x가 나온 횟수(문제 참고)
        int notX_count = 0; // 문자열에서 x가 아닌 횟수(문제 참고)
        char x = s.charAt(0);   // 첫글자 읽을 때 이 글자를 x로 놓음
        //String str = "";    // 문자열 분리된 것 저장용 (테스트 용도)
        
        // 문자열 길이만큼 반복문 돌리기(시간복잡도: n)
        for(int i=0; i<s.length(); i++) {
            // 문자열 왼쪽에서부터 읽을때 x와 같은 경우(같은 문자)
            if(x == s.charAt(i)) {
                x_count++;  // x변수 카운트 증가
            }
            // x와 다른 경우(다른 문자)
            else {
                notX_count++;   // x가 아닌 카운트변수 증가
            }
            //str+=s.charAt(i);   
            
            // x랑 x가 아닌 다른글자 나온 횟수 같을때
            if(x_count == notX_count) {
                // 문자열에서 맨마지막 문자가 아닌 경우
                if(i != s.length()-1) {
                    x = s.charAt(i+1);  // x를 다음 문자열에서 다음번 문자로 저장함
                }
                //System.out.println(str);
                x_count = 0;    // x가 나온 횟수 0으로 초기화
                notX_count = 0; // x가 아닌 횟수 0으로 초기화
                answer++;
                //str = "";
            }
            // x랑 x가 아닌 다른글자 나온 횟수 다른 경우
            else {
                // 문자열에서 맨 마지막 문자인 경우 (입출력 예 #2 같은 경우)
                if(i == s.length()-1) {
                    answer++;
                }
            }
            
        }
        
        return answer;
    }
}