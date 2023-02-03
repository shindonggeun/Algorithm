import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        Set<Character> set = new HashSet<>();   // 중복 X, 검색 빠름
        
        // 건너뛰어야하는 알파벳들 HashSet에 집어넣기 
        for(int i=0; i<skip.length(); i++) {
            set.add(skip.charAt(i));
        }
        
        for (int i = 0; i<s.length(); i++) {
            char ch = s.charAt(i); // 단어에서 각 자리의 알파벳 뽑기
            
            // 알파벳들 인덱스만큼 증가
            for (int j=0; j<index; j++) {
                ch++;   
                // 알파벳이 z를 넘어가면 a로 바꿔줌
                if (ch > 'z') {
                    ch = 'a';
                }
                // 건너뛰어야하는 알파벳인 경우 (HashSet은 검색 빠름)
                if (set.contains(ch)) {
                    j--;    // 반복문 변수 감소
                }
            }
            answer+=ch;
        }
        return answer;
    }
}