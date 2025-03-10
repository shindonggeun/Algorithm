class Solution {
    
    static String[] possibleWords = {"aya", "ye", "woo", "ma"};
    
    public int solution(String[] babbling) {
        int answer = 0;
        
        for (String babb: babbling) {
            for (String word: possibleWords) {
                babb = babb.replaceFirst(word, " ");
            }
            babb = babb.replaceAll(" ", "");
            
            if (babb.equals("")) {
                answer++;
            }
        }
        
        return answer;
    }
}