class Solution {
    public String solution(String s, int n) {
        String answer = "";
        
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            // 소문자인 경우
            if(Character.isLowerCase(ch)) { 
                ch = (char) ((ch - 'a' + n) % 26 + 'a');
            }
            // 대문자인 경우
            else if(Character.isUpperCase(ch)) {
                ch = (char) ((ch - 'A' + n) % 26 + 'A');
            }
            answer+=ch;
        }
        return answer;
    }
}