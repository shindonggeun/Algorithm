class Solution {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        int max = 0;    // 최대 공약수
        int min = 0;    // 최소 공배수
        
        max = gcd(n, m);
        min = lcm(n, m, max);
        
        answer[0] = max;
        answer[1] = min;
        
        return answer;
    }
    
    // 최대 공약수 구하는 메서드
    public int gcd(int n, int m) {
        // 나누는 수가 0이 아닐때까지 반복
        while(m != 0) {
		    int r = n % m;	// 나머지를 구해준다.
            
		    // GCD(a, b) = GCD(b, r)이므로 변환한다.
		    n = m;		
		    m = r;
	    }   
	    return n;
    }
    
    // 최소 공배수 구하는 메서드
    public int lcm(int n, int m, int gcd) {
        return n * m / gcd;
    }
}