import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};
        
        // s가 n보다 큰 경우
        if (s < n) {
            // 자연수 n개로 합이 s가 되는 집합을 만들 수 없으므로 -1 반환
            answer = new int[] {-1};
            return answer;
        }
        
        int baseValue = s / n; // 각 원소가 가질 수 있는 기본값 즉, s를 n으로 나눈 몫
        int remainder = s % n; // s를 n으로 나눈 나머지, 즉 해당 값은 일부 원소에 추가되게끔
        
        answer = new int[n]; // 결과 배열을 크기 n으로 할당 [0] ~ [n-1]
        
        Arrays.fill(answer, baseValue); // 해당 결과 배열의 모든 원소 기본값으로 채움
        
        int idx = n - 1; // 배열의 마지막 인덱스 설정
        // 나머지 값 횟수만큼 반복 (해당 결과값 오름차순으로 정렬해줄 수 있게끔 마지막 인덱스부터 접근)
        for (int i=0; i<remainder; i++) {
            answer[idx]++; // 현재 인덱스에 해당하는 원소값 증가 (+1)
            idx--; // 다음 인덱스로 이동 (왼쪽으로 한칸 이동 (인덱스 점점 감소))
        }
        
        return answer;
    }
}