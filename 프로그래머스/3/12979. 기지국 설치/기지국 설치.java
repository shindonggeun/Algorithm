import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int coverage = 2 * w + 1; // 기지국이 커버할 수 있는 전파 범위 (한 기지국으로 커버할 수 있는 아파트의 수)
        int idx = 0; // 현재 전파가 닿지 않는 첫 번째 아파트의 위치를 저장하는 변수
        int count = 0; // 추가로 설치해야 할 기지국의 개수를 저장하는 변수
        
        // 1. 기존의 기지국 위치 순회하며 전파가 닿지 않는 구간 탐색
        for (int station: stations) {
            int startOfCoverage = station - w; // 현재 기지국의 전파가 닿기 시작하는 아파트의 번호 (전파 시작 시점)
            
            // 현재 인덱스보다 전파 시작 시점이 더 위에 있는 경우
            // 이 경우의 구간은 전파가 닿지 않는 구간임
            if (idx + 1 < startOfCoverage) {
                // 전파가 닿지 않는 구간의 길이 계산
                int length = startOfCoverage - (idx + 1);
                // 해당 구간을 커버하기 위해 필요한 기지국 개수 계산
                count += Math.ceil((double) length / coverage);
            }
            idx = station + w; // 다음 전파 시작 시점을 현재 기지국의 전파 끝나는 지점으로 갱신 
        }
        
        // 2. 마지막 기지국 이후에도 전파가 닿지 않는 구간이 남아 있는지 확인
        // 현재 인덱스가 아파트의 마지막 인덱스보다 작은 경우
        if (idx < n) {
            int length = n - idx; // 남은 구간의 길이 계산
            // 남은 구간을 커버하기 위해 필요한 기지국 개수 계산
            count += Math.ceil((double) length / coverage);
        }
        
        answer = count; // 총 필요한 기지국 개수 결과값에 저장
        
        return answer;
    }
}