import java.util.*;

class Solution {
    public int[] answer;    // 전역변수 설정
    
    public int[] solution(String s) {
        answer = new int[2];
        int count = 0;
        
        // 문자열 s가 "1"이 될때까지 반복하기
        while(!s.equals("1")) {
            s = revertBinary(s);    // 문자열 s를 문제에서 주어진대로 이진변환 반복하기
            count++;    // 변환횟수 증가
        }
        answer[0] = count;  // 변환횟수 배열에 저장
        
        return answer;
    }
    
    public String revertBinary(String s) {
        String binary = "";
        int original = s.length();  // 파라미터로 날라온 문자열 s의 길이
        
        binary = s.replaceAll("[0]", "");   // 정규표현식을 이용해서 문자열 s에서 0 모두 제거
        int change = binary.length();   // 문자열 s에서 모든 "0"을 제거하고 남은 문자열의 길이
        
        int deleteZeroCount = original - change;    // 0을 제거한 개수
        
        answer[1] += deleteZeroCount;   // 제거한 0의 개수 더해주기
        
        binary = Integer.toBinaryString(change); // 10진수 -> 2진수
        
        return binary;
    }
}