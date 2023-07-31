class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] word = {"aya", "ye", "woo", "ma"}; // 조카가 발음할 수 있는 것들
        
        // 완전탐색 이용
        // 향상된 for문 이용
        // babbling 문자열 배열을 탐색해서
        for(String babb: babbling) {
            // 조카가 발음할 수 있는 것들 돌리기
            for(String wo: word) {
                babb = babb.replaceFirst(wo, "1");   // 조카가 발음할 수 있는 것(문자열)이 처음 나온것들만 문자열 "1"로 대체
            }
            babb = babb.replaceAll("1", "");  // 최종으로 문자열 "1"이 들어간 것들은 전부 빈 문자열로 대체
            
            // 위의 과정을 거쳐 빈 문자열인 경우(즉, 발음할 수 있는 것인 경우)
            if(babb.equals("")) {
                answer++;   // 조카가 발음할 수 있는 단어임
            }
        }
        return answer;
    }
}