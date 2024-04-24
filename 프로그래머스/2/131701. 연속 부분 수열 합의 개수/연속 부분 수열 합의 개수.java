import java.util.*;

class Solution {
    
    public int solution(int[] elements) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        int[] arr = new int[elements.length * 2];   // 원순열이므로 원래 배열에서 2배해서 이어붙여줌
        // 원래 배열 이어붙여줌
        for(int i=0; i<arr.length; i++) {
            arr[i] = elements[i % elements.length];
        }
        
        
        int count = 1;  // 슬라이딩 윈도우 알고리즘 이용하기 위한 count 변수
        
        // 슬라이딩 윈도우 알고리즘 이용
        while(true) {
            int sum = 0;    // 구간 합
            // 탈출조건 (원래 배열보다 길이가 크면 탈출함)
            if(count == elements.length+1) {
                break;
            }
            // 슬라이딩 윈도우 알고리즘 -> 시간복잡도: O(N)
            for(int i=0; i<arr.length; i++) {
                sum+=arr[i];
                // 처음 길이를 벗어났을 때 부터 한칸씩 밀어준다
                if(i >= count) {
                    sum-=arr[i-count];
                }
                set.add(sum);   // 중복되는 값을 제외해줘야하므로 set에 저장
            }
            
            count++;    // 연속 부분 수열의 길이 증가
        }
        
        answer = set.size();
        
        return answer;
    }
}