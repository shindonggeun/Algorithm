import java.util.*;

class Solution {
    public int[] solution(int[] numlist, int n) {
        int[] answer = new int[numlist.length];
        List<Integer> list = new ArrayList<>();
        // int형 배열을 list화 하기 위해 반복문 돌리기
        for(int i=0; i<numlist.length; i++) {
            list.add(numlist[i]);
        }
        
        // 사용자 정의에 의해서 ArrayList 정렬 
		Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                int num1 = Math.abs(i1 - n);
                int num2 = Math.abs(i2 - n);
                // System.out.println("일반값: "+ i1 + " " + i2 + "   차이값: " + num1 + " " + num2);
                
                // 거리(n) 비교한 두 값이 같으면 (n으로 부터 거리가 둘다 똑같으면 값이 더 큰게 앞에 와야하므로)
                if(num1 == num2) {
                    return i2 - i1; // 내림차순 정렬(더 큰 값이 앞으로) (원래 리스트에 있는 값 내림차순)
                } 
                // 그 이외는 거리 차이 작은(즉, 가까운) 순으로 정렬
                return num1 - num2;
            }
        });
        
        // ArrayList를 배열로 변환하는 과정
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}