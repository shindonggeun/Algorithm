import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        // 1. 이분탐색 알고리즘을 이용하기 위해 바위들의 위치를 담은 배열 오름차순 정렬
        Arrays.sort(rocks);
        
        int start = 1; // 시작점 (가능한 최소 거리)
        int end = distance; // 도착점 (가능한 최대 거리)
        
        // 2. 이분탐색 진행
        while (start <= end) {
            int mid = (start + end) / 2; // 현재 탐색하는 거리의 최솟값
            
            // 3. 바위 제거가 가능한지 검사 (매개변수 탐색)
            if (checkRemoveRocks(rocks, distance, mid, n)) {
                answer = mid; // 가능한 값 중 최대값 저장
                start = mid + 1; // 거리 증가
            }
            else {
                end = mid - 1; // 거리 감소
            }
        }
        
        return answer;
    }
    
    // 바위를 제거했을 때 거리 mid 이상을 유지할 수 있는지 검사하는 메서드
    public boolean checkRemoveRocks(int[] rocks, int distance, int mid, int n) {
        int removeCount = 0; // 제거한 바위 개수
        int prevPos = 0; // 이전 위치 (출발 지점)
        
        // 모든 바위들 탐색
        for (int rock: rocks) {
            // 해당 바위의 위치에서 이전 위치를 뺀 값 (= 지점 사이의 거리)가 해당 탐색한 거리의 최소값보다 작은 경우
            // 즉, 현재 바위가 제거 가능한 경우
            if (rock - prevPos < mid) {
                removeCount++; // 제거한 바위 개수 증가
            }
            // 현재 바위가 제거 불가능한 경우
            else {
                prevPos = rock; // 기준점을 업데이트함 
            }
            
            // 제거한 바위 개수가 n개 초과한 경우
            if (removeCount > n) {
                return false; // n개 이하로 바위 제거 불가능
            }
        }
        
        // 마지막 도착 지점까지 거리 확인하기
        if (distance - prevPos < mid) {
            removeCount++;
        }
        
        // n개 이하로 제거 가능한 경우 true, 불가능한 경우 false 반환
        return removeCount <= n;
    }
    
}