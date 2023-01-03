import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int i=0; i<commands.length; i++) {
            // 배열 복사(복사할 배열, 시작인덱스, 끝 인덱스) -> 복사할 배열을 시작인덱스부터 끝 인덱스 전! 까지 복사함
            int[] arr = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
            Arrays.sort(arr);   // 오름차순 정렬(내림차순일경우는 arr배열 Integer로 타입 변경해줘야함)
            //System.out.println(Arrays.toString(arr));
            //System.out.println(arr[commands[i][2] - 1]);
            answer[i] = arr[commands[i][2] - 1];
        }
        
        
        return answer;
    }
}