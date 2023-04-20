class Solution {
    public int solution(int n) {
        int answer = 0;
        
        String binaryString_n = Integer.toBinaryString(n);  // n을 이진수로 바꾼 뒤
        String noZero_n = binaryString_n.replace("0", "");  // 바꾼 이진수에서 0을 모두 제거
        //System.out.println(nozero_n);
        answer = n + 1; // n 다음의 수
        
        while(true) {
            
            String binaryString_answer = Integer.toBinaryString(answer);    // n 다음의 수를 이진수로 바꾼뒤
            String noZero_answer = binaryString_answer.replace("0", "");    // 바꾼 이진수에서 0을 모두 제거
            
            // 0을 제거한 n 다음의 수가 n과 1의 개수가 같으면 무한반복 빠져나옴
            if(noZero_n.equals(noZero_answer)) {
                break;
            }
            
            answer++;
        }
        
        return answer;
    }
}