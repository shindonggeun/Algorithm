class Solution {
    public int solution(int n) {
        int answer = 0;
        String thirdDigit = "";
        
        while(n != 0) {
            int digit = n % 3;
            thirdDigit+=Integer.toString(digit);
            n = n /3;
        }
        
        int exp = thirdDigit.length() - 1;
        for(int i=0; i<thirdDigit.length(); i++) {
            if(thirdDigit.charAt(i) != '0') {
                answer+=((int)thirdDigit.charAt(i) - '0') * Math.pow(3, exp);
            }
            exp--;
        }
        
        return answer;
    }
}