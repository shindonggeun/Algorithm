class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        if(s.length() == 4 || s.length() == 6) {
            answer = s.matches("^[0-9]*$"); // 문자열에서 숫자로만 이루어진 경우 true, 아니면 false 값 리턴
        }
        else {
            answer = false;
        }
        
        return answer;
    }
}