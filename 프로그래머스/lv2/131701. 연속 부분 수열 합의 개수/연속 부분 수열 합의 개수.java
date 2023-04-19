import java.util.*;

class Solution {
    
    public int solution(int[] elements) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        int[] arr = new int[elements.length * 2];
        for(int i=0; i<arr.length; i++) {
            arr[i] = elements[i % elements.length];
        }
        
        int count = 1; 
        
        // 슬라이딩 윈도우 알고리즘 이용
        while(true) {
            int sum = 0;
            
            if(count == elements.length+1) {
                break;
            }
            
            for(int i=0; i<arr.length; i++) {
                sum+=arr[i];
                
                if(i >= count) {
                    sum-=arr[i-count];
                }
                set.add(sum);
            }
            
            count++;
        }
        //System.out.println(set);
        answer = set.size();
        
        return answer;
    }
}