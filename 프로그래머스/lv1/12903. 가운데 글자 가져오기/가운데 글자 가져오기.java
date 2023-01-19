class Solution {
    public String solution(String s) {
        String answer = "";
        int middle = s.length() / 2;    // 문자열의 가운데 인덱스 구하기
        
        // 단어의 길이가 짝수라면 가운데 두글자 반환
        if(s.length()%2 == 0) {
            answer+=s.charAt(middle-1);
            answer+=s.charAt(middle);
        }
        // 단어의 길이가 짝수가 아니라면 가운데 글자 반환
        else {
            answer+=String.valueOf(s.charAt(middle));
        }
        
        return answer;
    }
}