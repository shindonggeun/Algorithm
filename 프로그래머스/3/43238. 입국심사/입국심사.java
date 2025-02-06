import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long minTime = 1;   // 심사 최소 시간 1로 초기화 (심사하는데 걸리는 시간 1분부터 시작이므로)
        
        Arrays.sort(times); // 심사하는데 걸리는 시간을 저장한 배열 오름차순 정렬
        
        // 최종 심사하는데 걸리는 최대 시간
        // 최악의 경우는 가장 느린 심사대에서 모든 사람이 심사를 받는 경우이므로 *n을 해준다
        long maxTime = (long) times[times.length - 1] * n;
        answer = maxTime;   // 일단 결과값 최악의 경우로 저장 (최종 심사하는데 걸리는 최대 시간으로)
        
        while (minTime <= maxTime) {
            long mid = (minTime + maxTime) / 2;
            long completeCount = 0; // 각 심사대가 처리할 수 있는 최대 인원 수
            
            for (int time: times) {
                completeCount += mid / time;  // 이 시간동안 각 심사대가 처리할 수 있는 최대 인원 수 계산
            }
            
            // 각 심사대가 처리할 수 있는 최대 인원 수가 입국 심사를 기다리는 사람을 모두 처리할 수 있는 경우
            if (completeCount >= n) {
                maxTime = mid - 1;  // 모든 사람을 심사하는데 걸리는 최대 시간을 줄임 (범위 줄이기)
                answer = mid;   // 시간범위 안에 들은 최소 시간 저장
            }
            // 모두 처리 불가능한 경우 (모든 인원 심사할 수 없는 경우)
            else {
                minTime = mid + 1;  // 모든 사람을 심사하는데 걸리는 최소 시간을 늘림 (범위 줄이기)
            }
        }
        
        return answer;
    }
}