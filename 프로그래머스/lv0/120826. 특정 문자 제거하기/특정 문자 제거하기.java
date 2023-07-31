class Solution {
    public String solution(String my_string, String letter) {
        String answer = "";
        answer = my_string.replace(letter, ""); // 해당 문자열에서 특정 문자(letter)를 빈문자열로 대체 (제거)
        
        return answer;
    }
}