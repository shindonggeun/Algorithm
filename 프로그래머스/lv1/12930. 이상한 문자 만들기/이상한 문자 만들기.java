class Solution {
    public String solution(String s) {
        String answer = "";
        
        // 공백문자로 문자열 쪼개기
        // 뒤에 -1 인자를 넣어야 문자열 맨뒤에 공백이 나와도 그것을 단어로 취급해서 잘라줌
        /*String[] str = s.split(" ", -1);    
                                   
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
        
        return answer;*/
        
        int cnt = 0;
        String[] array = s.split("");

        for(String ss : array) {
            cnt = ss.contains(" ") ? 0 : cnt + 1;
            answer += cnt%2 == 0 ? ss.toLowerCase() : ss.toUpperCase(); 
        }
        return answer;
    }
}