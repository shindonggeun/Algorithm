import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] sum = new int[n+1];   // 누적합 이용
        
        // 1부터 n까지의 각 구간의 누적합 계산해서 배열에 넣어줌
        for(int i=1; i<=n; i++) {
            sum[i] = sum[i-1] + i;
        }
        
        int startIdx = 0;   // 시작 인덱스
        
        for(int i=1; i<=n; i++) {
            int temp = sum[i] - sum[startIdx];
            // 계산 결과가 n인 경우
            if(temp == n) {
                answer++;   // 갯수 증가
                startIdx++; // 시작 인덱스 증가(오른쪽으로 이동)
                i = startIdx;   // 다시 구간합 비교할 수 있게끔
            }
            // 계산 결과가 n보다 큰 경우
            else if(temp > n) {
                startIdx++; // 시작 인덱스 증가(오른쪽으로 이동)
                i = startIdx;   // 다시 구간합 비교할 수 있게끔
            }
        }
        
        
        return answer;
    }
}