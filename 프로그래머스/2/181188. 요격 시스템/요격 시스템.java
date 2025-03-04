import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        // 1. 폭격 미사일을 끝나는 지점 (e)을 기준으로 정렬하는 과정
        Arrays.sort(targets, (o1,o2) -> {
            // 만약 끝나는 지점이 같은 경우
            if (o1[1] == o2[1]) {
                // 시작 지점 (s) 기준 오름차순 정렬
                return o1[0] - o2[0];
            }
            
            // 끝나는 지점 기준으로 오름차순 정렬
            return o1[1] - o2[1];
        });
        
        int missile = 0; // 요격 미사일이 발사된 x 좌표
        
        // 2. 그리디 알고리즘을 이용하여 최소 요격 미사일 횟수 계산하는 과정
        for (int i=0; i<targets.length; i++) {
            // 요격 미사일의 좌표가 해당 폭격 미사일의 시작 지점보다 작거나 같은 경우
            // 즉, 해당 경우는 요격 미사일을 새로 발사해야 하는 경우임
            if (missile <= targets[i][0]) {
                missile = targets[i][1]; // 요격 미사일을 현재 폭격 미사일의 끝 지점에 발사하도록 좌표 설정
                answer++; // 요격 미사일 횟수 증가
            }
        }
        
        return answer;
    }
}