class Solution {
    
    static int count; // 올바른 괄호 조합의 개수
    
    public int solution(int n) {
        int answer = 0;
        
        count = 0; // 올바른 괄호 조합의 개수 0으로 초기화
        generateBracket(0, 0, n); // 올바른 괄호를 만드는 메서드 호출
        
        answer = count; // 결과 변수에 올바른 괄호 조합의 개수 저장
        return answer;
    }
    
    // 올바른 괄호를 만드는 메서드
    // open: 현재 여는 괄호의 개수
    // close: 현재 닫히는 괄호의 개수
    // n: 괄호 쌍의 개수
    public void generateBracket(int open, int close, int n) {
        // 현재 여는 괄호의 개수가 n개면서 동시에 닫히는 괄호의 개수가 n개인 경우 (기저 조건)
        // 즉, 해당 경우는 올바른 괄호인 경우임
        if (open == n && close == n) {
            count++; // 올바른 괄호의 개수 증가
            return; // 메서드 종료
        }
        
        // 현재 여는 괄호의 개수가 n보다 작은 경우 (해당 경우는 '(' 추가 가능)
        if (open < n) {
            // 여는 괄호의 개수를 증가시켜 해당 메서드 재귀 호출
            generateBracket(open+1, close, n);
        }
        
        // 현재 닫히는 괄호의 개수가 여는 괄호의 개수보다 작은 경우 (해당 경우는 ')' 추가 가능)
        if (close < open) {
            // 닫히는 괄호의 개수를 증가시켜 해당 메서드 재귀 호출
            generateBracket(open, close+1, n);
        }
        
    }
}