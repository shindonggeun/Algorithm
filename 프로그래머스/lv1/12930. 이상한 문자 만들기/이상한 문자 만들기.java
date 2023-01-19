class Solution {
    public String solution(String s) {
        String answer = "";
        
        String[] str = s.split("");
        int cnt = 0;

        for(String ss : str) {
            if(ss.contains(" ")) {
                cnt = 0;
            }
            else {
                cnt+=1;
            }
            
            if(cnt%2 == 0) {
                answer+=ss.toLowerCase();
            }
            else {
                answer+=ss.toUpperCase();
            }
            //cnt = ss.contains(" ") ? 0 : cnt + 1;
            //answer += cnt%2 == 0 ? ss.toLowerCase() : ss.toUpperCase(); 
        }
        
        
        // 밑에 방법은 이중 반복문 사용하므로 시간과 메모리 사용량이 크다
        // split()에서 -1 인자를 넣었을 경우만 잘 알아두자!
        
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
        }*/
        
        return answer;
        
    }
}