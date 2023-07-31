class Solution {
    public int solution(String s) {
        int answer = 0;
        // 숫자(인덱스)에 따른 영문자를 표현한 배열
        // [0] -> "zero", ... , [9] -> "nine"
        String[] num_word = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        // 영문자를 숫자로 변환시켜줄 수 있게끔 0 ~ 9까지 모든 숫자들 반복문 돌리기
        for(int i=0; i<num_word.length; i++) {
            // 문자열에서 해당 영문자를 숫자로 변환시켜줌
            s = s.replace(num_word[i], Integer.toString(i));
            //System.out.println(s);
        }
        answer = Integer.parseInt(s);   // String으로 표현된 숫자를 int형 정수로 변환
        
        return answer;
    }
}