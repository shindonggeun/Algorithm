class Solution {
    public String solution(String s) {
        String answer = "";
        String[] str = s.split(" ", -1);    // 공백문자로 문자열 쪼개기        
        
        for(int i=0; i<str.length; i++) {
            for(int j=0; j<str[i].length(); j++) {
                // 짝수면 대문자로
                if(j%2==0) {
                    answer+=Character.toUpperCase(str[i].charAt(j));
                }
                // 홀수면 소문자로
                else {
                    answer+=Character.toLowerCase(str[i].charAt(j));
                }
            }
            if(i!=str.length-1) {
                answer+=" ";
            }
        }
        
        return answer;
    }
}