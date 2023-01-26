import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int[][] result = new int[sizes.length][2];
        
        for(int i=0; i<sizes.length; i++) {
            int big = 0;  
            int small = 0;
            // 
            if(sizes[i][0] < sizes[i][1]) {
                big = sizes[i][1];
                small = sizes[i][0];
            }
            else {
                big = sizes[i][0];
                small = sizes[i][1];
            }
            result[i][0] = big;
            result[i][1] = small;
        }
        
        int w_max = 0;
        int h_max = 0;
        for(int i=0; i<result.length; i++) {
            if(w_max < result[i][0]) {
                w_max = result[i][0];
            }
            
            if(h_max < result[i][1]) {
                h_max = result[i][1];
            }
        }
        
        answer = w_max * h_max;
        
        return answer;
    }
}