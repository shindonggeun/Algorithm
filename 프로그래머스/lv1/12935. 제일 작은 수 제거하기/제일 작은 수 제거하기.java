import java.util.*;

class Solution {
    public List<Integer> solution(int[] arr) {
        //int[] answer = {}; 
        List<Integer> list = new ArrayList<>();
        // int형 배열 ArrayList로 복사(반복문 이용)
        for(int i: arr) {
            list.add(i);
        }
        int min = list.get(0);
        
        for(int i=0; i<list.size(); i++)  {
            if(list.get(i) < min) {
                min = list.get(i);
            }
        }
        
        if(list.contains(min) && list.size() >= 2) {
            // list의 min값에 해당하는 원소 제거
            list.remove(Integer.valueOf(min));
        }
        else if(list.size() == 1) {
            list.remove(list.size() - 1);
            list.add(-1);
        }
        
        
        return list;
    }
}