import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        // 고속도로를 이동하는 차량의 경로를 진출 지점으로 오름차순 정렬
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        
        int camera = Integer.MIN_VALUE; // 카메라가 마지막으로 설치된 위치
        
        for (int[] route: routes) {
            int inPoint = route[0];
            int outPoint = route[1];
            
            // 현재 카메라 위치가 다음 차량의 진입 지점보다 앞쪽에 위치 한 경우
            if (camera < inPoint) {
                camera = outPoint;  // 해당 차량의 진출 지점에 카메라 설치
                answer++;   // 현재 카메라 설치한 개수 증가
            }
        }
        
        return answer;
    }
}