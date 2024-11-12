class Solution {
    
    static int count;
    
    public int solution(int n) {
        int answer = 0;
        
        count = 0;
        generateBracket(0, 0, n);
        
        answer = count;
        return answer;
    }
    
    public void generateBracket(int open, int close, int n) {
        if (open == n && close == n) {
            count++;
            return;
        }
        
        if (open < n) {
            generateBracket(open+1, close, n);
        }
        
        if (close < open) {
            generateBracket(open, close+1, n);
        }
        
    }
}