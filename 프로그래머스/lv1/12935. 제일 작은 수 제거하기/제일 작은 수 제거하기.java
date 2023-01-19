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
        
        // ArrayList에서 최소값 찾는 과정
        for(int i=0; i<list.size(); i++)  {
            if(list.get(i) < min) {
                min = list.get(i);
            }
        }
        
        list.remove(Integer.valueOf(min));   // list의 min값에 해당하는 원소 제거
        // 삭제하고 나서 list의 길이가 0이면 원소 -1 추가
        if(list.size() == 0) {
            list.add(-1);
        }
        
        
        return list;
    }
}