import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = -1;
        
        // 1. A팀과 B팀의 순서를 서로 비교하기 쉽게 정렬하는 과정
        // B팀이 최대한 높은 승점을 얻을 수 있게끔 출전 순서를 결정하기 위해 정렬해서 비교함
        Arrays.sort(A); // A팀의 숫자들 오름차순 정렬
        Arrays.sort(B); // B팀의 숫자들 오름차순 정렬
        
        int n = A.length;
        
        // 2. 두 팀의 인덱스 설정 과정
        int aIdx = 0; // A팀의 현재 비교 중인 사원의 인덱스 설정
        int bIdx = 0; // B팀의 현재 비교 중인 사원의 인덱스 설정
        int score = 0; // B팀이 얻는 승점을 저장할 변수
        
        // 3. A팀과 B팀의 사원들을 비교하는 과정
        for (int i=0; i<n; i++) {
            // A팀의 aIdx번째의 숫자가 B팀의 bIdx번째의 사원의 숫자보다 큰 경우
            if (A[aIdx] > B[bIdx]) {
                bIdx++; // B팀이 승리할 수 없으므로 B팀의 다음 사원을 확인할 수 있도록 B팀의 비교중인 현재 인덱스 증가
            }
            // A팀의 aIdx번째의 숫자가 B팀의 bIdx번째의 사원의 숫자와 같은 경우
            else if (A[aIdx] == B[bIdx]) {
                bIdx++; // B팀이 승리할 수 없으므로 B팀의 다음 사원을 확인할 수 있도록 B팀의 비교중인 현재 인덱스 증가
            }
            // A팀의 aIdx번째의 숫자가 B팀의 bIdx번째의 사원의 숫자보다 작은 경우
            else {
                // 해당 경우는 B팀이 승리했으므로 승점을 얻고, A팀과 B팀 모두 다음 사원을 비교
                aIdx++;
                bIdx++;
                score++; // B팀의 승점 증가
            }
        }
        
        answer = score; // 결과값에 B팀의 최종 승점 반영
        return answer;
    }
    
}