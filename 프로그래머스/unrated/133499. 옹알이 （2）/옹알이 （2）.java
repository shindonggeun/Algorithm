class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        // String 배열 선언 및 초기화 (문제에서 주어진 네가지 발음)
        String[] result = {"aya", "ye", "ma", "woo"};
        
        // 향상된 for문 이용
        for(String bab: babbling) {
            bab = babblingChangeNumber(bab, result);
            //System.out.println(bab);
            
            // 정규표현식 이용 -> ^: 문자열 시작, $: 문자열 끝, *: 앞문자가 없을수도 무한정 많을수도 있음
            // bab이 숫자로 이루어져있으면서 (정규표현식 이용)
            // 서로 다른 연속된 숫자인경우(123 이런경우, 11처럼 똑같은 숫자 연속되면 false)
            if(bab.matches("^[0-9]*$") && isNumberContinue(bab)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public String babblingChangeNumber(String bab, String[] result) {
        for(int i=0; i<result.length; i++) {
            bab = bab.replace(result[i], String.valueOf(i));
        }
        return bab;
    }
    
    public boolean isNumberContinue(String bab) {
        for(int i=0; i<bab.length()-1; i++) {
            if(bab.charAt(i) == bab.charAt(i+1)) {
                return false;
            }
        }
        return true;
    }
}