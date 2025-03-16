import java.util.*;

public class Solution {
    
    static List<Integer> list; // 연속적으로 중복된 숫자를 제거한 값을 저장할 리스트
    
    public int[] solution(int[] arr) {
        int[] answer = {};
        
        list = new ArrayList<>();
        list.add(arr[0]); // 첫 번째 숫자는 무조건 추가
        
        // 1. 배열의 1번방부터 순회하며 이전 값과 비교하며 다른 값만 추가하는 과정
        for (int i=1; i<arr.length; i++) {
            // 해당 숫자가 이전 숫자와 다른 경우
            if (arr[i] != arr[i - 1]) {
                list.add(arr[i]); // 리스트에 추가
            }
        }
        
        // 2. 해당 리스트를 결과 배열로 변환하는 과정
        answer = new int[list.size()]; // 결과 배열 리스트의 사이즈만큼 배열 방 개수 생성
        
        for (int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}