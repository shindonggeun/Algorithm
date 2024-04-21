import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        // 1. 정렬
        Arrays.sort(data, (tuple1, tuple2) -> {
            // 튜플의 col 번째 컬럼의 값이 같은 경우
            if (tuple1[col-1] == tuple2[col-1]) {
                // 튜플에서 기본키인 첫 번째 컬럼의 값을 기준으로 내림차순 정렬
                return tuple2[0] - tuple1[0];
            }
            // 튜플의 col 번째 컬럼의 값 기준으로 오름차순 정렬
            return tuple1[col-1] - tuple2[col-1];
        });
        
        // 2. 정렬된 데이터 기준으로 시작행부터 도착행까지 탐색
        for (int i=row_begin; i<=row_end; i++) {
            int sum = 0;    // 정렬된 데이터에서 i번째 행의 튜플에 대해서 각 컬럼의 값을 i로 나눈 나머지들의 합
            
            int[] tuple = data[i-1];    // 정렬된 데이터에서 해당 행에 해당하는 튜플들 추출
            // 튜플에 대한 각 컬럼의 값 탐색
            for (int value: tuple) {
                // 3. 나머지들의 합
                sum += (value % i);
            }
            
            // 4. S_i 누적한 것 XOR한 해시값 추출
            answer ^= sum;
        }
        
        return answer;
    }
}