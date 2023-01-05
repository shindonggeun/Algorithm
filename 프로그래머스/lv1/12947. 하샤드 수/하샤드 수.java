class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        String num = Integer.toString(x);
        int sum = 0;
        
        for(int i=0; i<num.length(); i++) {
            char ch = num.charAt(i);
            int number = Character.getNumericValue(ch);
            sum+=number;
        }
        // 입력값이 모든 자릿수의 합으로 나누어 떨어지면 true
        if(x % sum == 0) {
            answer = true;
        }
        else {
            answer = false;
        }
        return answer;
    }
}