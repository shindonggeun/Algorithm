import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] arr, int divisor) {
        //int[] answer = {};
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int i=0; i<arr.length; i++) {
            if(arr[i] % divisor == 0) {
                answer.add(arr[i]);
            }
        }
        
        if(answer.size() == 0) {
            answer.add(-1);
        }
        else {
            Collections.sort(answer);   // 오름차순 정렬 
        }
        
        return answer;
    }
}