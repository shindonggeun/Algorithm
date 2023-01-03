class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        // String 배열 선언 및 초기화 (문제에서 주어진 네가지 발음)
        String[] result = {"aya", "ye", "ma", "woo"};
        
        // 향상된 for문 이용 
        for(String bab: babbling) {
            // 4가지 발음 향상된 for문 이용
            for(String str: result) {
                // 애기가 발음한 문자열에서 주어진 발음 처음 나온것을 "1"로
                // ex) ayaye -> "1ye" -> "11"
                bab = bab.replaceFirst(str, "1");   
            }
            System.out.println(bab);
            if(bab.replace("1", "").equals("")) {
                answer++;
            } 
        }
        return answer;
    }
}