class Solution {
    public long solution(String numbers) {
        long answer = 0;
        
        // 문자열에서 문자열로 변환
        numbers = numbers.replace("zero", "0");     // 문자열에서 "zero" 인것은 "0"으로 치환(변환)
        numbers = numbers.replace("one", "1");      // 문자열에서 "one" 인것은 "1"로 치환(변환)
        numbers = numbers.replace("two", "2");      // 문자열에서 "two" 인것은 "2"로 치환(변환)
        numbers = numbers.replace("three", "3");    // 문자열에서 "three" 인것은 "3"으로 치환(변환)
        numbers = numbers.replace("four", "4");     // 문자열에서 "four" 인것은 "4"로 치환(변환)
        numbers = numbers.replace("five", "5");     // 문자열에서 "five" 인것은 "5"로 치환(변환)
        numbers = numbers.replace("six", "6");      // 문자열에서 "six" 인것은 "6"으로 치환(변환)
        numbers = numbers.replace("seven", "7");    // 문자열에서 "seven" 인것은 "7"로 치환(변환)
        numbers = numbers.replace("eight", "8");    // 문자열에서 "eight" 인것은 "8"으로 치환(변환)
        numbers = numbers.replace("nine", "9");     // 문자열에서 "nine" 인것은 "9"으로 치환(변환)
        
        answer = Long.parseLong(numbers);   // String 타입의 숫자 Long 타입(wrapper 클래스)으로 변환
        return answer;
    }
}