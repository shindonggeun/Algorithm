import java.util.*;

class Solution {
    
    static boolean[] isPrime; // 각 수마다 소수인지 여부를 저장하는 배열
    
    public int solution(int n) {
        int answer = 0;
        
        isPrime = new boolean[n+1]; // [0] ~ [n]
        Arrays.fill(isPrime, true); // 초기 모든 수를 소수라고 세팅
        
        sieveOfEratosthenes(n); // 에라토스테네스의 체 이용
        
        // 2부터 n까지 순회하면서 소수인 수를 카운트
        for (int i=2; i<=n; i++) {
            // 해당 수가 소수인 경우
            if (isPrime[i]) {
                answer++; // 카운트 증가
            }
        }
        
        return answer;
    }
    
    // 소수 판별 알고리즘 중 하나인 에라토스테네스의 체 알고리즘 메서드
    public void sieveOfEratosthenes(int n) {
        // 0과 1은 소수가 아님
        isPrime[0] = false;
        isPrime[1] = false;
        
        // 2부터 n의 제곱근까지 반복
        for (int i=2; i*i<=n; i++) {
            // 해당 수가 소수가 아닌 경우
            if (!isPrime[i]) {
                continue; // 다음 수로 넘어감
            }
            
            // i가 소수인 경우, i의 배수들을 소수 아님(false)으로 처리하는 과정
            for (int j=i*i; j<=n; j+=i) {
                isPrime[j] = false; // 소수 아님
            }
        }
    }
}