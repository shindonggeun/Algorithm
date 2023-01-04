class Solution {
    boolean solution(String s) {
        boolean answer = true;
        s = s.toLowerCase();    // 문자열 소문자로 변환(문제에서 개수 비교할 때 대문자 소문자 구별 안한다 했으므로)
        s = s.replaceAll("[^py]", "");    // 정규식 이용해서 소문자 p랑 y가 아닌것들은 빈문자로 치환 -> []에서 ^는 not
        int p_count = 0;
        int y_count = 0;
        
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == 'p') {
                p_count++;
            }
            if(ch == 'y') {
                y_count++;
            }
        }
        if(p_count == y_count) {
            answer = true;
        }
        else {
            answer = false;
        }

        return answer;
    }
}