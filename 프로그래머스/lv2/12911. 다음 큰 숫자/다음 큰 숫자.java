class Solution {
    public int solution(int n) {
        int answer = 0;
        
        String binaryString_n = Integer.toBinaryString(n);
        String noZero_n = binaryString_n.replace("0", "");
        //System.out.println(nozero_n);
        answer = n;
        
        while(true) {
            answer++;
            
            String binaryString_answer = Integer.toBinaryString(answer);
            String noZero_answer = binaryString_answer.replace("0", "");
            
            if(noZero_n.equals(noZero_answer)) {
                break;
            }
        }
        
        return answer;
    }
}