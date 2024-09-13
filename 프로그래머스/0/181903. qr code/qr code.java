class Solution {
    public String solution(int q, int r, String code) {
        String answer = "";
        
        for(int i=0; i<code.length(); i++) {
            // 문자열의 각 인덱스를 q로 나누었을 때 나머지가 r인 경우
            if(i % q == r) {
                char ch = code.charAt(i);   // 해당 문자 뽑아내기
                answer += ch;   // 순서대로 붙이기
            }
        }
        
        return answer;
    }
}