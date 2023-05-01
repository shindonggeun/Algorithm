import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        // 2번 과정 (정렬)
        Arrays.sort(data, new Comparator<int[]>() {
           
            @Override
            public int compare(int[] tuple1, int[] tuple2) {
                // 테이블의 튜플에서 col값이 동일한 경우 
                if(tuple1[col-1] == tuple2[col-1]) {
                    return tuple2[0] - tuple1[0];   // 기본키인 첫번째 컬럼의 값을 기준으로 내림차순 정렬
                }
                return tuple1[col-1] - tuple2[col-1];    // col번째 컬럼의 값을 기준으로 오름차순 정렬
            }
        });
        //System.out.println(Arrays.deepToString(data));
        
        // 3번 과정 (정렬된 데이터에서 s_i를 i_번쨰 행의 튜플에 대해 각 컬럼의 값을 i로 나눈 나머지들의 합으로 정의)
        for(int i=row_begin; i<=row_end; i++){
            int sum = 0;
            
            for(int column : data[i-1]) {
                sum += (column % (i));
            }
            answer ^= sum;  // 4번 과정 (XOR 연산)
        }
        
        return answer;
    }
}