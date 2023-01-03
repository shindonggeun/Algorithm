import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int i=0; i<commands.length; i++) {
            int[] arr = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
            Arrays.sort(arr);   // 오름차순 정렬
            System.out.println(Arrays.toString(arr));
            //System.out.println(arr[commands[i][2] - 1]);
            answer[i] = arr[commands[i][2] - 1];
        }
        
        
        return answer;
    }
}