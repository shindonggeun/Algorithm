class Solution {
    public String solution(String s, int n) {
        String answer = "";
        
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            // 소문자인 경우
            if(Character.isLowerCase(ch)) {
                if(ch+n > 'z') {
                    ch = (char)(ch + n - 26);
                }
                else {
                    ch = (char)(ch + n);
                }
            }
            // 대문자인 경우
            else if(Character.isUpperCase(ch)) {
                if(ch+n > 'Z') {
                    ch = (char)(ch + n - 26);
                }
                else {
                    ch = (char)(ch + n);
                }
            }
            answer+=ch;
        }
        return answer;
    }
}