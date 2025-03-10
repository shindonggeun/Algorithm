class Solution {
    
    // 조카가 발음할 수 있는 단어 목록
    static String[] possibleWords = {"aya", "ye", "woo", "ma"};
    
    public int solution(String[] babbling) {
        int answer = 0;
        
        // 1. 주어진 문자열 배열에서 조카가 발음할 수 있는 단어들을 탐색하는 과정
        for (String babb: babbling) {
            // 가능한 단어들을 하나씩 탐색 
            for (String word: possibleWords) {
                babb = babb.replaceFirst(word, " "); // 첫 번째로 등장하는 해당 단어를 공백으로 치환
            }
            babb = babb.replaceAll(" ", ""); // 모든 공백을 제거
            
            // 해당 문자가 빈 문자열인 경우 -> 조카가 발음 가능한 단어임
            if (babb.equals("")) {
                answer++; // 결과 카운트 증가
            }
        }
        
        return answer;
    }
}